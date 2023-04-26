package com.Zee5blog.Zee5.repostory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Zee5blog.Zee5.models.Category;
import com.Zee5blog.Zee5.models.Post;
import com.Zee5blog.Zee5.models.User;
import com.Zee5blog.Zee5.payloads.PostDto;

public interface CatagoryRepository extends JpaRepository<Category, Integer>{


}
