package com.etc.tencent.aiperson.entity.rest.aiperson;


import com.alibaba.fastjson2.annotation.JSONField;
import com.etc.tencent.aiperson.entity.rest.IRestEntity;
import com.etc.tencent.aiperson.entity.rest.ITencentCloudRestRsp;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * @Date 2025/4/29 16:37
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskStateResponseRsp  implements IRestEntity, ITencentCloudRestRsp {

    /**
     * 任务ID
     */
    @JSONField(name = "TaskId")
    @JsonProperty("TaskId")
    private String taskId;

    /**
     * 任务状态
     * 枚举值：WAITING, PROCESSING, SUCCESS, FAILED
     */
    @JSONField(name = "TaskState")
    @JsonProperty("TaskState")
    private String taskState;

    /**
     * 错误码（仅当TaskState=FAILED时有效）
     */
    @JSONField(name = "ErrorCode")
    @JsonProperty("ErrorCode")
    private Integer errorCode;

    /**
     * 错误信息（仅当TaskState=FAILED时有效）
     */
    @JSONField(name = "ErrorMsg")
    @JsonProperty("ErrorMsg")
    private String errorMsg;

    /**
     * 是否通过人脸检测（仅当CheckerVersion=2时存在）
     */
    @JSONField(name = "IsPassDetect")
    @JsonProperty("IsPassDetect")
    private Boolean isPassDetect;

    /**
     * 人脸检测结果列表（仅当CheckerVersion=2时存在）
     */
    @JSONField(name = "Faces")
    @JsonProperty("Faces")
    private List<FaceInfo> faces;

    @Data
    public static class FaceInfo {
        /**
         * 人脸框信息
         */
        @JSONField(name = "Box")
        @JsonProperty("Box")
        private BoxInfo box;

        /**
         * 人脸旋转角度（CheckerVersion=1时存在）
         */
        @JSONField(name = "Rotation")
        @JsonProperty("Rotation")
        private Float rotation;

        /**
         * 人脸置信度（CheckerVersion=1时存在）
         */
        @JSONField(name = "Score")
        @JsonProperty("Score")
        private Float score;

        /**
         * 人脸区域占比（CheckerVersion=2时存在）
         */
        @JSONField(name = "AreaRatio")
        @JsonProperty("AreaRatio")
        private Float areaRatio;

        /**
         * 脸部遮挡比例（CheckerVersion=2时存在）
         */
        @JSONField(name = "FaceRadio")
        @JsonProperty("FaceRadio")
        private Float faceRadio;

        /**
         * 五官遮挡比例（CheckerVersion=2时存在）
         */
        @JSONField(name = "FeatureRadio")
        @JsonProperty("FeatureRadio")
        private Float featureRadio;

        /**
         * 侧脸角度（CheckerVersion=2时存在）
         */
        @JSONField(name = "Yaw")
        @JsonProperty("Yaw")
        private Float yaw;

        /**
         * 俯仰角度（CheckerVersion=2时存在）
         */
        @JSONField(name = "Pitch")
        @JsonProperty("Pitch")
        private Float pitch;

        /**
         * 最大裁剪建议区域（CheckerVersion=2时存在）
         */
        @JSONField(name = "MaxCrop")
        @JsonProperty("MaxCrop")
        private BoxInfo maxCrop;

        /**
         * 最小裁剪建议区域（CheckerVersion=2时存在）
         */
        @JSONField(name = "MinCrop")
        @JsonProperty("MinCrop")
        private BoxInfo minCrop;
    }

    @Data
    public static class BoxInfo {
        /**
         * 左上角X坐标
         */
        @JSONField(name = "X")
        @JsonProperty("X")
        private Float x;

        /**
         * 左上角Y坐标
         */
        @JSONField(name = "Y")
        @JsonProperty("Y")
        private Float y;

        /**
         * 框宽度
         */
        @JSONField(name = "Width")
        @JsonProperty("Width")
        private Float width;

        /**
         * 框高度
         */
        @JSONField(name = "Height")
        @JsonProperty("Height")
        private Float height;
    }
}
