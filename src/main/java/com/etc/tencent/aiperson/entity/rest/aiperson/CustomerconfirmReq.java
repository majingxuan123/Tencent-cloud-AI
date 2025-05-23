package com.etc.tencent.aiperson.entity.rest.aiperson;


import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IOkxRestReq;
import com.etc.tencent.aiperson.entity.rest.IRestEntity;
import com.etc.tencent.aiperson.entity.rest.ITencentCloudRestRsp;
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
 * @Date 2025/4/29 10:20
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerconfirmReq implements IOkxRestReq {
    /**
     * 制作的任务 ID，携带 TaskId 访问进度查询接口 (https://cloud.tencent.com/document/product/1240/96070)
     * ，可获得制作进度和制作结果
     */
    @JSONField(name = "TaskId")
    @JsonProperty("TaskId")
    private String TaskId;

    // 操作：
    // AGREE：认同
    // REJECT：驳回
    @JSONField(name = "Operate")
    @JsonProperty("Operate")
    private String Operate;

    // 驳回原因，当 Operate:REJECT 时必填，最多300字
    @JSONField(name = "Reason")
    @JsonProperty("Reason")
    private String Reason;

}
