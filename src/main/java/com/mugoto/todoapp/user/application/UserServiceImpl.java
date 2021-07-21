package com.mugoto.todoapp.user.application;

import com.mugoto.todoapp.user.domain.User;
import com.mugoto.todoapp.shared.exceptions.EtAuthException;
import com.mugoto.todoapp.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if (email != null) {
            email = email.toLowerCase();
        }

        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User createUser(String username, String email, String password, String repeatPassword) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if (email != null) {
            email.toLowerCase();
        }

        if (!pattern.matcher(email).matches()) {
            throw new EtAuthException("Invalid email format");
        }

        Integer count = userRepository.countByEmail(email);

        if (count > 0) {
            throw new EtAuthException("Email already in use");
        }

        UUID userId = userRepository.create(username, email, password);

        return userRepository.findById(userId);
    }
}
