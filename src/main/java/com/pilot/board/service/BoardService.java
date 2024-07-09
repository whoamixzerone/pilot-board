package com.pilot.board.service;

import com.pilot.board.domain.board.BoardPageableResponse;
import com.pilot.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<BoardPageableResponse> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable).map(BoardPageableResponse::toDto);
    }
}
