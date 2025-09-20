package com.inner_code.enums;

public enum SubscriptionPlan {
    NONE("-1"),
    FREE("1"),
    MONTHLY("2"),
    QUARTERLY("3"),
    YEARLY("4");

    private final String value;

    SubscriptionPlan(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SubscriptionPlan fromValue(String value) {
        for (SubscriptionPlan plan : values()) {
            if (plan.value.equals(value)) {
                return plan;
            }
        }
        throw new IllegalArgumentException("Invalid SubscriptionPlan value: " + value);
    }
}
