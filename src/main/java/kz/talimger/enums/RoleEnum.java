package kz.talimger.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("ADMIN"),
    USER("USER"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

    private final String code;

    RoleEnum(String code) {
        this.code = code;
    }
}
