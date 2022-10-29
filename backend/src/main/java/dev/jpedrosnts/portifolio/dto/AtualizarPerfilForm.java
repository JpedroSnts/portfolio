package dev.jpedrosnts.portifolio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AtualizarPerfilForm {
    
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;
    @NotBlank(message = "Senha não pode estar vazia")
    private String senha;
    private String novaSenha;
}
