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
        movies.add(new Movie(1, "Матрица", "136", "Боевик", "Жизнь Томаса Андерсона разделена на две части: днём он — самый обычный офисный работник, получающий нагоняи от начальства, а ночью превращается в хакера по имени Нео, и нет места в сети, куда он бы не смог проникнуть. Но однажды всё меняется. Томас узнаёт ужасающую правду о реальности." , true, "card-img-0.png"));
        movies.add(new Movie(2, "Безумный макс", "136", "Боевик", "Преследуемый призраками прошлого Макс уверен, что лучший способ выжить — скитаться в одиночестве. Но он попадает в плен и вынужденно присоединяется к бунтарям, бегущим через пустыню от тирании Несмертного Джо. Они забрали у него кое-что очень ценное, и разъярённый диктатор бросает все силы в погоню за мятежниками." , false, "card-img-1.png" ));
        movies.add(new Movie(3, "Джентельмены", "136", "Драма", "Один ушлый американец ещё со студенческих лет приторговывал наркотиками, а теперь придумал схему нелегального обогащения с использованием поместий обедневшей английской аристократии и очень неплохо на этом разбогател. Другой пронырливый журналист приходит к Рэю, правой руке американца, и предлагает тому купить киносценарий, в котором подробно описаны преступления его босса при участии других представителей лондонского криминального мира — партнёра-еврея, китайской диаспоры, чернокожих спортсменов и даже русского олигарха." , false, "card-img-2.png" ));
        movies.add(new Movie(4, "Отступники", "136", "Триллер", "Два лучших выпускника полицейской академии оказались по разные стороны баррикады: один из них — агент мафии в рядах правоохранительных органов, другой — «крот», внедрённый в мафию. Каждый считает своим долгом обнаружить и уничтожить противника." , true, "card-img-3.png" ));
        movies.add(new Movie(5, "Гладиатор", "136", "Боевик", "Римская империя. Бесстрашного и благородного генерала Максимуса боготворят солдаты, а старый император Марк Аврелий безгранично доверяет ему и относится как к сыну. Однако опытный воин, готовый сразиться с любым противником в честном бою, оказывается бессильным перед коварными придворными интригами. Коммод, сын Марка Аврелия, убивает отца, который планировал сделать преемником не его, а Максимуса, и захватывает власть. Решив избавиться от опасного соперника, который к тому же отказывается присягнуть ему на верность, Коммод отдаёт приказ убить Максимуса и всю его семью. Чудом выжив, но не сумев спасти близких, Максимус попадает в плен к работорговцу, который продаёт его организатору гладиаторских боёв Проксимо. Так легендарный полководец становится гладиатором. Но вскоре ему представится шанс встретиться со своим смертельным врагом лицом к лицу." , false, "card-img-4.png" ));
        movies.add(new Movie(6, "Однажды в Голливуде", "136", "Драма", "1969 год, золотой век Голливуда уже закончился. Известный актёр Рик Далтон и его дублер Клифф Бут пытаются найти свое место в стремительно меняющемся мире киноиндустрии." , false, "card-img-5.png"));
        movies.add(new Movie(7, "Предложение", "136", "Комедия", "Главная героиня фильма – ответственная начальница, которой грозит высылка в Канаду. Ради того, чтобы избежать ссылки в край озер, героиня готова на все – даже фиктивно выскочить замуж за своего молодого ассистента..." , false, "card-img-6.png" ));
        movies.add(new Movie(8, "Малышка на миллион", "136", "Драма", "Тренеру по боксу Фрэнку Данну так и не удалось воспитать чемпиона. Он владеет спортивным залом в Лос-Анджелесе, где всё ещё проводит тренировки. Дочь не отвечает на его письма, а его лучший боец подписал контракт с другим менеджером. Неожиданно в жизни Фрэнка появляется Мэгги Фицжеральд, 31-летняя официантка, мечтающая стать боксером. Фрэнк не желает тренировать женщину, но упорство Мэгги заставляет его передумать. Впереди - их главное сражение, требующее собрать в кулак всю волю и мужество." , true, "card-img-7.png" ));
        movies.add(new Movie(9, "Ларри Краун", "136", "Комедия", "До своего увольнения приветливый и любезный Ларри Краун был преуспевающим командным руководителем крупной компании, в которой он работал после службы на флоте. Под тяжестью ипотеки и будучи в неясности от того, что делать с внезапно появившимися свободными днями, Ларри направляется в местный колледж, чтобы начать все сначала." , true, "card-img-8.png" ));
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
