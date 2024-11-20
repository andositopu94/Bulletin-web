package com.example.Bulletin.Implementation.Repository;

import com.example.Bulletin.Implementation.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
