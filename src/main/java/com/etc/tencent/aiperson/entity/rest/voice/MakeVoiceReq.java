package com.etc.tencent.aiperson.entity.rest.voice;

import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IOkxRestReq;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
/**
 * Description :
 *
 * @Author JayChou
 * @Date 2025/4/30 15:28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakeVoiceReq implements IOkxRestReq {

    /**
     * 主播名称（2-50字符，汉字/字母/数字/下划线/横线）
     */
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[\\u4e00-\\u9fa5a-zA-Z0-9_-]+$")
    @JSONField(name = "AnchorName")
    @JsonProperty("AnchorName")
    private String anchorName;

    /**
     * 定制类型枚举
     */
    public enum MakeType {
        IMAGE,
        IMAGE_GENERAL,
        IMAGE_4K,
        IMAGE_PHOTO,
        VOICE,
        ZERO_SHOT_VOICE
    }

    @NotNull
    @JSONField(name = "MakeType")
    @JsonProperty("MakeType")
    private MakeType makeType;

    @JSONField(name = "IdentityCosUrl")
    @JsonProperty("IdentityCosUrl")
    private String identityCosUrl;

    @JSONField(name = "IdentityWrittenCosUrl")
    @JsonProperty("IdentityWrittenCosUrl")
    private String identityWrittenCosUrl;

    @JSONField(name = "MaterialCosUrl")
    @JsonProperty("MaterialCosUrl")
    private String materialCosUrl;

    /**
     * 性别枚举
     */
    public enum SexType {
        MALE, FEMALE
    }

    @NotNull
    @JSONField(name = "SexType")
    @JsonProperty("SexType")
    private SexType sexType;

    @Size(max = 100)
    @JSONField(name = "Notes")
    @JsonProperty("Notes")
    private String notes;

    @Size(min = 4, max = 1000)
    @JSONField(name = "TextDriver")
    @JsonProperty("TextDriver")
    private String textDriver;

    @JSONField(name = "VoiceDriverCosFile")
    @JsonProperty("VoiceDriverCosFile")
    private String voiceDriverCosFile;

    @JSONField(name = "AudioId")
    @JsonProperty("AudioId")
    private String audioId;

    @JSONField(name = "TimbreInfo")
    @JsonProperty("TimbreInfo")
    private TimbreTemplate timbreInfo;

    @JSONField(name = "IsHaveBackground")
    @JsonProperty("IsHaveBackground")
    private Boolean isHaveBackground;

    @JSONField(name = "RemoveBackground")
    @JsonProperty("RemoveBackground")
    private Integer removeBackground;

    @JSONField(name = "AlphaCosUrl")
    @JsonProperty("AlphaCosUrl")
    private String alphaCosUrl;

    /**
     * 语言类型枚举
     */
    public enum ZeroshotLang {
        CHI, ENG, CAN
    }

    @JSONField(name = "ZeroshortLang")
    @JsonProperty("ZeroshortLang")
    private ZeroshotLang zeroshortLang;

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

        @DecimalMin("0.5")
        @DecimalMax("1.5")
        @JSONField(name = "Speed")
        @JsonProperty("Speed")
        private String speed;

        @JSONField(name = "IsWithTimbre")
        @JsonProperty("IsWithTimbre")
        private Boolean isWithTimbre;
    }
}
