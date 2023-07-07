package com.mihnea.springboot3.c2.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<UserAccount, Long> {}
