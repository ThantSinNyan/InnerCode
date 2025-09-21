package com.inner_code.feignClient;

import com.inner_code.dto.HealingPlanRequest;
import com.inner_code.dto.HealingPlanResponse;
import com.inner_code.dto.HealingRequest;
import com.inner_code.dto.PersonalOverViewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "healingClient",
        url = "${healing.client.url}"
)
public interface HealingFeignClient {

    @PostMapping("/healing/generate_overview_end_user")
    PersonalOverViewDto generateOverview(@RequestBody HealingRequest request);

    @PostMapping("/healing/generate_plan")
    HealingPlanResponse generatePlan(@RequestBody HealingPlanRequest request);
}
