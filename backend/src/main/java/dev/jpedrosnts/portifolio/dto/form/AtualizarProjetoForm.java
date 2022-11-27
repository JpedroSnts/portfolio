package dev.jpedrosnts.portifolio.dto.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AtualizarProjetoForm {
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;
    @NotBlank(message = "Link não pode estar vazio")
    private String link;
    @NotBlank(message = "Tipo de Projeto não pode estar vazio")
    private String tipoProjeto;
    @NotBlank(message = "Descrição não pode estar vazia")
    private String descricao;
    private MultipartFile imagem;
}
