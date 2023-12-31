package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Entity.Sjediste;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalaService {
    List<Sala> getSveSale();

    ResponseEntity getSaluPrekoBrojaSale(int brojSale);

    ResponseEntity spasiSalu(Sala sala);

    ResponseEntity obrisiSalu(int id);

    ResponseEntity obrisiSveSale();

    ResponseEntity postaviFilmZaSalu(int id, List<Sala> saleZaFilm);

    ResponseEntity dodajSjedisteZaSalu(int sala_id, Sjediste sjediste);
}
