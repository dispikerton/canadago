package ru.ivanov.canadago.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Entity
@Table(name = "image")
@Data
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private long size;

  @Column(nullable = false)
  private byte[] data;

  @ManyToOne
  @JoinColumn(name = "article_id")
  @JsonIgnore
  private Article article;
}
