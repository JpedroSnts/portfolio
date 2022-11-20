package dev.jpedrosnts.portifolio.dto;

import dev.jpedrosnts.portifolio.model.Certificado;
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

    public CertificadoDto(Certificado certificado) {
        this.id = certificado.getId();
        this.nome = certificado.getNome();
        this.certificado = certificado.getImagem();
    }

    public static List<CertificadoDto> toDto(List<Certificado> certificados) {
        return certificados.stream().map(CertificadoDto::new).collect(Collectors.toList());
    }
}
