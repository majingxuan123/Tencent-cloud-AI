package com.etc.tencent.test;

import com.etc.tencent.aiperson.TencentAiPersonService;
import com.etc.tencent.aiperson.entity.rest.TencentCloudRestRequest;
import com.etc.tencent.aiperson.entity.rest.aiperson.CustomerMakeRsp;
import com.etc.tencent.aiperson.entity.rest.aiperson.GetTempTokenRsp;
import com.etc.tencent.aiperson.entity.rest.aiperson.GetprogressReq;
import com.etc.tencent.aiperson.entity.rest.audio.GetProgressRsp;
import com.etc.tencent.aiperson.entity.rest.audio.VideoMakeNoTrainReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.CreateBucketRequest;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.etc.tencent.aiperson.entity.rest.TencentCloudRestResponse;

@Slf4j
public class OkxServiceTest {

    private TencentAiPersonService getTencentAiPersonService() {
//        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 7890));
        String appKey = "";
        String accessToken = "";
        String requestid = "";
        return new TencentAiPersonService(appKey, accessToken, requestid);
    }


    @Test
    public void testPutObjectToTencent(){
        // 1 传入获取到的临时密钥 (tmpSecretId, tmpSecretKey, sessionToken)
        String tmpSecretId = "SECRETID";
        String tmpSecretKey = "SECRETKEY";
        String sessionToken = "TOKEN";
        BasicSessionCredentials cred = new BasicSessionCredentials(tmpSecretId, tmpSecretKey, sessionToken);
// 2 设置 bucket 的地域
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分
        Region region = new Region("COS_REGION"); //COS_REGION 参数：配置成存储桶 bucket 的实际地域，例如 ap-beijing，更多 COS 地域的简称请参见 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(region);
// 3 生成 cos 客户端
        COSClient cosClient = new COSClient(cred, clientConfig);

        // ======================创建存储桶=====================
        String bucket = "examplebucket-1250000000"; //存储桶名称，格式：BucketName-APPID
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
// 设置 bucket 的权限为 Private(私有读写)、其他可选有 PublicRead（公有读私有写）、PublicReadWrite（公有读写）
        createBucketRequest.setCannedAcl(CannedAccessControlList.Private);
        try{
            Bucket bucketResult = cosClient.createBucket(createBucketRequest);
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        }
    }



    @Test
    public void test01() {
        TencentAiPersonService service = getTencentAiPersonService();
        TencentCloudRestResponse<GetTempTokenRsp> getuploadcredentials = service.getuploadcredentials();
        System.out.println("===="+getuploadcredentials);
//        TencentCloudRestResponse(header=TencentCloudRestResponse.Header(requestId=569d4868296474d623f70ca8caa51e5c,
//        sessionId=sh16c5813517465179210481896, code=0, message=ok),
    }


    @Test
    public void testIssue2() throws JsonProcessingException {
        TencentAiPersonService service = getTencentAiPersonService();
        TencentCloudRestRequest<VideoMakeNoTrainReq> request1 = new TencentCloudRestRequest<>();
        VideoMakeNoTrainReq.SpeechParam male1 = VideoMakeNoTrainReq.SpeechParam.builder().timbreKey("male_1").speed(1.0F).build();
        VideoMakeNoTrainReq build = VideoMakeNoTrainReq.builder().refVideoUrl("https://eas-gen-ai-test-1311433919.cos.ap-guangzhou.myqcloud.com/%E6%95%B0%E5%AD%97%E4%BA%BA.mp4")
                .driverType(VideoMakeNoTrainReq.DriverTypeEnum.TEXT)
                .inputSsml("今天我们要为大家介绍一款专为芒果种植设计的产品——益果丰。这是一款能显著改善芒果品质的神奇助剂。" +
                        "首先，益果丰能够有效促进芒果从黄白皮转变到成熟色泽，使得果实看起来更加诱人。同时，它还具有促进果实膨大的效果，让每个芒果都能长成理想的大小。" +
                        "使用益果丰后，芒果表面会自然形成一层细腻的果粉，这不仅增加了芒果的价值感，更提升了口感体验。不仅如此，这款产品还能保证同一树上结出的果实大小基本一致，解决了以往芒果大小参差不齐的问题。" +
                        "现在就加入我们吧！让益果丰帮助你轻松获得高品质、高产量的芒果收获。")
                .speechParam(male1).build();
        request1.setData(build);
        TencentCloudRestResponse<CustomerMakeRsp> videoWithoutTraining = service.createVideoWithoutTraining(request1);
        System.out.println("=================");
        System.out.println(videoWithoutTraining);
    }

    @Test
    public void pollTaskStatus() throws JsonProcessingException {
        TencentAiPersonService service = getTencentAiPersonService();
        TencentCloudRestRequest<GetprogressReq> request = new TencentCloudRestRequest<>();
        request.setData(GetprogressReq.builder().TaskId("4154d2956cca42e18XXXf").build());
        TencentCloudRestResponse<GetProgressRsp> getProgressRspTencentCloudRestResponse = service.pollTaskStatus(request);
        System.out.println("===="+getProgressRspTencentCloudRestResponse);
    }


}
