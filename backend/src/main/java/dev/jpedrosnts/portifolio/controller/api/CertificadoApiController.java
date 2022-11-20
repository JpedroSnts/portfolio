package dev.jpedrosnts.portifolio.controller.api;

import dev.jpedrosnts.portifolio.dto.CertificadoDto;
import dev.jpedrosnts.portifolio.repository.CertificadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certificado")
public class CertificadoApiController {
    private final CertificadoRepository repository;

    @Autowired
    public CertificadoApiController(CertificadoRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificadoDto>> listar() {
        return ResponseEntity.ok().body(CertificadoDto.toDto(repository.findAll()));
    }

    @GetMapping(value = "/tecnologia/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificadoDto>> listarPorTecnologia(@PathVariable UUID id) {
        return ResponseEntity.ok().body(CertificadoDto.toDto(repository.findCertificadoByTecnologiaId(id)));
    }
}
