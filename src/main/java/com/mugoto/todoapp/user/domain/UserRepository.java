package com.mugoto.todoapp.user.domain;

import com.mugoto.todoapp.shared.exceptions.EtAuthException;

import java.util.UUID;

public interface UserRepository {
    UUID create(String username, String email, String password) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer countByEmail(String email);

    User findById(UUID userId);
}
