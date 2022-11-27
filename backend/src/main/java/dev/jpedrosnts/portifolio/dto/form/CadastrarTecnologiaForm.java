package dev.jpedrosnts.portifolio.dto.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CadastrarTecnologiaForm {
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;
    @NotNull(message = "Icone não pode estar vazio")
    private MultipartFile icone;
}
