package dev.jpedrosnts.portifolio.controller.api;

import dev.jpedrosnts.portifolio.dto.ProjetoDto;
import dev.jpedrosnts.portifolio.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projeto")
public class ProjetoApiController {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoApiController(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjetoDto>> listar() {
        List<ProjetoDto> projetoDtos = ProjetoDto.toDto(projetoRepository.findAll());
        return ResponseEntity.ok().body(projetoDtos);
    }
}
