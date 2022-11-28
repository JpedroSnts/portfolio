package dev.jpedrosnts.portifolio.dto;

import dev.jpedrosnts.portifolio.model.Certificado;
import dev.jpedrosnts.portifolio.model.Tecnologia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class CertificadoDto {

    private UUID id;
    private String nome;
    private String certificado;
    private Tecnologia tecnologia;

    public CertificadoDto(Certificado certificado) {
        this.id = certificado.getId();
        this.nome = certificado.getNome();
        this.certificado = certificado.getImagem();
        this.tecnologia = certificado.getTecnologia();
    }

    public static List<CertificadoDto> toDto(List<Certificado> certificados) {
        return certificados.stream().map(CertificadoDto::new).collect(Collectors.toList());
    }
}
