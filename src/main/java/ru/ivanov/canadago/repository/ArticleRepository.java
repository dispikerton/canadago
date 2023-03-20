package ru.ivanov.canadago.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.ivanov.canadago.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
  List<Article> findAllByOrderByCreatedAtDesc();
}
