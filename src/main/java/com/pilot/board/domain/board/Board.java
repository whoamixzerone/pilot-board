package com.pilot.board.domain.board;

import com.pilot.board.domain.BaseEntity;
import com.pilot.board.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity(name = "board")
public class Board extends BaseEntity {

    @NotNull
    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
