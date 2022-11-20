package dev.jpedrosnts.portifolio.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CadastrarCertificadoForm {
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;
    @NotBlank(message = "Tecnologia não pode estar vazia")
    private String tecnologia;
    private MultipartFile certificado;
    private String certificadoLink;
}
