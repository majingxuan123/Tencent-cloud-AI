package com.etc.tencent.aiperson.entity.rest.aiperson;

import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.ITencentCloudRestRsp;
import com.etc.tencent.aiperson.entity.rest.IRestEntity;
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
 * @Date 2025/4/23 14:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetTempTokenRsp implements IRestEntity, ITencentCloudRestRsp {

    /**
     * 临时证书信息
     */
    @JSONField(name = "Credentials")
    @JsonProperty("Credentials")
    private Credentials credentials;

    /**
     * 证书过期时间（Unix时间戳，秒级）
     */
    @JSONField(name = "ExpiredTime")
    @JsonProperty("ExpiredTime")
    private int expiredTime;

    /**
     * COS存储路径前缀
     */
    @JSONField(name = "PathPrefix")
    @JsonProperty("PathPrefix")
    private String pathPrefix;

    /**
     * 证书生效时间（Unix时间戳，秒级）
     */
    @JSONField(name = "StartTime")
    @JsonProperty("StartTime")
    private int startTime;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Credentials {
        /**
         * 临时安全证书ID
         */
        @JSONField(name = "TmpSecretId")
        @JsonProperty("TmpSecretId")
        private String tmpSecretId;

        /**
         * 临时安全证书密钥
         */
        @JSONField(name = "TmpSecretKey")
        @JsonProperty("TmpSecretKey")
        private String tmpSecretKey;

        /**
         * 安全令牌
         */
        @JSONField(name = "Token")
        @JsonProperty("Token")
        private String token;
    }


}
