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
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit2 API interface
 */
public interface TencentAiPersonApi {

    /**
     * 获取上传临时令牌
     * https://cloud.tencent.com/document/product/1240/96067
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/assetmanager/customservice/getuploadcredentials")
    Single<TencentCloudRestResponse<GetTempTokenRsp>> getuploadcredentials(@Body TencentCloudRestRequest request);


    /**
     * 定制接口
     * https://cloud.tencent.com/document/product/1240/96069
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/assetmanager/customservice/make")
    Single<TencentCloudRestResponse<CustomerMakeRsp>> make(@Body TencentCloudRestRequest<CustomerMakeReq> request);


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
     * https://cloud.tencent.com/document/product/1240/102665
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/assetmanager/customse rvice/customerconfirm")
    Single<TencentCloudRestResponse> customerconfirm(@Body TencentCloudRestRequest<CustomerconfirmReq> request);


    /**
     * 人脸检测任务创建
     * https://cloud.tencent.com/document/product/1240/109990
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/facedetectserver/facedetectservice/facedetect")
    Single<TencentCloudRestResponse<CustomerMakeRsp>> facedetect(@Body TencentCloudRestRequest<ImageCheckReq> request);

    /**
     * 人脸检测任务状态查询
     * https://cloud.tencent.com/document/product/1240/109991
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/facedetectserver/facedetectservice/getfacedetectstate")
    Single<TencentCloudRestResponse<TaskStateResponseRsp>> getfacedetectstate(@Body TencentCloudRestRequest<GetprogressReq> request);


    // ===============下面开始是很多公共部分的接口 ========================
    /**
     * 人脸检测任务状态查询
     * https://cloud.tencent.com/document/product/1240/99703
     *
     * @param request
     * @return
     */
    @POST("/v2/ivh/crmserver/customerassetservice/describetimbre")
    Single<TencentCloudRestResponse<DescribetimbreRsp>> describetimbre(@Body TencentCloudRestRequest<DescribetimbreReq> request);

}
