package com.etc.tencent.aiperson.entity.rest.aiperson;


import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IRestEntity;
import com.etc.tencent.aiperson.entity.rest.ITencentCloudRestRsp;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Description :
 *
 * @Author JayChou
 * @Date 2025/4/29 10:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskStatusResponseRsp implements IRestEntity, ITencentCloudRestRsp {

    @JSONField(name = "Status")
    @JsonProperty("Status")
    private String status;

    @JSONField(name = "FailMessage")
    @JsonProperty("FailMessage")
    private String failMessage;

    @JSONField(name = "Stage")
    @JsonProperty("Stage")
    private String stage;

    @JSONField(name = "StageId")
    @JsonProperty("StageId")
    private String stageId;

    @JSONField(name = "StageV2")
    @JsonProperty("StageV2")
    private String stageV2;

    @JSONField(name = "StatusV2")
    @JsonProperty("StatusV2")
    private String statusV2;

    @JSONField(name = "StageInfo")
    @JsonProperty("StageInfo")
    private String stageInfo;

    @JSONField(name = "AssetList")
    @JsonProperty("AssetList")
    private List<AssetInfo> assetList;

    @Data
    public static class CustomerConfirmContent {
        @JSONField(name = "TrainResult")
        @JsonProperty("TrainResult")
        private List<String> trainResult;

        @JSONField(name = "RejectReason")
        @JsonProperty("RejectReason")
        private String rejectReason;

        @JSONField(name = "TrainRecord")
        @JsonProperty("TrainRecord")
        private List<TrainRecord> trainRecord;
    }

    @Data
    public static class TrainRecord {
        @JSONField(name = "StartTime")
        @JsonProperty("StartTime")
        private String startTime;

        @JSONField(name = "Record")
        @JsonProperty("Record")
        private List<String> record;
    }

    @Data
    public static class AssetInfo {
        @JSONField(name = "VirtualmanTypeCode")
        @JsonProperty("VirtualmanTypeCode")
        private String virtualmanTypeCode;

        @JSONField(name = "VirtualmanKey")
        @JsonProperty("VirtualmanKey")
        private String virtualmanKey;

        @JSONField(name = "Resolution")
        @JsonProperty("Resolution")
        private String resolution;

        @JSONField(name = "OriginZoom")
        @JsonProperty("OriginZoom")
        private String originZoom;

        @JSONField(name = "VirtualmanResourceId")
        @JsonProperty("VirtualmanResourceId")
        private Integer virtualmanResourceId;

        @JSONField(name = "TimbreKey")
        @JsonProperty("TimbreKey")
        private String timbreKey;

        @JSONField(name = "TimbreSample")
        @JsonProperty("TimbreSample")
        private String timbreSample;

        @JSONField(name = "MakeType")
        @JsonProperty("MakeType")
        private String makeType;
    }

}
