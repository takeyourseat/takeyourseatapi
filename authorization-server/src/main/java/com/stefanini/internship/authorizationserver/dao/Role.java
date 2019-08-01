package com.stefanini.internship.authorizationserver.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name="enabled", nullable = false)
    private boolean enabled;

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}
