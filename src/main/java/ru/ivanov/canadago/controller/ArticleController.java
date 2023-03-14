package ru.ivanov.canadago.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import ru.ivanov.canadago.exception.ResourceNotFoundException;
import ru.ivanov.canadago.model.Article;
import ru.ivanov.canadago.repository.ArticleRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleRepository articleRepository;

  @GetMapping("/all")
  @Operation(summary = "Получение всех статей")
  public List<Article> getArticles() {
    return articleRepository.findAll();
  }

  @PostMapping
  @Operation(summary = "Создание новой статьи")
  @Transactional
  public ResponseEntity<Article> createArticle(@RequestBody ArticleRequest articleRequest) {
    Article article = new Article();
    article.setTitle(articleRequest.getTitle());
    article.setContent(articleRequest.getContent());
    Article savedArticle = articleRepository.save(article);
    return ResponseEntity.created(URI.create("/articles/" + savedArticle.getId())).body(savedArticle);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Получение статьи по id")
  public ArticleResponse getArticleById(@PathVariable Long id) {
    Article article = articleRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Article not found"));
    ArticleResponse articleRequest = new ArticleResponse();
    articleRequest.setId(article.getId());
    articleRequest.setTitle(article.getTitle());
    articleRequest.setContent(article.getContent());
    return articleRequest;
  }

  @PutMapping
  @Operation(summary = "Редактирование статьи")
  @Transactional
  public Article updateArticle(@RequestBody ArticleRequest articleData) {
    Article article = articleRepository.findById(articleData.getId())
      .orElseThrow(() -> new ResourceNotFoundException("Article not found"));

    article.setTitle(articleData.getTitle());
    article.setContent(articleData.getContent());

    return articleRepository.save(article);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Удаление статьи")
  @Transactional
  public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
    articleRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}

