package ru.ivanov.canadago.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.ivanov.canadago.exception.ResourceNotFoundException;
import ru.ivanov.canadago.model.Article;
import ru.ivanov.canadago.repository.ArticleRepository;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleRepository articleRepository;

  @GetMapping
  public List<Article> getArticles() {
    return articleRepository.findAll();
  }

  @PostMapping
  public Article createArticle(@RequestBody Article article) {
    return articleRepository.save(article);
  }

  @GetMapping("/{id}")
  public Article getArticleById(@PathVariable Long id) {
    return articleRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Article not found"));
  }

  @PutMapping
  public Article updateArticle(@RequestBody Article articleData) {
    Article article = articleRepository.findById(articleData.getId())
      .orElseThrow(() -> new ResourceNotFoundException("Article not found"));

    article.setTitle(articleData.getTitle());
    article.setContent(articleData.getContent());

    return articleRepository.save(article);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
    articleRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}

