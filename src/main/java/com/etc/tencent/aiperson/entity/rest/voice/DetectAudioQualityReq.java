package com.etc.tencent.aiperson.entity.rest.voice;


import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IOkxRestReq;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description :
 *
 * @Author JayChou
 * @Date 2025/4/30 16:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetectAudioQualityReq implements IOkxRestReq {
    @JSONField(name = "TextId")
    @JsonProperty("TextId")
    private String textId;
    /**
     * 参考文本（可选）
     * 用于计算文本准确率指标
     */
    @JSONField(name = "ReferenceText")
    @JsonProperty("ReferenceText")
    private String referenceText;
    /**
     * 待检测音频URL
     * 支持格式：wav、mp3、m4a、aac、wma、asf
     */
    @JSONField(name = "AudioUrl")
    @JsonProperty("AudioUrl")
    private String audioUrl;

}
