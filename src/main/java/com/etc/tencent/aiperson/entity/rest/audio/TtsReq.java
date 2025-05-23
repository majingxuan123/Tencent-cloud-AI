package com.etc.tencent.aiperson.entity.rest.audio;


import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.commons.basics.annotation.beancheck.AtLeastOneNotEmpty;
import com.etc.tencent.aiperson.entity.rest.IOkxRestReq;
import com.etc.tencent.aiperson.entity.rest.IRestEntity;
import com.etc.tencent.aiperson.entity.rest.ITencentCloudRestRsp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
/**
 * Description :
 *
 * @Author JayChou
 * @Date 2025/4/30 17:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AtLeastOneNotEmpty(fields = {"virtualmanKey", "timbreKey"}, message = "虚拟人标识和音色key不能同时为空")
public class TtsReq implements IOkxRestReq {
    /**
     * 音色key（与VirtualmanKey二选一）
     */
    @JsonProperty("TimbreKey")
    @JSONField(name = "TimbreKey")
    private String timbreKey;

    /**
     * 虚拟人标识（与TimbreKey二选一）
     */
    @JsonProperty("VirtualmanKey")
    @JSONField(name = "VirtualmanKey")
    private String virtualmanKey;

    /**
     * 播报文本（必填）
     */
    @NotBlank
    @Size(max = 20000)
    @JsonProperty("InputSsml")
    @JSONField(name = "InputSsml")
    private String inputSsml;

    /**
     * 语速（必填）
     */
    @NotNull
    @DecimalMin("0.5") @DecimalMax("1.5")
    @JsonProperty("Speed")
    @JSONField(name = "Speed")
    private Float speed;

    @JsonProperty("AudioStorageS3Url")
    @JSONField(name = "AudioStorageS3Url")
    private String audioStorageS3Url;

    @Builder.Default
    @JsonProperty("SampleRate")
    @JSONField(name = "SampleRate")
    private Integer sampleRate = 24000;

    @Builder.Default
    @JsonProperty("Codec")
    @JSONField(name = "Codec")
    private CodecEnum codec = CodecEnum.MP3;

    @JsonProperty("SentenceMaxWords")
    @JSONField(name = "SentenceMaxWords")
    private Integer sentenceMaxWords;

    @JsonProperty("SentenceDisplayPunctuation")
    @JSONField(name = "SentenceDisplayPunctuation")
    private String sentenceDisplayPunctuation;

    @JsonProperty("SentenceSplitPunctuation")
    @JSONField(name = "SentenceSplitPunctuation")
    private String sentenceSplitPunctuation;

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

    /**
     * 音频格式枚举
     */
    public enum CodecEnum {
        MP3("mp3"),
        WAV("wav");

        private final String value;

        CodecEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }

    /**
     * 分句配置（可选扩展）
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SentenceConfig {
        @Min(0) @Max(999)
        private Integer maxWords;
        private String displayPunctuation;
        private String splitPunctuation;
    }

    /**
     * 音色校验逻辑
     */
    @AssertTrue(message = "基础音色不支持音量调节")
    public boolean isVolumeValid() {
        if (volume == null || volume == 0) return true;
        return timbreKey == null
                || (!timbreKey.startsWith("male_1-20")
                && !timbreKey.startsWith("female_1-23"));
    }

}
