package com.devjoemar.OnlineStoreApi.repository;

import com.devjoemar.OnlineStoreApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
