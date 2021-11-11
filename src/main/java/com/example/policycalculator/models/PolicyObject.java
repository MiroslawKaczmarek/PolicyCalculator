package com.example.policycalculator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "policy_object")
@NoArgsConstructor
@AllArgsConstructor
public class PolicyObject implements Serializable {

    private static final long serialVersionUID = -3442876734863637806L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 40)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "policy_object_id", referencedColumnName = "id")
    @OrderBy("id")
    private List<PolicySubObject> policySubObjects = new ArrayList<>();



}
