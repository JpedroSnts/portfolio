package dev.jpedrosnts.portifolio.dto;

import dev.jpedrosnts.portifolio.model.Projeto;
import dev.jpedrosnts.portifolio.model.Tecnologia;
import dev.jpedrosnts.portifolio.model.TipoProjeto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ProjetoDto {

    private UUID id;
    private String nome;
    private String descricao;
    private String link;
    private String imagem;
    private TipoProjeto tipoProjeto;
    private List<Tecnologia> tecnologias = new ArrayList<>();

    public ProjetoDto(Projeto projeto) {
        id = projeto.getId();
        nome = projeto.getNome();
        descricao = projeto.getDescricao();
        link = projeto.getDescricao();
        imagem = projeto.getImagem();
        tipoProjeto = projeto.getTipoProjeto();
        tecnologias = projeto.getTecnologias();
    }

    public static List<ProjetoDto> toDto(List<Projeto> all) {
        return all.stream().map(ProjetoDto::new).collect(Collectors.toList());
    }
}
