package dev.jpedrosnts.portifolio.controller;

import dev.jpedrosnts.portifolio.dto.ContatoTemplateForm;
import dev.jpedrosnts.portifolio.dto.SobreMimTemplateForm;
import dev.jpedrosnts.portifolio.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/template")
public class TemplateController {
    private final TemplateService service;

    @Autowired
    public TemplateController(TemplateService service) {
        this.service = service;
    }


    @GetMapping
    public String template() {
        return "template/index";
    }

    @GetMapping("/sobre-mim")
    public String sobreMim(SobreMimTemplateForm sobreMimTemplateForm) {
        service.listarSobreMim(sobreMimTemplateForm);
        return "template/sobreMim";
    }

    @GetMapping("/contato")
    public String contato(ContatoTemplateForm contatoTemplateForm) {
        service.listarContatos(contatoTemplateForm);
        return "template/contato";
    }

    @PostMapping("/sobre-mim")
    public String sobreMimPost(@Valid SobreMimTemplateForm form, BindingResult result) {
        return service.atualizarSobreMim(form, result);
    }

    @PostMapping("/contato")
    public String contatoPost(@Valid ContatoTemplateForm form, BindingResult result) {
        return service.atualizarContato(form, result);
    }

}
