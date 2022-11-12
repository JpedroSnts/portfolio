package dev.jpedrosnts.portifolio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class TipoProjetoForm {
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;
}
