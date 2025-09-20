package com.inner_code.enums;

public enum AnswerStatus {
    STRONGLY_AGREE(5),
    AGREE(4),
    NEUTRAL(3),
    DISAGREE(2),
    STRONGLY_DISAGREE(1),
    NOT_ANSWER(-1);

    private final int value;

    AnswerStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AnswerStatus fromValue(int value) {
        for (AnswerStatus status : values()) {
            if (status.value == value) {
                return status;
            }
        }
       return AnswerStatus.NOT_ANSWER;
    }
}
