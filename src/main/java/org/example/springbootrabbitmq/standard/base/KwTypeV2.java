package org.example.springbootrabbitmq.standard.base;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum KwTypeV2 {
    ALL("all"),
    NAME("name"),
    OWNER("OWNER");

    private final String value;
}
