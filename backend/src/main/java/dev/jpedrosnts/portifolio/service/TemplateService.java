package dev.jpedrosnts.portifolio.service;

import dev.jpedrosnts.portifolio.dto.form.ContatoTemplateForm;
import dev.jpedrosnts.portifolio.dto.form.SobreMimTemplateForm;
import dev.jpedrosnts.portifolio.model.ContatoTemplate;
import dev.jpedrosnts.portifolio.model.SobreMimTemplate;
import dev.jpedrosnts.portifolio.repository.ContatoTemplateRepository;
import dev.jpedrosnts.portifolio.repository.SobreMimTemplateRepository;
import dev.jpedrosnts.portifolio.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TemplateService {

    private final ContatoTemplateRepository cRepository;
    private final SobreMimTemplateRepository smRepository;
    private final FileUploadUtil fileUploadUtil;

    @Autowired
    public TemplateService(ContatoTemplateRepository cRepository, SobreMimTemplateRepository smRepository, FileUploadUtil fileUploadUtil) {
        this.cRepository = cRepository;
        this.smRepository = smRepository;
        this.fileUploadUtil = fileUploadUtil;
    }

    public String atualizarContato(ContatoTemplateForm form, BindingResult result) {
        List<ContatoTemplate> contatos = cRepository.findAll();
        ContatoTemplate contato1 = new ContatoTemplate();
        ContatoTemplate contato2 = new ContatoTemplate();
        ContatoTemplate contato3 = new ContatoTemplate();
        ContatoTemplate contato4 = new ContatoTemplate();
        if (contatos.size() >= 1) contato1 = contatos.get(0);
        if (contatos.size() >= 2) contato2 = contatos.get(1);
        if (contatos.size() >= 3) contato3 = contatos.get(2);
        if (contatos.size() >= 4) contato4 = contatos.get(3);

        validarContatoExistente(contato1, form.getIcone1(), result, "icone1");
        validarContatoExistente(contato2, form.getIcone2(), result, "icone2");
        validarContatoExistente(contato3, form.getIcone3(), result, "icone3");
        validarContatoExistente(contato4, form.getIcone4(), result, "icone4");

        if (result.hasErrors()) {
            return "template/contato";
        }

        try {
            salvarContato(contato1, form.getNome1(), form.getLink1(), form.getIcone1());
            salvarContato(contato2, form.getNome2(), form.getLink2(), form.getIcone1());
            salvarContato(contato3, form.getNome3(), form.getLink3(), form.getIcone3());
            salvarContato(contato4, form.getNome4(), form.getLink4(), form.getIcone4());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return "redirect:/template/contato";
    }

    private void validarImagem(MultipartFile file, BindingResult result, String field) {
        FieldError fieldError;
        String f = field.toUpperCase().charAt(0) + field.substring(1);
        String originalFilename = file.getOriginalFilename();
        if (file.isEmpty()) {
            fieldError = new FieldError("form", field, f + " não pode estar vazio(a)");
            result.addError(fieldError);
        } else if (!FileUploadUtil.isImage(originalFilename)) {
            fieldError = new FieldError("form", field, "Formato não suportado. Tente: PNG, JPG ou JPEG");
            result.addError(fieldError);
        }
    }

    private void validarContatoExistente(ContatoTemplate contato, MultipartFile file, BindingResult result, String field) {
        if (contato.getId() == null) {
            validarImagem(file, result, field);
        } else if (!file.isEmpty() && !FileUploadUtil.isImage(file.getOriginalFilename())) {
            var fieldError = new FieldError("form", field, "Formato não suportado. Tente: PNG, JPG ou JPEG");
            result.addError(fieldError);
        }
    }

    private void validarSobreMimExistente(SobreMimTemplate sobreMim, MultipartFile file, BindingResult result, String field) {
        if (sobreMim.getId() == null) {
            validarImagem(file, result, field);
        } else if (!file.isEmpty() && !FileUploadUtil.isImage(file.getOriginalFilename())) {
            var fieldError = new FieldError("form", field, "Formato não suportado. Tente: PNG, JPG ou JPEG");
            result.addError(fieldError);
        }
    }

    private void salvarContato(ContatoTemplate contato, String nome, String link, MultipartFile icone) throws IOException {
        contato.setNome(nome);
        contato.setLink(link);
        cRepository.save(contato);
        if (contato.getId() == null || !icone.isEmpty()) {
            if (contato.getIcone() != null) {
                String[] arr = contato.getIcone().split("/");
                fileUploadUtil.delete(arr[arr.length - 1]);
            }
            String fileName = UUID.randomUUID() + FileUploadUtil.getExtension(Objects.requireNonNull(icone.getOriginalFilename()));
            String urlImagem = fileUploadUtil.upload(fileName, icone.getBytes());
            contato.setIcone(urlImagem);
            cRepository.save(contato);
        }
    }

    public String atualizarSobreMim(SobreMimTemplateForm form, BindingResult result) {
        SobreMimTemplate sobreMim = new SobreMimTemplate();
        List<SobreMimTemplate> all = smRepository.findAll();
        if (all.size() >= 1) {
            sobreMim = all.get(0);
        }

        validarSobreMimExistente(sobreMim, form.getImagem(), result, "imagem");

        if (result.hasErrors()) {
            return "template/sobreMim";
        }

        sobreMim.setNome(form.getNome());
        sobreMim.setSobrenome(form.getSobrenome());
        sobreMim.setTexto(form.getTexto());
        sobreMim.setTecnologias(form.getTecnologias());
        smRepository.save(sobreMim);
        if (sobreMim.getId() == null || !form.getImagem().isEmpty()) {
            if (sobreMim.getMinhaFoto() != null) {
                String[] arr = sobreMim.getMinhaFoto().split("/");
                fileUploadUtil.delete(arr[arr.length - 1]);
            }
            String fileName = UUID.randomUUID() + FileUploadUtil.getExtension(Objects.requireNonNull(form.getImagem().getOriginalFilename()));
            String urlImagem = null;
            try {
                urlImagem = fileUploadUtil.upload(fileName, form.getImagem().getBytes());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            sobreMim.setMinhaFoto(urlImagem);
            smRepository.save(sobreMim);
        }
        return "redirect:/template/sobre-mim";
    }

    public void listarContatos(ContatoTemplateForm form) {
        List<ContatoTemplate> contatos = cRepository.findAll();
        if (contatos.size() >= 4) {
            form.setNome1(contatos.get(0).getNome());
            form.setLink1(contatos.get(0).getLink());
            form.setNome2(contatos.get(1).getNome());
            form.setLink2(contatos.get(1).getLink());
            form.setNome3(contatos.get(2).getNome());
            form.setLink3(contatos.get(2).getLink());
            form.setNome4(contatos.get(3).getNome());
            form.setLink4(contatos.get(3).getLink());
        }
    }

    public void listarSobreMim(SobreMimTemplateForm form) {
        SobreMimTemplate sobreMim;
        List<SobreMimTemplate> all = smRepository.findAll();
        if (all.size() >= 1) {
            sobreMim = all.get(0);
            form.setNome(sobreMim.getNome());
            form.setSobrenome(sobreMim.getSobrenome());
            form.setTexto(sobreMim.getTexto());
            form.setTecnologias(sobreMim.getTecnologias());
        }
    }
}
