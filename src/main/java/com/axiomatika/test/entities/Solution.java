package com.axiomatika.test.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "x1")
    private Double x1;
    @Column(name = "x2")
    private Double x2;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equation_id", referencedColumnName = "id")
    private Equation equation;
}
