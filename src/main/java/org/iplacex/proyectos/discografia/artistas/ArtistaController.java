package org.iplacex.proyectos.discografia.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepo;

    //metodo Post (publica)
    @PostMapping(
        value = "/artista",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista nuevoArtista = artistaRepo.save(artista);
        return new ResponseEntity<>(nuevoArtista, HttpStatus.CREATED);
    }

    //metodo get todos (trae)
    @GetMapping(
        value = "/artistas",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Artista>> HandleGetAristasRequest() {
        List<Artista> artistas = artistaRepo.findAll();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    //metodo get por id (trae por id)
    @GetMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleGetArtistaRequest(@PathVariable("id") String id) {
        Optional<Artista> artista = artistaRepo.findById(id);
        if (!artista.isPresent()) {
            return new ResponseEntity<>("Artista no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(artista.get(), HttpStatus.OK);
    }

    //metodo put por id (actualiza por id)
    @PutMapping(
        value = "/artista/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleUpdateArtistaRequest(@PathVariable("id") String id, @RequestBody Artista artistaActualizado) {
        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>("Artista no encontrado para actualizar", HttpStatus.NOT_FOUND);
        }
        artistaActualizado._id = id;
        Artista artista = artistaRepo.save(artistaActualizado);
        return new ResponseEntity<>(artista, HttpStatus.OK);
    }

    //metodo delete por id (elimina por id)
    @DeleteMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleDeleteArtistaRequest(@PathVariable("id") String id) {
        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>("Artista no encontrado para eliminar", HttpStatus.NOT_FOUND);
        }
        artistaRepo.deleteById(id);
        return new ResponseEntity<>("Artista eliminado correctamente", HttpStatus.OK);
    }
}
