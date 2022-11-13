package dev.jpedrosnts.portifolio.controller;

import dev.jpedrosnts.portifolio.dto.AtualizarTecnologiaForm;
import dev.jpedrosnts.portifolio.dto.CadastrarTecnologiaForm;
import dev.jpedrosnts.portifolio.model.Tecnologia;
import dev.jpedrosnts.portifolio.repository.TecnologiaRepository;
import dev.jpedrosnts.portifolio.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("tecnologia")
public class TecnologiasController {

    private final FileUploadUtil fileUploadUtil;
    private final TecnologiaRepository repository;

    @Autowired
    public TecnologiasController(FileUploadUtil fileUploadUtil, TecnologiaRepository repository) {
        this.fileUploadUtil = fileUploadUtil;
        this.repository = repository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tecnologias", repository.findAll());
        return "tecnologias/index";
    }

    @GetMapping("editar/{id}")
    public String editar(AtualizarTecnologiaForm atualizarTecnologiaForm, @PathVariable UUID id, Model model) {
        Tecnologia tecnologia = repository.findById(id).orElseThrow();
        model.addAttribute(id);
        model.addAttribute("iconeLink", tecnologia.getIcone());
        atualizarTecnologiaForm.setNome(tecnologia.getNome());
        return "tecnologias/editar";
    }

    @PostMapping("editar/{id}")
    public String editarPost(@Valid AtualizarTecnologiaForm form, @PathVariable UUID id, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute(id);
            return "tecnologias/cadastrar";
        }

        Tecnologia tecnologia = repository.getById(id);
        tecnologia.setNome(form.getNome());
        String originalFilename = form.getIcone().getOriginalFilename();
        if (originalFilename != null && !originalFilename.equals("")) {
            String fileName = tecnologia.getId() + FileUploadUtil.getExtension(originalFilename);
            String urlImagem = fileUploadUtil.upload(fileName, form.getIcone().getBytes());
            tecnologia.setIcone(urlImagem);
        }
        repository.save(tecnologia);

        return "redirect:/tecnologia";
    }

    @GetMapping("cadastrar")
    public String cadastrar(CadastrarTecnologiaForm cadastrarTecnologiaForm) {
        return "tecnologias/cadastrar";
    }

    @PostMapping("cadastrar")
    public String cadastrarPost(@Valid CadastrarTecnologiaForm form, BindingResult result) throws IOException {

        FieldError fieldError;
        String originalFileName = form.getIcone().getOriginalFilename();

        if (form.getIcone().isEmpty()) {
            fieldError = new FieldError("form", "icone", "Icone não pode estar vazia");
            result.addError(fieldError);
        } else if (!FileUploadUtil.isImage(originalFileName)) {
            fieldError = new FieldError("form", "icone", "Formato não suportado. Tente: PNG, JPG, JPEG ou SVG");
            result.addError(fieldError);
        }

        if (result.hasErrors() || originalFileName == null) {
            return "tecnologias/cadastrar";
        }
        Tecnologia tecnologia = new Tecnologia();
        tecnologia.setNome(form.getNome());
        repository.save(tecnologia);
        String fileName = tecnologia.getId() + FileUploadUtil.getExtension(originalFileName);
        String urlImagem = fileUploadUtil.upload(fileName, form.getIcone().getBytes());
        tecnologia.setIcone(urlImagem);
        repository.save(tecnologia);

        return "redirect:/tecnologia";
    }

    @PostMapping("excluir/{id}")
    public String excluir(@PathVariable UUID id) {
        repository.deleteById(id);

        return "redirect:/tecnologia";
    }

}
