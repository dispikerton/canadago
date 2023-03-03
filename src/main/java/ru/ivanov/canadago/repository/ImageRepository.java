package ru.ivanov.canadago.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.ivanov.canadago.model.Image;

public interface ImageRepository extends JpaRepository<Image, UUID> {
  List<Image> findAllByIdIn(List<UUID> imageIds);
  List<Image> findAllByArticleId(Long id);
}
