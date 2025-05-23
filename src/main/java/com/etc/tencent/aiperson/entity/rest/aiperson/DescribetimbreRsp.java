package com.etc.tencent.aiperson.entity.rest.aiperson;


import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IRestEntity;
import com.etc.tencent.aiperson.entity.rest.ITencentCloudRestRsp;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description :
 *
 * @Author JayChou
 * @Date 2025/5/12 17:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DescribetimbreRsp  implements IRestEntity, ITencentCloudRestRsp {

    /**
     * 声音资产列表
     */
    @JSONField(name = "TimbreAssets")
    @JsonProperty("TimbreAssets")
    private List<TimbreAssetDetail> timbreAssets;

    /**
     * 总数
     */
    @JSONField(name = "Total")
    @JsonProperty("Total")
    private Integer total;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimbreAssetDetail {

        @JSONField(name = "TimbreKey")
        @JsonProperty("TimbreKey")
        private String timbreKey;

        @JSONField(name = "TimbreName")
        @JsonProperty("TimbreName")
        private String timbreName;

        @JSONField(name = "TimbreSample")
        @JsonProperty("TimbreSample")
        private String timbreSample;

        @JSONField(name = "TimbreDesc")
        @JsonProperty("TimbreDesc")
        private String timbreDesc;

        @JSONField(name = "SupportVirtualmanTypeCodes")
        @JsonProperty("SupportVirtualmanTypeCodes")
        private List<String> supportVirtualmanTypeCodes;

        @JSONField(name = "SupportDriverTypes")
        @JsonProperty("SupportDriverTypes")
        private List<String> supportDriverTypes;

        @JSONField(name = "ExpireDate")
        @JsonProperty("ExpireDate")
        private String expireDate;

        @JSONField(name = "AudioDuration")
        @JsonProperty("AudioDuration")
        private Integer audioDuration;

        @JSONField(name = "AudioNum")
        @JsonProperty("AudioNum")
        private Integer audioNum;

        @JSONField(name = "EmotionalCategories")
        @JsonProperty("EmotionalCategories")
        private List<String> emotionalCategories;

        @JSONField(name = "TimbreGender")
        @JsonProperty("TimbreGender")
        private Integer timbreGender;

        @JSONField(name = "PurchaseType")
        @JsonProperty("PurchaseType")
        private String purchaseType;

        @JSONField(name = "TimbreType")
        @JsonProperty("TimbreType")
        private String timbreType;

        @JSONField(name = "TimbreLanguages")
        @JsonProperty("TimbreLanguages")
        private List<String> timbreLanguages;
    }

}
