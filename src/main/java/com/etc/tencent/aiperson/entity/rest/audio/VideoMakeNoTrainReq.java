package com.etc.tencent.aiperson.entity.rest.audio;


import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IOkxRestReq;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description :
 *
 * @Author JayChou
 * @Date 2025/4/30 17:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoMakeNoTrainReq implements IOkxRestReq {

    /**
     * 模板视频URL（必填）
     * 格式：mp4/mov/avi，大小≤5G，分辨率360-4096，时长5-600秒
     */
    @NotNull
    @JsonProperty("RefVideoUrl")
    @JSONField(name = "RefVideoUrl")
    private String refVideoUrl;

    /**
     * 驱动类型（必填）
     */
    @NotNull
    @JsonProperty("DriverType")
    @JSONField(name = "DriverType")
    private DriverTypeEnum driverType;

    @JsonProperty("IdentityWrittenUrl")
    @JSONField(name = "IdentityWrittenUrl")
    private String identityWrittenUrl;

    @JsonProperty("IdentityVideoUrl")
    @JSONField(name = "IdentityVideoUrl")
    private String identityVideoUrl;

    /**
     * 驱动音频URL（当DriverType=OriginalVoice时必填）
     * 格式：wav/mp3/wma/m4a/aac/ogg，大小≤100MB，时长≤10分钟
     */
    @JsonProperty("InputAudioUrl")
    @JSONField(name = "InputAudioUrl")
    private String inputAudioUrl;

    /**
     * 播报文本（DriverType=Text时必填）
     * SSML格式，2000字以内，需转义
     */
    @JsonProperty("InputSsml")
    @JSONField(name = "InputSsml")
    private String inputSsml;

    @JsonProperty("SpeechParam")
    @JSONField(name = "SpeechParam")
    private SpeechParam speechParam;

    @JsonProperty("ConcurrencyType")
    @JSONField(name = "ConcurrencyType")
    private ConcurrencyTypeEnum concurrencyType;

    @JsonProperty("VideoLoop")
    @JSONField(name = "VideoLoop")
    private Integer videoLoop;

    @JsonProperty("VideoParametersConsistent")
    @JSONField(name = "VideoParametersConsistent")
    private Integer videoParametersConsistent;

    @JsonProperty("CallbackUrl")
    @JSONField(name = "CallbackUrl")
    private String callbackUrl;

    /**
     * 驱动类型枚举
     */
    public enum DriverTypeEnum {
        TEXT("Text"),
        ORIGINAL_VOICE("OriginalVoice");

        private final String value;

        DriverTypeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }

    /**
     * 资源类型枚举
     */
    public enum ConcurrencyTypeEnum {
        EXCLUSIVE("Exclusive"),
        SHARED("Shared");

        private final String value;

        ConcurrencyTypeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpeechParam {
        /**
         * 语速 [0.5-1.5]
         */
        @JsonProperty("Speed")
        @JSONField(name = "Speed")
        private Float speed;

        /**
         * 音色key（必填）
         */
        @JsonProperty("TimbreKey")
        @JSONField(name = "TimbreKey")
        private String timbreKey;

        /**
         * 音量 [0-10]
         */
        @JsonProperty("Volume")
        @JSONField(name = "Volume")
        private Integer volume;

        @JsonProperty("EmotionCategory")
        @JSONField(name = "EmotionCategory")
        private String emotionCategory;

        @JsonProperty("EmotionIntensity")
        @JSONField(name = "EmotionIntensity")
        private Integer emotionIntensity;

        @JsonProperty("TimbreLanguage")
        @JSONField(name = "TimbreLanguage")
        private String timbreLanguage;
    }

}
