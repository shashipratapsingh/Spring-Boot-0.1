package com.dev.service;

import com.dev.model.Users;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    public CompletableFuture<List<Users>> saveUser(MultipartFile file) throws Exception;

    public CompletableFuture<List<Users>> findAllUsers();
}
