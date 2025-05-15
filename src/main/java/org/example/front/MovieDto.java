package org.example.front;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MovieDto {
    private String title;
    private String duration;
    private String type;
    private String description;
    private Boolean isFavorite;
    private MultipartFile image;
}
