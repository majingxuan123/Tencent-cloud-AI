package com.etc.tencent.aiperson.interceptor;

import ch.qos.logback.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.etc.tencent.aiperson.constant.TencentAipersonConstant.*;
import java.security.InvalidKeyException;
/**
 * OkHttp Interceptor that adds an authorization header
 */
@Slf4j
public class AuthenticationInterceptor implements Interceptor {

    private final String appkey;
    private final String accessToken;
    private final String requestid;


    public AuthenticationInterceptor(String apiKey, String accessToken,String requestid) {
        this.appkey = apiKey;
        this.accessToken = accessToken;
        this.requestid = requestid;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        String method = chain.request().method();

        Map<String, String> parameter = new TreeMap<>();
//        String body = EMPTY;
//        if (method.equalsIgnoreCase("POST")) {
//            body = bodyToString(chain.request().body());
//        }

        String url;
        if(StringUtil.isNullOrEmpty(requestid)){
            // 示例一(访问需要提供 appkey 和 timestamp 参数的接口):
            // 用户按需填入生成签名所需的公共参数
            parameter.put("appkey", appkey);
            // 用户提供的时间戳应保证单位为秒且与当前时间相差不得超过五分钟
            // 示例时间戳:
            // parameter.put("timestamp", "1717639699");
            // 推荐使用下面的语句生成当前时间戳:
            parameter.put("timestamp", String.valueOf(Instant.now().getEpochSecond()));
            url = GenReqURL(parameter, this.accessToken, chain.request().url().toString());
        }else{
            parameter.put("appkey", appkey);
            parameter.put("requestid", requestid);
            // 用户提供的时间戳应保证单位为秒且与当前时间相差不得超过五分钟
            // 示例时间戳:
            // parameter.put("timestamp", "1717639699");
            // 推荐使用下面的语句生成当前时间戳:
            parameter.put("timestamp", String.valueOf(Instant.now().getEpochSecond()));
            url = GenReqURL(parameter, this.accessToken, chain.request().url().toString());
        }
        Request.Builder requestBuilder = chain.request()
                .newBuilder()
                .url(url);
        return chain.proceed(requestBuilder.build());
    }

    public static String GenReqURL(Map<String, String> parameter, String accessToken, String baseURL) {
        // 按字典序拼接待计算签名的字符串
        String signingContent = parameter.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        // 计算签名
        String signature = GenSignature(signingContent, accessToken);

        // 拼接访问接口的完整URL
        return baseURL + "?" + signingContent + signature;
    }

    public static String GenSignature(String signingContent, String accessToken) {
        try {
            // 计算 HMAC-SHA256 值
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(accessToken.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String hashInBase64 = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(signingContent.getBytes(StandardCharsets.UTF_8)));
            // URL encode
            String encodeSign = URLEncoder.encode(hashInBase64, StandardCharsets.UTF_8.toString());
            // 拼接签名
            String signature = "&signature=" + encodeSign;
            return signature;
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            if (request != null) {
                request.writeTo(buffer);
            } else {
                return EMPTY;
            }
            return buffer.readUtf8();
        } catch (final IOException e) {
            log.error("Failed to serialize request body", e);
            return EMPTY;
        }
    }

}
