package com.example.policycalculator.repositories;

import com.example.policycalculator.models.PolicyStrategy;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PolicyStrategyRepository {

    // The main plan was storing policy strategies in database, storing this in database is better from potential changes point of view
    // ....add new strategy not requires redeploy new version of application.
    // Because database is not required here it is only as 'mock'.
    public Map<String, PolicyStrategy> mapOfPolicyStategies(){
        Map<String, PolicyStrategy> map = new HashMap<>();
        map.put("FIRE", new PolicyStrategy("Fire strategy", "FIRE", BigDecimal.valueOf(0.014), BigDecimal.valueOf(0.024), ">100"));
        map.put("THEFT", new PolicyStrategy("Theft strategy", "THEFT", BigDecimal.valueOf(0.11), BigDecimal.valueOf(0.05), ">=15"));
        map.put("TORNADO", new PolicyStrategy("Tornado strategy", "TORNADO", BigDecimal.valueOf(0.005), BigDecimal.valueOf(0.004), ">150"));
        map.put("FLOOD", new PolicyStrategy("Flood strategy", "FLOOD", BigDecimal.valueOf(0.018), BigDecimal.valueOf(0.05), "grater than two thousand"));
        return map;
    }


}
