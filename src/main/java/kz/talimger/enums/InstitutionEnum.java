package kz.talimger.enums;

import lombok.Getter;

@Getter
public enum InstitutionEnum {
    SCHOOL("SCHOOL"),
    KINDERGARTEN("KINDERGARTEN");

    private final String code;

    InstitutionEnum(String code) {
        this.code = code;
    }
}
