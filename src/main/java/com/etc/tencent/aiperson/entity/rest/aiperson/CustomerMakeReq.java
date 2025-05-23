package com.etc.tencent.aiperson.entity.rest.aiperson;

import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IOkxRestReq;
import com.etc.tencent.aiperson.enumeration.TencentMakeType;
import com.etc.tencent.aiperson.enumeration.TencentSexType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;


/**
 * https://cloud.tencent.com/document/product/1240/96069
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerMakeReq implements IOkxRestReq {

    /**
     * 主播名称（2-50字符，汉字/字母/数字/下划线/横线）
     */
    @NonNull
    @JSONField(name = "AnchorName")
    @JsonProperty("AnchorName")
    private String anchorName;

    /**
     * 定制类型枚举
     */
    @NonNull
    @JSONField(name = "MakeType")
    @JsonProperty("MakeType")
    private TencentMakeType makeType;

    /**
     * 视频授权书URL（格式：.../idcard/*.mp4）
     */
    @JSONField(name = "IdentityCosUrl")
    @JsonProperty("IdentityCosUrl")
    private String identityCosUrl;

    /**
     * PDF授权书URL（格式：.../idcard/*.pdf）
     */
    @JSONField(name = "IdentityWrittenCosUrl")
    @JsonProperty("IdentityWrittenCosUrl")
    private String identityWrittenCosUrl;

    /**
     * 素材URL（根据类型不同路径不同）
     */
    @JSONField(name = "MaterialCosUrl")
    @JsonProperty("MaterialCosUrl")
    private String materialCosUrl;

    /**
     * 性别枚举
     */
    @NonNull
    @JSONField(name = "SexType")
    @JsonProperty("SexType")
    private TencentSexType sexType;

    /**
     * 定制备注信息（100字以内）
     */
    @JSONField(name = "Notes")
    @JsonProperty("Notes")
    private String notes;

    /**
     * 驱动文本内容（4-1000字符）
     */
    @JSONField(name = "TextDriver")
    @JsonProperty("TextDriver")
    private String textDriver;

    /**
     * 驱动音频文件路径（格式：.../audio/*.wav）
     */
    @JSONField(name = "VoiceDriverCosFile")
    @JsonProperty("VoiceDriverCosFile")
    private String voiceDriverCosFile;

    /**
     * 音质检测任务ID（极速版必填）
     */
    @JSONField(name = "AudioId")
    @JsonProperty("AudioId")
    private String audioId;

    /**
     * 音色模板信息
     */
    @JSONField(name = "TimbreInfo")
    @JsonProperty("TimbreInfo")
    private TimbreTemplate timbreInfo;

    /**
     * 是否保留背景（默认false）
     */
    @JSONField(name = "IsHaveBackground")
    @JsonProperty("IsHaveBackground")
    private Boolean isHaveBackground;

    /**
     * 背景处理方式（1-绿幕 2-白幕）
     */
    @JSONField(name = "RemoveBackground")
    @JsonProperty("RemoveBackground")
    private Integer removeBackground;

    /**
     * Alpha通道视频地址
     */
    @JSONField(name = "AlphaCosUrl")
    @JsonProperty("AlphaCosUrl")
    private String alphaCosUrl;

    /**
     * 极速版语言类型（chi/eng/can）
     */
    @JSONField(name = "ZeroshortLang")
    @JsonProperty("ZeroshortLang")
    private String zeroshortLang;

    /**
     * 照片版本（0-快速版 1-优享版）
     */
    @JSONField(name = "PhotoVersion")
    @JsonProperty("PhotoVersion")
    private Integer photoVersion;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimbreTemplate {
        @JSONField(name = "TimbreCode")
        @JsonProperty("TimbreCode")
        private String timbreCode;

        @JSONField(name = "Speed")
        @JsonProperty("Speed")
        private String speed;

        @JSONField(name = "IsWithTimbre")
        @JsonProperty("IsWithTimbre")
        private Boolean isWithTimbre;
    }
}
