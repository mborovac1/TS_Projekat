package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Popust;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PopustService {
    List<Popust> getSviPopusti();

    ResponseEntity<Popust> getPopustByID(int id);

    ResponseEntity<Popust> spasiPopust(Popust popust);

    ResponseEntity<Popust> azurirajPopust(int id, Popust popust);

    ResponseEntity<String> obrisiPopust(int id);
}
