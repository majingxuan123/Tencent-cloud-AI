package com.etc.tencent.aiperson.entity.rest.voice;

import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IRestEntity;
import com.etc.tencent.aiperson.entity.rest.ITencentCloudRestRsp;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Description :
 *
 * @Author JayChou
 * @Date 2025/4/23 14:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetTextRsp implements IRestEntity, ITencentCloudRestRsp {
    /**
     * 临时证书信息
     */
    @JSONField(name = "Credentials")
    @JsonProperty("Credentials")
    private List<Texts> credentials;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Texts {
        @JSONField(name = "TextId")
        @JsonProperty("TextId")
        private String textId;

        @JSONField(name = "Text")
        @JsonProperty("Text")
        private String text;
    }
}
