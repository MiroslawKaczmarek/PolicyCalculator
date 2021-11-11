package com.example.policycalculator.services;

import com.example.policycalculator.exceptions.ElementNotFoundInDatabaseException;
import com.example.policycalculator.models.Policy;
import com.example.policycalculator.repositories.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final PolicyCalculator policyCalculator;

    public PolicyService(PolicyRepository policyRepository, PolicyCalculator policyCalculator) {
        this.policyRepository = policyRepository;
        this.policyCalculator = policyCalculator;
    }

    public BigDecimal calculate(Long idOfPolicy) {
        Policy policy = readPolicy(idOfPolicy);
        return policyCalculator.calculateInsurrance(policy);
    }

    public Policy getPolicy(Long idOfPolicy) {
        return readPolicy(idOfPolicy);
    }

    private Policy readPolicy(Long idOfPolicy) {
        Optional<Policy> policyOptional = policyRepository.findById(idOfPolicy);
        if(!policyOptional.isPresent())
            throw new ElementNotFoundInDatabaseException("Not exist Policy with ID: " + idOfPolicy + ".");

        return policyOptional.get();
    }

}
