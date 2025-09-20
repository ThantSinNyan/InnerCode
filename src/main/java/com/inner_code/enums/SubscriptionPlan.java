package com.inner_code.enums;

public enum SubscriptionPlan {
    NONE(-1),
    FREE(1),
    MONTHLY(2),
    QUARTERLY(3),
    YEARLY(4);

    private final int value;

    SubscriptionPlan(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SubscriptionPlan fromValue(int value) {
        for (SubscriptionPlan plan : values()) {
            if (plan.value == value) {
                return plan;
            }
        }
       return SubscriptionPlan.NONE;
    }
}
