package com.etc.tencent.aiperson;

import com.etc.tencent.aiperson.entity.rest.TencentCloudRestRequest;
import com.etc.tencent.aiperson.entity.rest.TencentCloudRestResponse;
import com.etc.tencent.aiperson.entity.rest.aiperson.CustomerMakeReq;
import com.etc.tencent.aiperson.entity.rest.aiperson.CustomerMakeRsp;
import com.etc.tencent.aiperson.entity.rest.aiperson.CustomerconfirmReq;
import com.etc.tencent.aiperson.entity.rest.aiperson.DescribetimbreReq;
import com.etc.tencent.aiperson.entity.rest.aiperson.DescribetimbreRsp;
import com.etc.tencent.aiperson.entity.rest.aiperson.GetTempTokenRsp;
import com.etc.tencent.aiperson.entity.rest.aiperson.GetprogressReq;
import com.etc.tencent.aiperson.entity.rest.aiperson.ImageCheckReq;
import com.etc.tencent.aiperson.entity.rest.aiperson.TaskStateResponseRsp;
import com.etc.tencent.aiperson.entity.rest.aiperson.TaskStatusResponseRsp;
import com.etc.tencent.aiperson.entity.rest.audio.GetProgressRsp;
import com.etc.tencent.aiperson.entity.rest.audio.TtsReq;
import com.etc.tencent.aiperson.entity.rest.audio.VideoMakeNoTrainReq;
import com.etc.tencent.aiperson.entity.rest.voice.AudioCheckReq;
import com.etc.tencent.aiperson.entity.rest.voice.GetTextReq;
import com.etc.tencent.aiperson.entity.rest.voice.GetTextRsp;
import com.etc.tencent.aiperson.entity.rest.voice.MakeVoiceReq;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import com.etc.tencent.aiperson.entity.rest.TencentHttpException;
import com.etc.tencent.aiperson.entity.rest.TencentCloudRestError;
import com.etc.tencent.aiperson.interceptor.AuthenticationInterceptor;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static com.etc.tencent.aiperson.constant.TencentAipersonConstant.BASE_URL;

@Slf4j
public class TencentAiPersonService {
    private static final Integer DEFAULT_TIMEOUT = 30;
    private static final ObjectMapper errorMapper = defaultObjectMapper();

    private final TencentAiPersonApi personApi;
    private final TencentAiVoiceApi voiceApi;
    private final TencentAiAudioApi audioApi;

    private final OkHttpClient client;


    public TencentAiPersonService(String appkey, String accessToken, String requestid) {
        this(buildPersonApi(appkey, accessToken, requestid, DEFAULT_TIMEOUT),
                buildVoiceApi(appkey, accessToken, requestid, DEFAULT_TIMEOUT),
                buildAudioApi(appkey, accessToken, requestid, DEFAULT_TIMEOUT),
                defaultClient(appkey, accessToken, requestid, DEFAULT_TIMEOUT)
        );
    }

    public TencentAiPersonService(final TencentAiPersonApi personApi,
                                  final TencentAiVoiceApi voiceApi,
                                  final TencentAiAudioApi audioApi,
                                  final OkHttpClient client) {
        this.personApi = personApi;
        this.voiceApi = voiceApi;
        this.audioApi = audioApi;
        this.client = client;
    }


    public static TencentAiPersonApi buildPersonApi(String appkey, String accessToken, String requestid, Integer timeout) {
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(appkey, accessToken, requestid, timeout);
        Retrofit retrofit = defaultRetrofit(client, mapper);
        return retrofit.create(TencentAiPersonApi.class);
    }

    public static TencentAiVoiceApi buildVoiceApi(String appkey, String accessToken, String requestid, Integer timeout) {
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(appkey, accessToken, requestid, timeout);
        Retrofit retrofit = defaultRetrofit(client, mapper);
        return retrofit.create(TencentAiVoiceApi.class);
    }
    public static TencentAiAudioApi buildAudioApi(String appkey, String accessToken, String requestid, Integer timeout) {
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(appkey, accessToken, requestid, timeout);
        Retrofit retrofit = defaultRetrofit(client, mapper);
        return retrofit.create(TencentAiAudioApi.class);
    }

    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        return mapper;
    }

    /**
     * @param appkey
     * @param accessToken
     * @param requestid
     * @param timeout  单位秒
     * @return {@link OkHttpClient }
     */
    public static OkHttpClient defaultClient(String appkey, String accessToken, String requestid, Integer timeout) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(appkey, accessToken, requestid))
                .connectionPool(new ConnectionPool(10, 4, TimeUnit.SECONDS))
                .readTimeout(Duration.ofSeconds(timeout).toMillis(), TimeUnit.MILLISECONDS)
                .build();

    }


    public static Retrofit defaultRetrofit(OkHttpClient client, ObjectMapper mapper) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    /**
     * Calls the Open AI api, returns the response, and parses error messages if the request fails
     */
    public static <T> T execute(Single<T> apiCall) {
        try {
            return apiCall.blockingGet();
        } catch (HttpException e) {
            try {
                if (e.response() == null || e.response().errorBody() == null) {
                    throw e;
                }
                String errorBody = e.response().errorBody().string();
                TencentCloudRestError error = errorMapper.readValue(errorBody, TencentCloudRestError.class);
                throw new TencentHttpException(error, error.getHeader().getMessage(), e);
            } catch (IOException ex) {
                // couldn't parse OpenAI error
                throw e;
            }
        }
    }

    /**
     * 获取上传临时令牌
     * https://cloud.tencent.com/document/product/1240/96067
     * @return
     */
    public TencentCloudRestResponse<GetTempTokenRsp> getuploadcredentials() {
        TencentCloudRestRequest request = new TencentCloudRestRequest<>();
        return execute(personApi.getuploadcredentials(request));
    }

    /**
     * 定制接口
     * https://cloud.tencent.com/document/product/1240/96069
     * @param request
     * @return
     */
    public TencentCloudRestResponse<CustomerMakeRsp> make(TencentCloudRestRequest<CustomerMakeReq> request) {
        return execute(personApi.make(request));
    }

    /**
     * 进度查询接口
     * https://cloud.tencent.com/document/product/1240/96070
     *
     * @param request
     * @return
     */
    public TencentCloudRestResponse<TaskStatusResponseRsp> getprogress(TencentCloudRestRequest<GetprogressReq> request) {
        return execute(personApi.getprogress(request));
    }


    /**
     * 效果确认接口
     * https://cloud.tencent.com/document/product/1240/102665
     *
     * @param request
     * @return
     */
    public TencentCloudRestResponse customerconfirm(TencentCloudRestRequest<CustomerconfirmReq> request) {
        return execute(personApi.customerconfirm(request));
    }

    /**
     * 人脸检测任务创建
     * https://cloud.tencent.com/document/product/1240/109990
     *
     * @param request
     * @return
     */
    public TencentCloudRestResponse<CustomerMakeRsp> facedetect(TencentCloudRestRequest<ImageCheckReq> request) {
        return execute(personApi.facedetect(request));
    }


    /**
     * 人脸检测任务状态查询
     * https://cloud.tencent.com/document/product/1240/109991
     *
     * @param request
     * @return
     */
    public TencentCloudRestResponse<TaskStateResponseRsp> getfacedetectstate(TencentCloudRestRequest<GetprogressReq> request) {
        return execute(personApi.getfacedetectstate(request));
    }

    // ======================================= 声音相关接口 ===================================
    /**
     * 音质检测任务创建接口
     * https://cloud.tencent.com/document/product/1240/109991
     *
     * @param request
     * @return
     */
    public TencentCloudRestResponse<CustomerMakeRsp> audioevaluation(TencentCloudRestRequest<AudioCheckReq> request) {
        return execute(voiceApi.audioevaluation(request));
    }
    // 已存在的字段和构造方法保持不变...
    // ========================== Person API ==========================
    /**
     * 获取人脸检测任务状态
     * @param request 包含TaskId的请求
     */
    public TencentCloudRestResponse<TaskStateResponseRsp> getFaceDetectState(TencentCloudRestRequest<GetprogressReq> request) {
        return execute(personApi.getfacedetectstate(request));
    }

    // ========================== Voice API ==========================
    /**
     * 创建音质检测任务
     * @param request 音频检测请求参数
     */
    public TencentCloudRestResponse<CustomerMakeRsp> createAudioEvaluation(
            TencentCloudRestRequest<AudioCheckReq> request
    ) {
        return execute(voiceApi.audioevaluation(request));
    }

    /**
     * 查询音质检测任务状态
     * @param request 包含TaskId的请求
     */
    public TencentCloudRestResponse<CustomerMakeRsp> getAudioEvaluationState(
            TencentCloudRestRequest<GetprogressReq> request
    ) {
        return execute(voiceApi.getaudioevaluationstate(request));
    }

    /**
     * 创建声音克隆任务
     * @param request 声音制作请求参数
     */
    public TencentCloudRestResponse<CustomerMakeRsp> createVoiceClone(
            TencentCloudRestRequest<MakeVoiceReq> request
    ) {
        return execute(voiceApi.make(request));
    }

    /**
     * 获取文本转语音结果
     * @param request 包含SessionId的请求
     */
    public TencentCloudRestResponse<GetTextRsp> getTtsResult(
            TencentCloudRestRequest<GetTextReq> request
    ) {
        return execute(voiceApi.gettext(request));
    }

    // ========================== Audio API ==========================
    /**
     * 创建免训练版视频任务
     * @param request 视频生成请求参数
     */
    public TencentCloudRestResponse<CustomerMakeRsp> createVideoWithoutTraining(
            TencentCloudRestRequest<VideoMakeNoTrainReq> request
    ) {
        return execute(audioApi.videomakenotrain(request));
    }

    /**
     * 创建基础版视频任务
     * @param request 视频生成请求参数
     */
    public TencentCloudRestResponse<CustomerMakeRsp> videomake(
            TencentCloudRestRequest<VideoMakeNoTrainReq> request
    ) {
        return execute(audioApi.videomake(request));
    }

    /**
     * 文本转语音合成
     * @param request 语音合成请求参数
     */
    public TencentCloudRestResponse<CustomerMakeRsp> tts(
            TencentCloudRestRequest<TtsReq> request
    ) {
        return execute(audioApi.tts(request));
    }

    public TencentCloudRestResponse<GetProgressRsp> pollTaskStatus(TencentCloudRestRequest<GetprogressReq> request) {
        return execute(audioApi.getprogress(request));
    }

    /**
     * 批量提交音视频检测
     * @param audioRequests 音频检测请求列表
     * @param imageRequests 图片检测请求列表
     */
    public List<TencentCloudRestResponse<?>> batchSubmitTasks(
            List<TencentCloudRestRequest<AudioCheckReq>> audioRequests,
            List<TencentCloudRestRequest<ImageCheckReq>> imageRequests
    ) {
        List<TencentCloudRestResponse<?>> results = new ArrayList<>();

        audioRequests.forEach(req ->
                results.add(execute(voiceApi.audioevaluation(req)))
        );

        imageRequests.forEach(req ->
                results.add(execute(personApi.facedetect(req)))
        );

        return results;
    }

    public TencentCloudRestResponse<DescribetimbreRsp> describetimbre(TencentCloudRestRequest<DescribetimbreReq> request) {
        return execute(personApi.describetimbre(request));
    }



}
