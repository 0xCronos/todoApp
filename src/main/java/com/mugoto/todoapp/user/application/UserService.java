package com.mugoto.todoapp.user.application;

import com.mugoto.todoapp.user.domain.User;
import com.mugoto.todoapp.shared.exceptions.EtAuthException;

public interface UserService {
    User validateUser(String email, String password) throws EtAuthException;

    User createUser(String username, String email, String password, String repeatPassword) throws EtAuthException;
}
