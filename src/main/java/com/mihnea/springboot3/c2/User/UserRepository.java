package com.mihnea.springboot3.c2.User;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}