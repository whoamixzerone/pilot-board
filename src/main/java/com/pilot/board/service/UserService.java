package com.pilot.board.service;

import com.pilot.board.domain.user.User;
import com.pilot.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User signUp(final User user) {
        return userRepository.save(user);
    }
}
