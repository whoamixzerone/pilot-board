package com.pilot.board.controller;

import com.pilot.board.domain.board.dto.BoardPageableResponse;
import com.pilot.board.domain.board.dto.BoardResponse;
import com.pilot.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    private final BoardService boardService;

    @GetMapping
    public String findAll(
            @PageableDefault(size = 25)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
            }) Pageable pageable,
            Model model
    ) {
        Page<BoardPageableResponse> boards = boardService.findAll(pageable);
        log.info("boards pageable: {}", boards.getPageable());
        log.info("boards totalPages: {}", boards.getTotalPages());
        model.addAttribute("boards", boards);

        return "board/boardList";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") long id, Model model) {
        BoardResponse boardResponse = boardService.findById(id);
        model.addAttribute("board", boardResponse);

        return "board/boardDetail";
    }
}
