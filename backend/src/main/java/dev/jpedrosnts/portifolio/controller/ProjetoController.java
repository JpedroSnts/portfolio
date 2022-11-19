package dev.jpedrosnts.portifolio.controller;

import dev.jpedrosnts.portifolio.dto.AtualizarProjetoForm;
import dev.jpedrosnts.portifolio.dto.CadastrarProjetoForm;
import dev.jpedrosnts.portifolio.model.Projeto;
import dev.jpedrosnts.portifolio.model.TipoProjeto;
import dev.jpedrosnts.portifolio.repository.ProjetoRepository;
import dev.jpedrosnts.portifolio.repository.TipoProjetoRepository;
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
@RequestMapping("/projeto")
public class ProjetoController {

    private final FileUploadUtil fileUploadUtil;
    private final ProjetoRepository projetoRepository;
    private final TipoProjetoRepository tipoProjetoRepository;

    @Autowired
    public ProjetoController(FileUploadUtil fileUploadUtil, ProjetoRepository projetoRepository, TipoProjetoRepository tipoProjetoRepository) {
        this.fileUploadUtil = fileUploadUtil;
        this.projetoRepository = projetoRepository;
        this.tipoProjetoRepository = tipoProjetoRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("projetos", projetoRepository.findAll());
        return "projeto/index";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(CadastrarProjetoForm cadastrarProjetoForm, Model model) {
        model.addAttribute("tiposProjetos", tipoProjetoRepository.findAll());
        return "projeto/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String editarGet(AtualizarProjetoForm atualizarProjetoForm, Model model, @PathVariable UUID id) {
        editar(atualizarProjetoForm, id, model);
        return "projeto/editar";
    }

    @PostMapping("/editar/{id}")
    public String editarPost(@Valid AtualizarProjetoForm form, BindingResult result, @PathVariable UUID id, Model model) throws IOException {
        if (result.hasErrors()) {
            editar(form, id, model);
            return "projeto/editar";
        }
        Projeto projeto = projetoRepository.findById(id).orElseThrow();
        TipoProjeto tipoProjeto = tipoProjetoRepository.findById(UUID.fromString(form.getTipoProjeto())).orElseThrow();
        projeto.setNome(form.getNome());
        projeto.setLink(form.getLink());
        projeto.setDescricao(form.getDescricao());
        projeto.setTipoProjeto(tipoProjeto);
        String originalFilename = form.getImagem().getOriginalFilename();
        if (originalFilename != null && !originalFilename.equals("")) {
            String fileName = projeto.getId() + FileUploadUtil.getExtension(originalFilename);
            String urlImagem = fileUploadUtil.upload(fileName, form.getImagem().getBytes());
            projeto.setImagem(urlImagem);
        }
        projetoRepository.save(projeto);
        return "redirect:/projeto";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid CadastrarProjetoForm form, BindingResult result, Model model) throws IOException {
        FieldError fieldError;
        String originalFileName = form.getImagem().getOriginalFilename();

        if (form.getImagem().isEmpty()) {
            fieldError = new FieldError("form", "imagem", "Imagem não pode estar vazia");
            result.addError(fieldError);
        } else if (!FileUploadUtil.isImage(originalFileName)) {
            fieldError = new FieldError("form", "imagem", "Formato não suportado. Tente: PNG, JPG ou JPEG");
            result.addError(fieldError);
        }

        if (result.hasErrors() || originalFileName == null) {
            model.addAttribute("tiposProjetos", tipoProjetoRepository.findAll());
            return "projeto/cadastrar";
        }

        TipoProjeto tipoProjeto = tipoProjetoRepository.findById(UUID.fromString(form.getTipoProjeto())).orElseThrow();
        Projeto projeto = new Projeto();
        projeto.setNome(form.getNome());
        projeto.setLink(form.getLink());
        projeto.setDescricao(form.getDescricao());
        projeto.setTipoProjeto(tipoProjeto);
        projeto = projetoRepository.save(projeto);
        String fileName = projeto.getId() + FileUploadUtil.getExtension(originalFileName);
        String urlImagem = fileUploadUtil.upload(fileName, form.getImagem().getBytes());
        projeto.setImagem(urlImagem);
        projetoRepository.save(projeto);
        return "redirect:/projeto";
    }

    @PostMapping("/excluir/{id}")
    private String excluir(@PathVariable UUID id) {
        Projeto projeto = projetoRepository.getById(id);
        String[] arr = projeto.getImagem().split("/");
        fileUploadUtil.delete(arr[arr.length - 1]);
        projetoRepository.delete(projeto);
        return "redirect:/projeto";
    }

    private void editar(AtualizarProjetoForm form, @PathVariable UUID id, Model model) {
        model.addAttribute("tiposProjetos", tipoProjetoRepository.findAll());
        model.addAttribute("id", id);
        Projeto projeto = projetoRepository.findById(id).orElseThrow();
        model.addAttribute("imagemLink", projeto.getImagem());
        form.setTipoProjeto(projeto.getTipoProjeto().getId().toString());
        form.setNome(projeto.getNome());
        form.setLink(projeto.getLink());
        form.setDescricao(projeto.getDescricao());
    }
}
