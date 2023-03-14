package ru.ivanov.canadago.controller;

import lombok.Data;

@Data
public class ArticleResponse {
  private Long id;
  private String title;
  private String content;
}
