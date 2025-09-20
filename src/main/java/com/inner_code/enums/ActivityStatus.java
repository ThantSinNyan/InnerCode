package com.inner_code.enums;

public enum ActivityStatus {
    NOT_STARTED(-1),
    IN_PROGRESS(0),
    COMPLETED(1),
    SKIPPED(2);

    private final int code;

    ActivityStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ActivityStatus fromCode(int code) {
        for (ActivityStatus status : ActivityStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ActivityStatus code: " + code);
    }
}
