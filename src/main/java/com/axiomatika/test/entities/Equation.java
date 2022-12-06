package com.axiomatika.test.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Equation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "a")
    private Double a;
    @Column(name = "b")
    private Double b;
    @Column(name = "c")
    private Double c;
    @OneToOne(mappedBy = "equation")
    @JsonIgnore
    private Solution solution;
}
