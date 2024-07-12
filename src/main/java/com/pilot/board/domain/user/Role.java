package com.pilot.board.domain.user;

import lombok.Getter;

import java.util.Arrays;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN", 0),
    ROLE_USER("ROLE_USER", 1);

    @Getter
    private final String name;
    private final int code;

    Role(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static Role ofCode(Integer code) {
        return Arrays.stream(Role.values())
                .filter(role -> role.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("권한에 code=[%s]가 존재하지 않습니다.", code)));
    }

    public Integer getCode() {
        return code;
    }
}
