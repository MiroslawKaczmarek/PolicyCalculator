package com.example.policycalculator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "policy_sub_object")
@NoArgsConstructor
@AllArgsConstructor
public class PolicySubObject implements Serializable {

    private static final long serialVersionUID = -3442876734863637808L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 40)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sum_insured")
    private BigDecimal sumInsured;

    @Column(name = "type") //contains for example FIRE, THEFT, FLOOD, etc.
    private String type;   //not used enum because in case enum each new type will require potential redeploy on server

    //At the beginning I was thinking about add here currency, but this could leads to problems during counting sum.
    //If currency will be needed , the best would be add it on the top element (in Policy entity)
}
