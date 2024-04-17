package com.example.miniblogback.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_GUEST("ROLE_GUEST"), ROLE_USER("ROLE_USER");

    private final String role;
}
