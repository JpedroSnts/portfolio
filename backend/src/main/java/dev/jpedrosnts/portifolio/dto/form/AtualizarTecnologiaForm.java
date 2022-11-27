package dev.jpedrosnts.portifolio.dto.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AtualizarTecnologiaForm {
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;
    private MultipartFile icone;
}
