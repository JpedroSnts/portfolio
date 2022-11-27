package dev.jpedrosnts.portifolio.dto;

import dev.jpedrosnts.portifolio.model.SobreMimTemplate;
import lombok.Getter;

@Getter
public class SobreMimTemplateDto {
    private final String[] name = new String[2];
    private String[] skills;
    private String image;
    private String text;

    public SobreMimTemplateDto(SobreMimTemplate sobreMimTemplate) {
        if (sobreMimTemplate != null) {
            this.name[0] = sobreMimTemplate.getNome();
            this.name[1] = sobreMimTemplate.getSobrenome();
            this.skills = sobreMimTemplate.getTecnologias().split(",");
            this.image = sobreMimTemplate.getMinhaFoto();
            this.text = sobreMimTemplate.getTexto();
        }
    }
}
