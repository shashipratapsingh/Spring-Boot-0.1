package com.dev.service.impl;

import com.dev.model.Users;
import com.dev.repository.UserRepo;
import com.dev.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    Object target;

    Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);

    @Async
    public CompletableFuture<List<Users>> saveUser(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        List<Users> users=parseCSVFile(file);
        logger.info("saving list of users size"+users.size(),""+Thread.currentThread().getName());
        users=userRepo.saveAll(users);
        long end = System.currentTimeMillis();
        logger.info("Total time ",(end-start));

        return CompletableFuture.completedFuture(users);

    }
    private List<Users> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Users> users = new ArrayList<>();
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {

                final String[] data = line.split(",");
                final Users user = new Users();
                user.setName(data[0]);
                user.setEmail(data[1]);
                user.setGender(data[2]);
                users.add(user);

            }
            return users;
        } catch (final IOException e) {
            logger.error("failed to parse CSV file {}", e);
            throw new Exception("failed to parse CSV file{}", e);
        }
    }
    @Async
    public CompletableFuture<List<Users>> findAllUsers(){
        logger.info("get all user By "+Thread.currentThread().getName());
        List<Users> users=userRepo.findAll();
        return CompletableFuture.completedFuture(users);
    }


}
