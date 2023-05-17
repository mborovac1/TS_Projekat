package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Film;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmService {

    List<Film> getSviFilmovi();

    ResponseEntity getFilmByID(int id);

    ResponseEntity spasiFilm(Film film);

    ResponseEntity azurirajFilm(int id, Film film);

    ResponseEntity obrisiFilm(int id);
}
