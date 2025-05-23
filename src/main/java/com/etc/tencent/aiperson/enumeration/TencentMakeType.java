package com.etc.tencent.aiperson.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TencentMakeType {

//    IMAGE：2D小样本-标准版（专属口型）形象定制
//    IMAGE_GENERAL：2D小样本-标准版（通用口型）形象定制
//    IMAGE_4K：2D小样本-高精版（专属口型）形象定制
//    IMAGE_PHOTO：2D小样本（照片数智人）形象定制
//    注意：选择 IMAGE_PHOTO 类型时，强烈建议预先执行人脸检测任务创建以提高成功率
//    VOICE：声音复刻（基础版）
//    ZERO_SHOT_VOICE：声音复刻（极速版）
    IMAGE("IMAGE"),
    IMAGE_GENERAL("IMAGE_GENERAL"),
    IMAGE_4K("IMAGE_4K"),
    IMAGE_PHOTO("IMAGE_PHOTO"),
    VOICE("VOICE"),
    ZERO_SHOT_VOICE("ZERO_SHOT_VOICE")
    ;
    private final String value;
    TencentMakeType(final String value) {
        this.value = value;
    }
    @JsonValue
    public String value() {
        return value;
    }

}
