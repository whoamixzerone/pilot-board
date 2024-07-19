package com.pilot.board.domain.user;

import com.pilot.board.domain.BaseEntity;
import com.pilot.board.util.RoleConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @Convert(converter = RoleConverter.class)
    private Role role;

    @Builder
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PrePersist
    public void initRole() {
        if (role == null) {
            role = Role.ROLE_USER;
        }
    }
}
