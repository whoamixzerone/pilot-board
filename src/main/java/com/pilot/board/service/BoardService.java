package com.pilot.board.service;

import com.pilot.board.domain.board.Board;
import com.pilot.board.domain.board.dto.BoardPageableResponse;
import com.pilot.board.domain.board.dto.BoardResponse;
import com.pilot.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final BoardRepository boardRepository;

    public Page<BoardPageableResponse> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable).map(BoardPageableResponse::toDto);
    }

    public BoardResponse findById(long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
        log.debug("board: {}", board);

        return BoardResponse.toDto(board);
    }
}
