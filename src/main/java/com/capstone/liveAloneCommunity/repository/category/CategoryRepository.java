package com.capstone.liveAloneCommunity.repository.category;

import com.capstone.liveAloneCommunity.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle_Title(String title);
}