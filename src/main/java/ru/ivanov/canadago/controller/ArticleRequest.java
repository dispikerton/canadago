package ru.ivanov.canadago.controller;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ArticleRequest {
  private Long id;
  private String title;
  private String content;
  private List<UUID> imageIds;
}

