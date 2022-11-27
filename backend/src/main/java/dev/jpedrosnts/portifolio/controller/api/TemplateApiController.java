package dev.jpedrosnts.portifolio.controller.api;

import dev.jpedrosnts.portifolio.dto.ContatoTemplateDto;
import dev.jpedrosnts.portifolio.dto.SobreMimTemplateDto;
import dev.jpedrosnts.portifolio.model.SobreMimTemplate;
import dev.jpedrosnts.portifolio.repository.ContatoTemplateRepository;
import dev.jpedrosnts.portifolio.repository.SobreMimTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/template")
public class TemplateApiController {
    private final ContatoTemplateRepository contatoTemplateRepository;
    private final SobreMimTemplateRepository sobreMimTemplateRepository;

    @Autowired
    public TemplateApiController(ContatoTemplateRepository contatoTemplateRepository, dev.jpedrosnts.portifolio.repository.SobreMimTemplateRepository sobreMimTemplateRepository) {
        this.contatoTemplateRepository = contatoTemplateRepository;
        this.sobreMimTemplateRepository = sobreMimTemplateRepository;
    }

    @GetMapping(value = "/contato", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContatoTemplateDto>> listarContato() {
        return ResponseEntity.ok().body(ContatoTemplateDto.toDto(contatoTemplateRepository.findAll()));
    }

    @GetMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SobreMimTemplateDto> listarHome() {
        SobreMimTemplate sobreMim = null;
        List<SobreMimTemplate> all = sobreMimTemplateRepository.findAll();
        if (all.size() > 0) {
            sobreMim = all.get(0);
            String[] nome = sobreMim.getNome().split(" ");
            sobreMim.setNome(nome[0]);
            sobreMim.setSobrenome(nome[1]);
        }
        return ResponseEntity.ok().body(new SobreMimTemplateDto(sobreMim));
    }

    @GetMapping(value = "/sobre-mim", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SobreMimTemplateDto> listarSobreMim() {
        SobreMimTemplate sobreMim = null;
        List<SobreMimTemplate> all = sobreMimTemplateRepository.findAll();
        if (all.size() > 0) {
            sobreMim = all.get(0);
        }
        return ResponseEntity.ok().body(new SobreMimTemplateDto(sobreMim));
    }
}
