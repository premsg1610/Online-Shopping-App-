package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

}
