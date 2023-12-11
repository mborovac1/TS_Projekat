package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Korisnik;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KorisnikService {
    ResponseEntity getKorisnikByEmail(String email);

    ResponseEntity getUserByEmail(String email);

    ResponseEntity getKorisnikById(int id);

    ResponseEntity spasiKorisnika(Korisnik korisnik);

    List<Korisnik> getSviKorisnici();

    ResponseEntity obrisiKorisnika(int id);

    ResponseEntity azurirajKorisnika(int id, Korisnik korisnik);

    ResponseEntity azurirajPasswordKorisnika(String email);

    ResponseEntity obrisiKorisnikaPrekoMaila(String email);
}
