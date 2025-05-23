package com.etc.tencent.aiperson.entity.rest.audio;


import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IRestEntity;
import com.etc.tencent.aiperson.entity.rest.ITencentCloudRestRsp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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
public class GetProgressRsp implements IRestEntity, ITencentCloudRestRsp {
    /**
     * 制作进度（-1~100）
     * -1:失败 100:成功 (当前仅供参考)
     */
    @JsonProperty("Progress")
    @JSONField(name = "Progress")
    private Integer progress;

    @JsonProperty("MediaUrl")
    @JSONField(name = "MediaUrl")
    private String mediaUrl;

    @JsonProperty("SubtitlesUrl")
    @JSONField(name = "SubtitlesUrl")
    private String subtitlesUrl;

    /**
     * 制作状态枚举
     */
    @JsonProperty("Status")
    @JSONField(name = "Status")
    private StatusEnum status;

    @JsonProperty("ArrayCount")
    @JSONField(name = "ArrayCount")
    private Integer arrayCount;

    @JsonProperty("FailCode")
    @JSONField(name = "FailCode")
    private Integer failCode;

    @JsonProperty("FailMessage")
    @JSONField(name = "FailMessage")
    private String failMessage;

    @JsonProperty("TextTimestampResult")
    @JSONField(name = "TextTimestampResult")
    private List<Sentence> textTimestampResult;

    @JsonProperty("Duration")
    @JSONField(name = "Duration")
    private Integer duration;

    /**
     * 制作状态枚举
     */
    public enum StatusEnum {
        COMMIT("COMMIT"),
        MAKING("MAKING"),
        SUCCESS("SUCCESS"),
        FAIL("FAIL");

        private final String value;

        StatusEnum(String value) {
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
    public static class Sentence {
        @JsonProperty("Sentence")
        @JSONField(name = "Sentence")
        private String sentence;

        @JsonProperty("Words")
        @JSONField(name = "Words")
        private List<Word> words;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Word {
        @JsonProperty("Word")
        @JSONField(name = "Word")
        private String word;

        @JsonProperty("StartTimestamp")
        @JSONField(name = "StartTimestamp")
        private Long startTimestamp;

        @JsonProperty("EndTimestamp")
        @JSONField(name = "EndTimestamp")
        private Long endTimestamp;
    }
}
