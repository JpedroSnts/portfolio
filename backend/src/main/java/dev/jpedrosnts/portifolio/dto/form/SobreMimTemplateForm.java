package dev.jpedrosnts.portifolio.dto.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SobreMimTemplateForm {
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;
    @NotBlank(message = "Sobrenome não pode estar vazio")
    private String sobrenome;
    @NotBlank(message = "Tecnologias não pode estar vazio")
    private String tecnologias;
    @NotBlank(message = "Texto não pode estar vazio")
    private String texto;
    @NotNull(message = "Imagem não pode estar vazia")
    private MultipartFile imagem;
}
