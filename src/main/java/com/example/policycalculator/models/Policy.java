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
@Table(name = "policy")
@NoArgsConstructor
@AllArgsConstructor
public class Policy implements Serializable {

    private static final long serialVersionUID = -3442876734863637806L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 40)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PolicyStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "policy_id", referencedColumnName = "id")
    @OrderBy("id")
    private List<PolicyObject> policyObjects = new ArrayList<>();

}
