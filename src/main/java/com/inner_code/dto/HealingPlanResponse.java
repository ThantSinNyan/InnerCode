package com.inner_code.dto;
import lombok.Data;
import java.util.List;

@Data
public class HealingPlanResponse {
    private List<PlanItem> plan;

    @Data
    public static class PlanItem {
        private String overview;
        private String activity;
        private List<String> prompts;
        private String meditation;
    }
}