package dev.jpedrosnts.portifolio.controller;

import dev.jpedrosnts.portifolio.dto.EmailForm;
import dev.jpedrosnts.portifolio.model.Contato;
import dev.jpedrosnts.portifolio.service.ContatoService;
import dev.jpedrosnts.portifolio.util.EnviarEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    private final ContatoService service;
    private final EnviarEmailUtil emailUtil;
    private final String emailAdmin;

    @Autowired
    public ContatoController(ContatoService service, EnviarEmailUtil emailUtil, @Value("${email.admin}") String emailAdmin) {
        this.service = service;
        this.emailUtil = emailUtil;
        this.emailAdmin = emailAdmin;
    }

    @PostMapping
    public ResponseEntity<Contato> enviar(@Valid @RequestBody EmailForm form) {
        Contato contato = new Contato();
        contato.setEmail(form.getEmail());
        contato.setNome(form.getNome());
        contato.setMensagem(form.getMensagem());
        contato = service.salvar(contato);
        String assunto = "Portifólio - " + contato.getNome();
        String msg = "De: " + contato.getNome() + "\nEmail: " + contato.getEmail() + "\n\n" + contato.getMensagem();
        emailUtil.enviar(assunto, msg.toString(), new String[] {emailAdmin});
        return ResponseEntity.status(201).body(contato);
    }
}
