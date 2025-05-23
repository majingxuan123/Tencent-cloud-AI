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
 * @Date 2025/4/29 16:28
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageCheckReq  implements IOkxRestReq {

    /**
     * 请求唯一标识ID
     * 示例值：569ae497-f266-4565-93bd-93aaf822524e
     */
    @JSONField(name = "RequestId")
    @JsonProperty("RequestId")
    private String RequestId;

    /**
     * 待检测图片URL
     */
    @JSONField(name = "ImageUrl")
    @JsonProperty("ImageUrl")
    private String ImageUrl;

    /**
     * 检测版本 [1,2]
     * 默认值：1
     * 1-检查比例并返回人脸信息
     * 2-返回裁剪建议及遮挡比例
     */
    @JSONField(name = "CheckerVersion")
    @JsonProperty("CheckerVersion")
    private Integer CheckerVersion;

}
