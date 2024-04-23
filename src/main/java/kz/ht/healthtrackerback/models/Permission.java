package kz.ht.healthtrackerback.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permission {

    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write");

    @Getter
    private final String permission;
}