package dev.jpedrosnts.portifolio.controller.api;

import dev.jpedrosnts.portifolio.model.Tecnologia;
import dev.jpedrosnts.portifolio.repository.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tecnologia")
public class TecnologiaApiController {

    private final TecnologiaRepository repository;

    @Autowired
    public TecnologiaApiController(TecnologiaRepository tecnologiaRepository) {
        this.repository = tecnologiaRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tecnologia>> listar() {
        return ResponseEntity.ok().body(repository.findAll());
    }
}
