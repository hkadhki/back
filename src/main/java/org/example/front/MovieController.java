package org.example.front;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
@CrossOrigin(origins = "*")
public class MovieController {
    private List<Movie> movies;

    public MovieController() throws IOException {
        movies = new ArrayList<Movie>();
        movies.add(new Movie(1, "Матрица", "136", "Боевик", "asd" , true, "card-img-0.png"));
        movies.add(new Movie(2, "Безумный макс", "136", "Боевик", "asd" , false, "card-img-1.png" ));
        movies.add(new Movie(3, "Джентельмены", "136", "Драма", "asd" , false, "card-img-2.png" ));
        movies.add(new Movie(4, "Отступники", "136", "Триллер", "ad" , true, "card-img-3.png" ));
        movies.add(new Movie(5, "Гладиатор", "136", "Боевик", "asd" , false, "card-img-4.png" ));
        movies.add(new Movie(6, "Однажды в Голливуде", "136", "Драма", "asd" , false, "card-img-5.png"));
        movies.add(new Movie(7, "Предложение", "136", "Комедия", "asd" , false, "card-img-6.png" ));
        movies.add(new Movie(8, "Малышка на миллион", "136", "Драма", "asd" , true, "card-img-7.png" ));
        movies.add(new Movie(9, "Ларри Краун", "136", "Комедия", "asd" , true, "card-img-8.png" ));
    }


    
    @GetMapping()
    public List<Movie> getMovies() {
        return movies;
    }

    
    @GetMapping("/{id}")
    public Movie film(@PathVariable int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addFilm(
            @RequestParam("title") String title,
            @RequestParam("type") String type,
            @RequestParam("duration") String duration,
            @RequestParam("description") String description,
            @RequestParam("isFavorite") boolean isFavorite,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        movies.add(new Movie(movies.size() + 1, title, duration, type, description, isFavorite, image));
        return ResponseEntity.ok("ok");
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/update")
    public ResponseEntity<String> updateFilm(
            @RequestParam("id") int id,
            @RequestParam("title") String title,
            @RequestParam("type") String type,
            @RequestParam("duration") String duration,
            @RequestParam("description") String description,
            @RequestParam("isFavorite") boolean isFavorite,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        for (Movie m : movies) {
            if (m.getId() == id) {
                m.setTitle(title);
                m.setDescription(description);
                m.setType(type);
                m.setDuration(duration);
                m.setImageBytes(image.getBytes());
            }
        }
        for (Movie m : movies) {
            System.out.println(m.getTitle());
        }


        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable int id) {
        System.out.println("d");
        movies = movies.stream().filter(m -> m.getId() != id).collect(Collectors.toList());
        return ResponseEntity.ok("ok");
    }

    
    @PutMapping("/{id}")
    public void updateMovie(@PathVariable int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movie.setIsFavorite(!movie.getIsFavorite());
            }
        }
    }

    
    @GetMapping("/favorite")
    public List<Movie> getFavoriteMovies() {
        return movies.stream().filter(Movie::getIsFavorite).collect(Collectors.toList());
    }

}
