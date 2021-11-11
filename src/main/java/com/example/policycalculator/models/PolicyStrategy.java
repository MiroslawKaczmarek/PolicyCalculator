package com.example.policycalculator.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "policy_strategy")
public class PolicyStrategy implements Serializable {

    private static final long serialVersionUID = -3442876734863637805L;

    //many parameters - can be replaced by builder later
    public PolicyStrategy(String fullname, String type, BigDecimal normalRate, BigDecimal conditionalRate, String condition){
        this.fullname = fullname;
        this.type = type;
        this.normalRate = normalRate;
        this.conditionalRate = conditionalRate;
        this.condition = condition;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 40)
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "type")  //for example FIRE, FLOOD, THEFT
    private String type;

    @Column(name = "normal_rate")
    private BigDecimal normalRate;

    @Column(name = "conditional_rate")
    private BigDecimal conditionalRate;

    @Column(name = "condition")
    private String condition;

}
