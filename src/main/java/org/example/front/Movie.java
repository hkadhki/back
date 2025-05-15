package org.example.front;

import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
public class Movie {
    private int id;
    private String title;
    private String duration;
    private String type;
    private String description;
    private Boolean isFavorite;
    private byte[] imageBytes;

    public Movie(MovieDto movieDto) throws IOException {
        this.title = movieDto.getTitle();
        this.duration = movieDto.getDuration();
        this.type = movieDto.getType();
        this.description = movieDto.getDescription();
        this.isFavorite = movieDto.getIsFavorite();
        this.imageBytes = movieDto.getImage().getBytes();
    }

    public Movie(int id, String title, String duration, String type, String description, Boolean isFavorite, MultipartFile image) throws IOException {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.type = type;
        this.description = description;
        this.isFavorite = isFavorite;
        this.imageBytes = image.getBytes();
    }

    public Movie(int id, String title, String duration, String type, String description, Boolean isFavorite, String image) throws IOException {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.type = type;
        this.description = description;
        this.isFavorite = isFavorite;
        try (InputStream imgStream = new ClassPathResource("static/img/" + image).getInputStream()) {
            byte[] imgBytes = imgStream.readAllBytes();
            this.imageBytes = imgBytes;
        }
    }
}
