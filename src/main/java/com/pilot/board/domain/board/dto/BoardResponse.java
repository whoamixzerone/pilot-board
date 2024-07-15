package com.pilot.board.domain.board.dto;

import com.pilot.board.domain.board.Board;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponse {

    @NotNull
    private Long id;
    @NotEmpty
    private String title;
    private String content;
    @NotEmpty
    private String name;
    private LocalDateTime createdAt;

    @Builder
    public BoardResponse(Long id, String title, String content, String name, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static BoardResponse toDto(final Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .name(board.getUser().getName())
                .createdAt(board.getCreatedAt())
                .build();
    }
}
