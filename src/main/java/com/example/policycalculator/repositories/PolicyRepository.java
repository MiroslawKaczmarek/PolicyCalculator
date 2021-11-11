package com.example.policycalculator.repositories;

import com.example.policycalculator.models.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
// policy repository - instead DB are used static objects in memory
public class PolicyRepository {

    private static List<Policy> allPolicies;
    static {
        allPolicies = new ArrayList<>();

        //in all constructors is added ID, but in case real database this should not be done
        PolicySubObject pso1 = new PolicySubObject(1L, "TV", BigDecimal.valueOf(200), "FIRE");
        PolicySubObject pso2 = new PolicySubObject(2L, "Computer", BigDecimal.valueOf(300), "FIRE");
        PolicySubObject pso3 = new PolicySubObject(3L, "TV", BigDecimal.valueOf(50), "THEFT");
        PolicySubObject pso4 = new PolicySubObject(4L, "Computer", BigDecimal.valueOf(52.51), "THEFT");
        PolicyObject po1 = new PolicyObject(1L, "House", Arrays.asList(pso1,pso2,pso3,pso4));
        Policy policy1 = new Policy(1L, "PolicyNumber-XB-111", PolicyStatus.APPROVED, Arrays.asList(po1));
        allPolicies.add(policy1);

        PolicySubObject pso5 = new PolicySubObject(5L, "Radio", BigDecimal.valueOf(100), "FIRE");
        PolicySubObject pso6 = new PolicySubObject(6L, "Radio", BigDecimal.valueOf(8), "THEFT");
        PolicyObject po2 = new PolicyObject(2L, "Car", Arrays.asList(pso5,pso6));
        Policy policy2 = new Policy(2L, "PolicyNumber-XB-112", PolicyStatus.REGISTERED, Arrays.asList(po2));
        allPolicies.add(policy2);

        PolicySubObject pso7 = new PolicySubObject(7L, "Dog", BigDecimal.valueOf(75), "TORNADO");
        PolicySubObject pso8 = new PolicySubObject(8L, "Cat", BigDecimal.valueOf(63), "FIRE");
        PolicyObject po3 = new PolicyObject(3L, "Animals", Arrays.asList(pso7,pso8));
        Policy policy3 = new Policy(3L, "PolicyNumber-XB-113", PolicyStatus.REGISTERED, Arrays.asList(po1,po2,po3));
        allPolicies.add(policy3);

        PolicySubObject pso9 = new PolicySubObject(9L, "Lumber", BigDecimal.valueOf(75), "abcUnknown");
        PolicyObject po4 = new PolicyObject(4L, "Store", Arrays.asList(pso9));
        Policy policy4 = new Policy(4L, "PolicyNumber-XB-114", PolicyStatus.REGISTERED, Arrays.asList(po4));
        allPolicies.add(policy4);

        PolicySubObject pso10 = new PolicySubObject(10L, "Trees", BigDecimal.valueOf(75), "FLOOD");
        PolicyObject po5 = new PolicyObject(5L, "Garten", Arrays.asList(pso10));
        Policy policy5 = new Policy(5L, "PolicyNumber-XB-115", PolicyStatus.APPROVED, Arrays.asList(po5));
        allPolicies.add(policy5);
    }

//    public List<Policy> findAll(){
//        return allPolicies;
//    }

    public Optional<Policy> findById(Long id){
        return allPolicies.stream().filter(el -> el.getId()==id).findFirst();
    }

}
