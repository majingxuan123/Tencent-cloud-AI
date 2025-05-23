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
 * @Date 2025/4/29 16:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetTextReq implements IOkxRestReq {

    /**
     * 待检测音频URL
     * 支持格式：wav、mp3、m4a、aac、wma、asf
     * chi-中文普通话，
     * eng-英文，
     * can-粤语
     */
    @JSONField(name = "Language")
    @JsonProperty("Language")
    private GetTextLanguage language;

    public enum GetTextLanguage {
        chi,eng,can
    }

}
