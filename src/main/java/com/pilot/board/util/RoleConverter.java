package com.pilot.board.util;

import com.pilot.board.domain.user.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getCode();
    }

    @Override
    public Role convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Role.ofCode(code);
    }
}
