package dev.jpedrosnts.portifolio.controller;

import dev.jpedrosnts.portifolio.dto.form.TipoProjetoForm;
import dev.jpedrosnts.portifolio.model.Projeto;
import dev.jpedrosnts.portifolio.model.TipoProjeto;
import dev.jpedrosnts.portifolio.repository.ProjetoRepository;
import dev.jpedrosnts.portifolio.repository.TipoProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("tipos-projeto")
public class TipoProjetoController {

    private TipoProjetoRepository repository;
    private ProjetoRepository projetoRepository;

    @Autowired
    public TipoProjetoController(TipoProjetoRepository repository, ProjetoRepository projetoRepository) {
        this.repository = repository;
        this.projetoRepository = projetoRepository;
    }

    @GetMapping
    public String index(TipoProjetoForm tipoProjetoForm, Model model) {
        model.addAttribute("tiposProjeto", repository.findAll());
        return "tipoProjeto/index";
    }

    @GetMapping("cadastrar")
    public String index() {
        return "redirect:/tipos-projeto";
    }

    @PostMapping("cadastrar")
    public String cadastrar(@Valid TipoProjetoForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tiposProjeto", repository.findAll());
            return "tipoProjeto/index";
        }
        repository.save(new TipoProjeto(UUID.randomUUID(), form.getNome()));
        return "redirect:/tipos-projeto";
    }

    @GetMapping("deletar/{id}")
    public String deletar(@PathVariable UUID id, Model model, TipoProjetoForm tipoProjetoForm) {
        List<Projeto> projetos = projetoRepository.findByTipoProjetoId(id);
        if (projetos.isEmpty()) {
            repository.deleteById(id);
            return "redirect:/tipos-projeto";
        }
        model.addAttribute("tiposProjeto", repository.findAll());
        model.addAttribute("erroProjetoExistente", true);
        return "/tipoProjeto/index";
    }

}
