package com.jwt.Dao;

import com.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDoa extends JpaRepository<User, String> {
}
