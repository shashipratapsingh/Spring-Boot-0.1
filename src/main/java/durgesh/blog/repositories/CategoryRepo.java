package durgesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import durgesh.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
