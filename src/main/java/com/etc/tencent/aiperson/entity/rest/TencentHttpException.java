package com.etc.tencent.aiperson.entity.rest;

public class TencentHttpException extends RuntimeException {

    private final TencentCloudRestError okxRestError;

    public TencentHttpException(TencentCloudRestError okxRestError, String message, Throwable cause) {
        super(message, cause);
        this.okxRestError = okxRestError;
    }

}
