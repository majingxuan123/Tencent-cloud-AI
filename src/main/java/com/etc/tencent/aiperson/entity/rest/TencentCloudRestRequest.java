package com.etc.tencent.aiperson.entity.rest;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TencentCloudRestRequest<T extends IOkxRestReq> implements ITencentCloudRestObject {
    /**
     * 响应头部信息
     */
    @JSONField(name = "Header")
    @JsonProperty("Header")
    private TencentCloudRestRequest.Header header;

    @JSONField(name = "Payload")
    @JsonProperty("Payload")
    private T data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Header {
        /**
         * 请求唯一标识
         */
        @JSONField(name = "RequestID")
        @JsonProperty("RequestID")
        private String requestId;

        /**
         * 会话唯一标识
         */
        @JSONField(name = "SessionID")
        @JsonProperty("SessionID")
        private String sessionId;

        /**
         * 响应状态码 (0表示成功)
         */
        @JSONField(name = "Code")
        @JsonProperty("Code")
        private int code;

        /**
         * 响应消息描述
         */
        @JSONField(name = "Message")
        @JsonProperty("Message")
        private String message;
    }

}
