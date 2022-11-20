package dev.jpedrosnts.portifolio.controller;

import dev.jpedrosnts.portifolio.dto.CadastrarCertificadoForm;
import dev.jpedrosnts.portifolio.model.Certificado;
import dev.jpedrosnts.portifolio.model.Tecnologia;
import dev.jpedrosnts.portifolio.repository.CertificadoRepository;
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
@RequestMapping("certificado")
public class CertificadoController {

    private final FileUploadUtil fileUploadUtil;
    private final CertificadoRepository repository;
    private final TecnologiaRepository tecnologiaRepository;

    @Autowired
    public CertificadoController(FileUploadUtil fileUploadUtil, CertificadoRepository repository, TecnologiaRepository tecnologiaRepository) {
        this.fileUploadUtil = fileUploadUtil;
        this.repository = repository;
        this.tecnologiaRepository = tecnologiaRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("certificados", repository.findAll());
        return "certificado/index";
    }

    @GetMapping("cadastrar")
    public String cadastrar(CadastrarCertificadoForm cadastrarCertificadoForm, Model model) {
        model.addAttribute("tecnologias", tecnologiaRepository.findAll());
        return "certificado/cadastrar";
    }

    @PostMapping("cadastrar")
    public String cadastrarPost(@Valid CadastrarCertificadoForm form, BindingResult result, Model model) throws IOException {
        FieldError fieldError;
        String originalFileName = form.getCertificado().getOriginalFilename();

        if (form.getCertificadoLink().isEmpty()) {
            if (form.getCertificado().isEmpty()) {
                fieldError = new FieldError("form", "certificado", "Certificado não pode estar vazio");
                result.addError(fieldError);
            }
        }

        if (result.hasErrors() || originalFileName == null) {
            model.addAttribute("tecnologias", tecnologiaRepository.findAll());
            return "certificado/cadastrar";
        }

        Tecnologia tecnologia = tecnologiaRepository.getById(UUID.fromString(form.getTecnologia()));
        Certificado certificado = new Certificado();
        certificado.setNome(form.getNome());
        certificado.setTecnologia(tecnologia);
        repository.save(certificado);
        if (form.getCertificadoLink().isEmpty()) {
            String fileName = certificado.getId() + FileUploadUtil.getExtension(originalFileName);
            String urlCertificado = fileUploadUtil.upload(fileName, form.getCertificado().getBytes());
            certificado.setImagem(urlCertificado);
        } else {
            certificado.setImagem(form.getCertificadoLink());
        }
        repository.save(certificado);

        return "redirect:/certificado";
    }


    @GetMapping("editar/{id}")
    public String editar(CadastrarCertificadoForm cadastrarCertificadoForm, Model model, @PathVariable UUID id) {
        model.addAttribute("id", id);
        model.addAttribute("tecnologias", tecnologiaRepository.findAll());
        Certificado certificado = repository.getById(id);
        cadastrarCertificadoForm.setNome(certificado.getNome());
        cadastrarCertificadoForm.setCertificadoLink(certificado.getImagem());
        cadastrarCertificadoForm.setTecnologia(certificado.getTecnologia().getId().toString());
        return "certificado/editar";
    }

    @PostMapping("editar/{id}")
    public String editarPost(@Valid CadastrarCertificadoForm form, BindingResult result, Model model, @PathVariable UUID id) throws IOException {
        FieldError fieldError;
        String originalFileName = form.getCertificado().getOriginalFilename();

        if (form.getCertificadoLink().isEmpty()) {
            if (form.getCertificado().isEmpty()) {
                fieldError = new FieldError("form", "certificado", "Certificado não pode estar vazio");
                result.addError(fieldError);
            }
        }

        if (result.hasErrors() || originalFileName == null) {
            model.addAttribute("tecnologias", tecnologiaRepository.findAll());
            return "certificado/editar";
        }

        Tecnologia tecnologia = tecnologiaRepository.getById(UUID.fromString(form.getTecnologia()));
        Certificado certificado = repository.getById(id);
        certificado.setNome(form.getNome());
        certificado.setTecnologia(tecnologia);
        repository.save(certificado);
        if (form.getCertificadoLink().isEmpty()) {
            fileUploadUtil.delete(certificado.getImagem());
            String fileName = certificado.getId() + FileUploadUtil.getExtension(originalFileName);
            String urlCertificado = fileUploadUtil.upload(fileName, form.getCertificado().getBytes());
            certificado.setImagem(urlCertificado);
        } else {
            certificado.setImagem(form.getCertificadoLink());
        }
        repository.save(certificado);

        return "redirect:/certificado";
    }

    @PostMapping("excluir/{id}")
    public String deletar(@PathVariable UUID id) {
        repository.deleteById(id);
        return "redirect:/certificado";
    }
}
