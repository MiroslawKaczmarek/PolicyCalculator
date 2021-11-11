package com.example.policycalculator.services;

import com.example.policycalculator.models.Policy;
import com.example.policycalculator.models.PolicyObject;
import com.example.policycalculator.models.PolicyStatus;
import com.example.policycalculator.models.PolicySubObject;
import com.example.policycalculator.repositories.PolicyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.example.policycalculator.repositories.PolicyStrategyRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mockito;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
public class PolicyServiceTest {

    @Inject
    PolicyStrategyRepository policyStrategyRepository;

    @Test
    public void calculateWithMockedRepositoryAndChangedData() {
        PolicyRepository policyRepositoryMocked = buildPolicyRepository();
        PolicyCalculator policyCalculator = new PolicyCalculator(policyStrategyRepository);
        PolicyService subject = new PolicyService(policyRepositoryMocked, policyCalculator);
        BigDecimal result = subject.calculate(100L);
        Assertions.assertEquals(BigDecimal.valueOf(159.39), result);
    }

    private PolicyRepository buildPolicyRepository() {
        PolicyRepository policyRepositoryMocked = Mockito.mock(PolicyRepository.class);
        PolicySubObject pso1 = new PolicySubObject(1L, "Pen", BigDecimal.valueOf(10), "FIRE");
        PolicySubObject pso2 = new PolicySubObject(2L, "Table", BigDecimal.valueOf(200), "FIRE");
        PolicySubObject pso3 = new PolicySubObject(3L, "Gold", BigDecimal.valueOf(3000), "THEFT");
        PolicyObject po1 = new PolicyObject(1L, "House", Arrays.asList(pso1,pso2,pso3));
        PolicySubObject pso4 = new PolicySubObject(4L, "Desk1", BigDecimal.valueOf(150), "FIRE");
        PolicySubObject pso5 = new PolicySubObject(5L, "Desk1", BigDecimal.valueOf(150), "TORNADO");
        PolicyObject po2 = new PolicyObject(2L, "Office", Arrays.asList(pso4,pso5));
        Policy p1 = new Policy(100L, "PolicyNumber-XB-111", PolicyStatus.APPROVED, Arrays.asList(po1,po2));
        Optional<Policy> optionalPolicy = Optional.of(p1);
        Mockito.when(policyRepositoryMocked.findById(Mockito.anyLong())).thenReturn(optionalPolicy);
        return policyRepositoryMocked;
    }
}
