package com.etc.tencent.aiperson.entity.rest.voice;


import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IRestEntity;
import com.etc.tencent.aiperson.entity.rest.ITencentCloudRestRsp;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 *
 * @Author JayChou
 * @Date 2025/4/30 16:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDetectProgressRsp implements IRestEntity, ITencentCloudRestRsp {

    /**
     * 音频ID
     */
    @JsonProperty("AudioId")
    @JSONField(name = "AudioId")
    private String audioId;

    /**
     * 检测code
     * 0:通过, -1:需重试, -2:需重新录制, -3:噪声过大
     */
    @JsonProperty("DetectionCode")
    @JSONField(name ="DetectionCode")
    private GetDetectProgressRsp.DetectionCodeEnum detectionCode;

    /**
     * 检测提示信息
     */
    @JsonProperty("DetectionMsg")
    @JSONField(name = "DetectionMsg")
    private String detectionMsg;

    /**
     * 任务状态
     * PROCESSING/SUCCESS/FAIL
     */
    @JsonProperty("TaskState")
    @JSONField(name = "TaskState")
    private String taskState;

    public enum DetectionCodeEnum {
        PASS(0, "通过"),
        RETRY(-1, "需要重试"),
        RE_RECORD(-2, "需要重新录制"),
        NOISE(-3, "背景噪声过大");

        private final int code;
        private final String desc;

        DetectionCodeEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        // getters...
    }
}
