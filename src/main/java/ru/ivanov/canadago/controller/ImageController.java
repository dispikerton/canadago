package ru.ivanov.canadago.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ResourceLoader;
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

import jakarta.annotation.Resource;


@RestController
@RequestMapping("/api/image")
public class ImageController {

  @Resource
  private ResourceLoader resourceLoader;

  @GetMapping
  public ResponseEntity<byte[]> getImage(@RequestParam String name) throws IOException {
    InputStream inputStream = resourceLoader.getResource("classpath:image/" + name).getInputStream();
    byte[] bytes = IOUtils.toByteArray(inputStream);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_JPEG);
    headers.setContentLength(bytes.length);
    return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
  }

  @PostMapping("/upload")
  public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
    try {
      String fileName = file.getOriginalFilename();
      Path path = Paths.get("src/main/resources/image/" + fileName);
      Files.write(path, file.getBytes());

      return ResponseEntity.ok(path.toString());
    } catch (IOException ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
    }
  }
}
