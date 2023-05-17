package ba.tim2.RezervacijaKarata.Service;


import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Entity.Sjedista;
import ba.tim2.RezervacijaKarata.ErrorHandling.NePostojiException;
import ba.tim2.RezervacijaKarata.Repository.SalaRepository;
import ba.tim2.RezervacijaKarata.Repository.SjedistaRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SjedistaServiceImpl implements SjedistaService {
    @Autowired
    private SjedistaRepository sjedistaRepository;
    @Autowired
    private SalaRepository salaRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
//        var salaPrva = new Sala(3,1);
//        var salaPrva = salaRepository.findById(14).orElse(null);
//        if(salaPrva != null) {
//            repository.save(new Sjedista(1, salaPrva));
//            repository.save(new Sjedista(2, salaPrva));
//            repository.save(new Sjedista(3, salaPrva));
//            repository.save(new Sjedista(4, salaPrva));
//            repository.save(new Sjedista(5, salaPrva));
//            repository.save(new Sjedista(6, salaPrva));
//        }
    }
    public ResponseEntity spasiSjediste(Sjedista sjediste) {
        sjedistaRepository.save(sjediste);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Sjedište je uspješno dodan!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Sjedista> request = new HttpEntity<>(sjediste, headers);
        //restTemplate.postForObject("http:://localhost:8080/dodajSjedista", request, Sjedista.class);
        return new ResponseEntity(sjediste, HttpStatus.CREATED);
    }

//    public List<Sjedista> spasiSjedista(List<Sjedista> sjedista) {
//        return repository.saveAll(sjedista);
//    }

    public List<Sjedista> getSvaSjedista() {
        return sjedistaRepository.findAll();
    }

    public int getSalaIdPrekoSjedista(int id) {
        return sjedistaRepository.findSalaIdBySjedisteId(id);
    }

    public int getBrojSalePrekoSjedista(int brojSale) {
        return sjedistaRepository.findBrojSalePrekoSjedista(brojSale);
    }

    public ResponseEntity getSjedistePrekoBrojaSjedista(int broj) {
        return new ResponseEntity(sjedistaRepository.findSjedisteByBrojSjedista(broj), HttpStatus.OK);
    }
    public ResponseEntity obrisiSvaSjedista() {
        if(salaRepository.count() > 0) {
            JSONObject objekat = new JSONObject();
            salaRepository.deleteAll();
            try {
                objekat.put("message", "Sjedišta su uspješno obrisana!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //restTemplate.delete("http://localhost:8080/obrisiFilm" + id);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NePostojiException("Nema sjedišta dostupnih za brisanje");
        }
    }

    public ResponseEntity obrisiSjediste(int id) {
        if (sjedistaRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            sjedistaRepository.deleteById(id);
            try {
                objekat.put("message", "Sjedište je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //restTemplate.delete("http://localhost:8080/obrisiFilm" + id);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
    }
}
