package com.pilot.board.domain.user;

import com.pilot.board.domain.BaseEntity;
import com.pilot.board.util.RoleConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
@Entity(name = "users")
public class User extends BaseEntity {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

//    @Enumerated(EnumType.STRING)
    @Convert(converter = RoleConverter.class)
    private Role role;

    @PrePersist
    public void initRole() {
        if (role == null) {
            role = Role.ROLE_USER;
        }
    }
}
