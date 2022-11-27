package dev.jpedrosnts.portifolio.dto;

import dev.jpedrosnts.portifolio.model.ContatoTemplate;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ContatoTemplateDto {
    private String name;
    private String link;
    private String icon;

    public ContatoTemplateDto(ContatoTemplate contatoTemplate) {
        this.name = contatoTemplate.getNome();
        this.link = contatoTemplate.getLink();
        this.icon = contatoTemplate.getIcone();
    }

    public static List<ContatoTemplateDto> toDto(List<ContatoTemplate> list) {
        return list.stream().map(ContatoTemplateDto::new).collect(Collectors.toList());
    }
}