package com.example.policycalculator.controllers;

import com.example.policycalculator.models.Policy;
import com.example.policycalculator.services.PolicyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping({"/policy/{policyId}/calculate"})
    @ApiOperation(value = "method:calculate", notes="Calculate policy for specified policy")
    public BigDecimal calculate(@ApiParam(value="Long (required)")
                                @PathVariable  long policyId) {

        log.info("calculate policy (id: " + policyId + ")");
        BigDecimal result = policyService.calculate(policyId);
        return result;
    }

    @GetMapping({"/policy/{policyId}"})
    @ApiOperation(value = "method:getPolicy", notes="Get details about policy")
    public Policy getPolicy(@ApiParam(value="Long (required)")
                            @PathVariable  long policyId) {

        log.info("getPolicy (id: " + policyId + ")");
        Policy result = policyService.getPolicy(policyId);
        return result;
    }


}
