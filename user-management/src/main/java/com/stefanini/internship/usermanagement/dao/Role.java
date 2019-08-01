package com.stefanini.internship.usermanagement.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;
    private String name;
    private boolean enabled;

    //region Constructors
    public Role(String name) {
        this.name = name;
    }

    public Role(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    //endregion

    //region Getters and Setters
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
