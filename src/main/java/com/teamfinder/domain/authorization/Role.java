package com.teamfinder.domain.authorization;

import com.teamfinder.domain.authorization.enums.SystemRole;
import lombok.Getter;

import javax.persistence.*;


@Entity
@Table(name = "ROLE")
@Getter
public class Role {

    private final static String GENERATOR_NAME = "SEQUENCE_GENERATOR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false,length = 50)
    @Enumerated(EnumType.STRING)
    private SystemRole name;

    public Role() {
    }

    public Role(SystemRole name) {
        this.name = name;
    }
}