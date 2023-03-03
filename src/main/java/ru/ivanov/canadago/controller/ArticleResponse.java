package ru.ivanov.canadago.controller;

import java.util.List;

import lombok.Data;
import ru.ivanov.canadago.model.Image;

@Data
public class ArticleResponse {
  private Long id;
  private String title;
  private String content;
  private List<Image> images;
}
