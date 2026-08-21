package org.iplacex.proyectos.discografia.discos;


import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DiscoController {
    
    @Autowired
    private IDiscoRepository discoRepo;

    @Autowired
    private IArtistaRepository artistaRepo;

    //metodo Post ( crea)
    @PostMapping(
        value = "/disco",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandlePostDiscoRequest(@RequestBody Disco disco){
        if (!artistaRepo.existsById(disco.idArtista)){
            return new ResponseEntity<>("El id del artista no existe", HttpStatus.BAD_REQUEST);
        }
        Disco nuevoDisco = discoRepo.save(disco);
        return new ResponseEntity<>(nuevoDisco, HttpStatus.CREATED);
    }

    //metodo get todos
    @GetMapping(
        value = "/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        List<Disco> discos = discoRepo.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK); 
    }


    //metodo get un registro por id
    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleGetDiscoRequest(@PathVariable("id") String id){
        Optional<Disco> disco = discoRepo.findById(id);
        if(!disco.isPresent()) {
            return new ResponseEntity<>("Disco no encontrado", HttpStatus.NOT_FOUND); 
        }
        return new ResponseEntity<>(disco.get(), HttpStatus.OK);
    }

    //metodo get por artista
    @GetMapping(
        value = "/artista/{id}/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String id) {
        List<Disco> discos = discoRepo.findDiscosByIdArtista(id);
        return new ResponseEntity<>(discos, HttpStatus.OK);  
    }

}
