package dev.jpedrosnts.portifolio.dto.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContatoTemplateForm {

    @NotBlank(message = "Nome1 não pode estar vazio")
    private String nome1;
    @NotBlank(message = "Link1 não pode estar vazio")
    private String link1;
    @NotNull(message = "Icone1 não pode estar vazia")
    private MultipartFile icone1;

    @NotBlank(message = "Nome2 não pode estar vazio")
    private String nome2;
    @NotBlank(message = "Link2 não pode estar vazio")
    private String link2;
    @NotNull(message = "Icone2 não pode estar vazia")
    private MultipartFile icone2;

    @NotBlank(message = "Nome3 não pode estar vazio")
    private String nome3;
    @NotBlank(message = "Link3 não pode estar vazio")
    private String link3;
    @NotNull(message = "Icone3 não pode estar vazia")
    private MultipartFile icone3;

    @NotBlank(message = "Nome4 não pode estar vazio")
    private String nome4;
    @NotBlank(message = "Link4 não pode estar vazio")
    private String link4;
    @NotNull(message = "Icone4 não pode estar vazia")
    private MultipartFile icone4;
}
