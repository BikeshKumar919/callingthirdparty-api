package com.example.api.Reprositories;

import com.example.api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepo extends JpaRepository<Category,Long> {
}
