package ru.ivanov.canadago.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.ivanov.canadago.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
