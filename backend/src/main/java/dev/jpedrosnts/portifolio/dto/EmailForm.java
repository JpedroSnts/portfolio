package dev.jpedrosnts.portifolio.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailForm {
    @NotEmpty(message = "Nome é obrigatório!")
    private String nome;
    @NotEmpty(message = "Email é obrigatório!")
    @Email(message = "Email inválido!")
    private String email;
    @NotEmpty(message = "Mensagem é obrigatória!")
    private String mensagem;
}
