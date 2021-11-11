package com.example.policycalculator.services;

import com.example.policycalculator.exceptions.ElementNotFoundInDatabaseException;
import com.example.policycalculator.exceptions.PolicyStrategyLogicException;
import com.example.policycalculator.models.Policy;
import com.example.policycalculator.models.PolicyStrategy;
import com.example.policycalculator.repositories.PolicyStrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PolicyCalculator {

    private final PolicyStrategyRepository policyStrategyRepository;

    public PolicyCalculator(PolicyStrategyRepository policyStrategyRepository) {
        this.policyStrategyRepository = policyStrategyRepository;
    }

    //Calculate Policy. Result is round. For example :
    //if calculated value is 12.345   then returned value is round to 12.34
    //if calculated value is 12.34501 then returned value is round to 12.35
    public BigDecimal calculateInsurrance(Policy policy) {
        BigDecimal result = new BigDecimal(0);
        Map<String, PolicyStrategy> strategies = policyStrategyRepository.mapOfPolicyStategies();
        Map<String, BigDecimal> sumByTypesMap = groupByPoliciesSubObjectsByTypeAndSumThem(policy);
        for(String type: sumByTypesMap.keySet()) {
            PolicyStrategy policyStrategy = strategies.get(type);
            if(policyStrategy == null)
                throw new ElementNotFoundInDatabaseException("Not exist PolicyStrategy with TYPE=" + type +
                        ". This type is used in sub objects of Policy (policy ID: " + policy.getId() + ").");
            result = result.add(calculateInsurranceForSpecifiedTypeAndSum(policyStrategy, sumByTypesMap.get(type)));
        }
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    //Scan structure of policy and find all policySubObjects
    //Group it by the same TYPE and sum of all elements
    //As result return map where key is TYPE (like FIRE, THEFT, etc.) and value is insurred sum of all sub objects
    private Map<String, BigDecimal> groupByPoliciesSubObjectsByTypeAndSumThem(Policy policy) {
        Map<String, BigDecimal> sumOfSubPoliciesByType = new HashMap<>();
        policy.getPolicyObjects().stream().forEach(polObj -> {
            polObj.getPolicySubObjects().stream().forEach(polSubObj -> {
                String type = polSubObj.getType();
                BigDecimal currSum = sumOfSubPoliciesByType.get(type);
                if(currSum==null)
                    currSum = BigDecimal.valueOf(0);
                currSum = currSum.add(polSubObj.getSumInsured());
                sumOfSubPoliciesByType.put(type, currSum);
            });
        });
        return sumOfSubPoliciesByType;
    }

    //Calculate insurance for specified sum of objects and selected strategy (TYPE)
    private BigDecimal calculateInsurranceForSpecifiedTypeAndSum(PolicyStrategy policyStrategy, BigDecimal sum) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");

        try {
            Boolean conditionOk = (Boolean) engine.eval(sum.toString() + policyStrategy.getCondition());
            if(conditionOk)
                return sum.multiply(policyStrategy.getConditionalRate());
        } catch (ScriptException e) {
            throw new PolicyStrategyLogicException("Condition (" + policyStrategy.getCondition() + ") used in PolicyStrategy record (type=" +
                    policyStrategy.getType() + ") is not correct. It must be in format <operator><value> - for example: >=14.55");
        }
        return sum.multiply(policyStrategy.getNormalRate());
    }

}
