package com.etc.tencent.aiperson;

import com.etc.tencent.aiperson.entity.rest.TencentCloudRestRequest;
import com.etc.tencent.aiperson.entity.rest.TencentCloudRestResponse;
import com.etc.tencent.aiperson.entity.rest.aiperson.CustomerMakeRsp;
import com.etc.tencent.aiperson.entity.rest.aiperson.GetprogressReq;
import com.etc.tencent.aiperson.entity.rest.audio.GetProgressRsp;
import com.etc.tencent.aiperson.entity.rest.audio.TtsReq;
import com.etc.tencent.aiperson.entity.rest.audio.VideoMakeNoTrainReq;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit2 API interface
 */
public interface TencentAiAudioApi {



    /**
     * 音视频制作进度查询接口
     * https://cloud.tencent.com/document/product/1240/81270
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/videomaker/broadcastservice/getprogress")
    Single<TencentCloudRestResponse<GetProgressRsp>> getprogress(@Body TencentCloudRestRequest<GetprogressReq> request);


    /**
     * 视频制作接口-免训练版
     * https://cloud.tencent.com/document/product/1240/116222
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/videomaker/broadcastservice/videomakenotrain")
    Single<TencentCloudRestResponse<CustomerMakeRsp>> videomakenotrain(@Body TencentCloudRestRequest<VideoMakeNoTrainReq> request);


    /**
     * 视频制作接口-基础版
     * https://cloud.tencent.com/document/product/1240/81268
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/videomaker/broadcastservice/videomake")
    Single<TencentCloudRestResponse<CustomerMakeRsp>> videomake(@Body TencentCloudRestRequest<VideoMakeNoTrainReq> request);


    /**
     * 音频制作接口
     * https://cloud.tencent.com/document/product/1240/81267
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/videomaker/broadcastservice/tts")
    Single<TencentCloudRestResponse<CustomerMakeRsp>> tts(@Body TencentCloudRestRequest<TtsReq> request);




}
