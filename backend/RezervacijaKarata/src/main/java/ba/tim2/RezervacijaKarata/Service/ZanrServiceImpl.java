package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Entity.Zanr;
import ba.tim2.RezervacijaKarata.ErrorHandling.NePostojiException;
import ba.tim2.RezervacijaKarata.Repository.FilmRepository;
import ba.tim2.RezervacijaKarata.Repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZanrServiceImpl implements ZanrService {
    @Autowired
    private ZanrRepository zanrRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<Zanr> getSviZanrovi() {
        return zanrRepository.findAll();
    }

    @Override
    public ResponseEntity postaviFilmZaZanr(int id, List<Zanr> zanroviZaFilm) {
        Film f = filmRepository.findByID(id);
        if (f != null) {
            f.setZanrovi(zanroviZaFilm);
            for (int i = 0; i < zanroviZaFilm.size(); i++) {
                zanroviZaFilm.get(i).dodajFilm(f);
                zanrRepository.save(zanroviZaFilm.get(i));
            }
            filmRepository.save(f);
        } else {
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
        return new ResponseEntity(f, HttpStatus.OK);
    }
}
