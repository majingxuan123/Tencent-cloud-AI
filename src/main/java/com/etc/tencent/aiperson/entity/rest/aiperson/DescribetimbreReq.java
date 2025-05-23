package com.etc.tencent.aiperson.entity.rest.aiperson;


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
 * @Date 2025/5/12 14:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DescribetimbreReq implements IOkxRestReq {

    /**
     * 当前页
     */
    @JSONField(name = "PageIndex")
    @JsonProperty("PageIndex")
    private Integer pageIndex;

    /**
     * 页面大小（最大不超过100）
     */
    @JSONField(name = "PageSize")
    @JsonProperty("PageSize")
    private Integer pageSize;

    /**
     * 数智人音色 code，支持精确匹配
     */
    @JSONField(name = "TimbreKey")
    @JsonProperty("TimbreKey")
    private String timbreKey;

    /**
     * 数智人音色名称，支持模糊匹配
     */
    @JSONField(name = "TimbreName")
    @JsonProperty("TimbreName")
    private String timbreName;

    /**
     * 购买类型，Lease:租赁，Custom:定制
     */
    @JSONField(name = "PurchaseType")
    @JsonProperty("PurchaseType")
    private String purchaseType;

}
