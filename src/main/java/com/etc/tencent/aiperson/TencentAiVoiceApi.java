package com.etc.tencent.aiperson;

import com.etc.tencent.aiperson.entity.rest.TencentCloudRestRequest;
import com.etc.tencent.aiperson.entity.rest.TencentCloudRestResponse;
import com.etc.tencent.aiperson.entity.rest.aiperson.CustomerMakeReq;
import com.etc.tencent.aiperson.entity.rest.aiperson.CustomerMakeRsp;
import com.etc.tencent.aiperson.entity.rest.aiperson.CustomerconfirmReq;
import com.etc.tencent.aiperson.entity.rest.aiperson.GetTempTokenRsp;
import com.etc.tencent.aiperson.entity.rest.aiperson.GetprogressReq;
import com.etc.tencent.aiperson.entity.rest.aiperson.ImageCheckReq;
import com.etc.tencent.aiperson.entity.rest.aiperson.TaskStateResponseRsp;
import com.etc.tencent.aiperson.entity.rest.aiperson.TaskStatusResponseRsp;
import com.etc.tencent.aiperson.entity.rest.voice.AudioCheckReq;
import com.etc.tencent.aiperson.entity.rest.voice.DetectAudioQualityReq;
import com.etc.tencent.aiperson.entity.rest.voice.GetTextReq;
import com.etc.tencent.aiperson.entity.rest.voice.GetTextRsp;
import com.etc.tencent.aiperson.entity.rest.voice.MakeVoiceReq;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit2 API interface
 */
public interface TencentAiVoiceApi {

    /**
     * 音质检测任务创建接口
     * https://cloud.tencent.com/document/product/1240/102623
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/algotaskserver/algotaskservice/audioevaluation")
    Single<TencentCloudRestResponse<CustomerMakeRsp>> audioevaluation(@Body TencentCloudRestRequest<AudioCheckReq> request);


    /**
     * 音质检测任务状态查询接口
     * https://cloud.tencent.com/document/product/1240/102624
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/algotaskserver/algotaskservice/getaudioevaluationstate")
    Single<TencentCloudRestResponse<CustomerMakeRsp>> getaudioevaluationstate(@Body TencentCloudRestRequest<GetprogressReq> request);


    /**
     * 音质检测任务状态查询接口
     * https://cloud.tencent.com/document/product/1240/102624
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/assetmanager/customservice/make")
    Single<TencentCloudRestResponse<CustomerMakeRsp>> make(@Body TencentCloudRestRequest<MakeVoiceReq> request);

    /**
     * 进度查询接口
     * https://cloud.tencent.com/document/product/1240/96070
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/assetmanager/customservice/getprogress")
    Single<TencentCloudRestResponse<TaskStatusResponseRsp>> getprogress(@Body TencentCloudRestRequest<GetprogressReq> request);

    /**
     * 效果确认接口
     * https://cloud.tencent.com/document/product/1240/105214
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/assetmanager/customservice/customerconfirm")
    Single<TencentCloudRestResponse> customerconfirm(@Body TencentCloudRestRequest<CustomerconfirmReq> request);

    //================ 复制声音 ===============

    /**
     * 效果确认接口
     * https://cloud.tencent.com/document/product/1240/105214
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/assetmanager/zeroshotservice/gettext")
    Single<TencentCloudRestResponse<GetTextRsp>> gettext(@Body TencentCloudRestRequest<GetTextReq> request);

    /**
     * 提交音质检测任务
     * https://cloud.tencent.com/document/product/1240/104986
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/assetmanager/zeroshotservice/detectaudioquality")
    Single<TencentCloudRestResponse<CustomerMakeRsp>> detectaudioquality(@Body TencentCloudRestRequest<DetectAudioQualityReq> request);


    /**
     * 提交音质检测任务
     * https://cloud.tencent.com/document/product/1240/104986
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/assetmanager/zeroshotservice/getdetectprogress")
    Single<TencentCloudRestResponse<CustomerMakeRsp>> getdetectprogress(@Body TencentCloudRestRequest<GetprogressReq> request);


}
