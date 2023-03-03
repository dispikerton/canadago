package ru.ivanov.canadago.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import ru.ivanov.canadago.model.Image;
import ru.ivanov.canadago.repository.ImageRepository;


@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {
  private final ImageRepository imageRepository;

  @GetMapping
  @Operation(summary = "Получение картинки по id")
  public ResponseEntity<byte[]> getImage(@RequestParam String id) {
    Optional<Image> imageOptional = imageRepository.findById(UUID.fromString(id));
    if (imageOptional.isPresent()) {
      Image image = imageOptional.get();
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.parseMediaType(image.getType()));
      headers.setContentLength(image.getSize());
      return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(summary = "Загрузить картинку")
  public ResponseEntity<String> handleImageUpload(@RequestParam("file") MultipartFile file) {
    try {
      Image image = new Image();
      image.setName(file.getOriginalFilename());
      image.setType(file.getContentType());
      image.setSize(file.getSize());
      image.setData(file.getBytes());
      Image save = imageRepository.save(image);
      return ResponseEntity.ok(String.valueOf(save.getId()));
    } catch (IOException ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
    }
  }
}
