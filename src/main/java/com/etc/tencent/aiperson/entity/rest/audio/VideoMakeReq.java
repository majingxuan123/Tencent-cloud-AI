package com.etc.tencent.aiperson.entity.rest.audio;


import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description :
 *
 * @Author JayChou
 * @Date 2025/4/30 17:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoMakeReq {
    /**
     * 虚拟人标识（必填）
     * 通过接口查询的枚举值，定义角色属性
     */
    @NotBlank
    @JsonProperty("VirtualmanKey")
    @JSONField(name = "VirtualmanKey")
    private String virtualmanKey;

    /**
     * 播报文本（必填）
     * SSML格式，最大支持20000字符
     */
    @NotBlank
    @Size(max = 20000)
    @JsonProperty("InputSsml")
    @JSONField(name = "InputSsml")
    private String inputSsml;

    @Valid
    @NotNull
    @JsonProperty("SpeechParam")
    @JSONField(name = "SpeechParam")
    private SpeechParam speechParam;

    @JsonProperty("VideoParam")
    @JSONField(name = "VideoParam")
    private VideoParam videoParam;

    @Size(max = 1000)
    @JsonProperty("CallbackUrl")
    @JSONField(name = "CallbackUrl")
    private String callbackUrl;

    @Builder.Default
    @JsonProperty("DriverType")
    @JSONField(name = "DriverType")
    private DriverTypeEnum driverType = DriverTypeEnum.TEXT;

    @JsonProperty("InputAudioUrl")
    @JSONField(name = "InputAudioUrl")
    private String inputAudioUrl;

    @JsonProperty("VideoStorageS3Url")
    @JSONField(name = "VideoStorageS3Url")
    private String videoStorageS3Url;

    @JsonProperty("SubtitleStorageS3Url")
    @JSONField(name = "SubtitleStorageS3Url")
    private String subtitleStorageS3Url;

    @JsonProperty("ConcurrencyType")
    @JSONField(name = "ConcurrencyType")
    private ConcurrencyTypeEnum concurrencyType;

    /**
     * 驱动类型枚举
     */
    public enum DriverTypeEnum {
        TEXT("Text"),
        ORIGINAL_VOICE("OriginalVoice"),
        MODULATED_VOICE("ModulatedVoice");

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
     * 并发类型枚举
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
         * 语速（必填）
         */
        @NotNull
        @DecimalMin("0.5") @DecimalMax("1.5")
        @JsonProperty("Speed")
        @JSONField(name = "Speed")
        private Float speed;

        @JsonProperty("TimbreKey")
        @JSONField(name = "TimbreKey")
        private String timbreKey;

        @Min(0) @Max(10)
        @JsonProperty("Volume")
        @JSONField(name = "Volume")
        private Integer volume;

        @JsonProperty("EmotionCategory")
        @JSONField(name = "EmotionCategory")
        private String emotionCategory;

        @Min(50) @Max(200)
        @JsonProperty("EmotionIntensity")
        @JSONField(name = "EmotionIntensity")
        private Integer emotionIntensity;

        @JsonProperty("TimbreLanguage")
        @JSONField(name = "TimbreLanguage")
        private String timbreLanguage;

        // 在SpeechParam类中添加
        public boolean isVolumeEditable() {
            return timbreKey == null
                    || (!timbreKey.startsWith("male_")
                    && !timbreKey.startsWith("female_"));
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoParam {
        @Builder.Default
        @JsonProperty("Format")
        @JSONField(name = "Format")
        private VideoFormatEnum format = VideoFormatEnum.TRANSPARENT_WEBM;

        /**
         * 视频格式枚举
         */
        public enum VideoFormatEnum {
            TRANSPARENT_WEBM("TransparentWebm"),
            TRANSPARENT_MOV("TransparentMov"),
            MP4("Mp4");

            private final String value;

            VideoFormatEnum(String value) {
                this.value = value;
            }

            @JsonValue
            public String getValue() {
                return value;
            }
        }
    }
    // 在VirtualmanRequest类中添加
    public boolean isAudioDriver() {
        return DriverTypeEnum.ORIGINAL_VOICE.equals(driverType)
                || DriverTypeEnum.MODULATED_VOICE.equals(driverType);
    }
}
