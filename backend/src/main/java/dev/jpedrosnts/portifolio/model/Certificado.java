package dev.jpedrosnts.portifolio.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_certificado")
public class Certificado {

    @Id
    @Column(name = "cd_certificado")
    private UUID id;
    @Column(name = "nm_certificado")
    private String nome;
    @Column(name = "ds_certificado")
    private String descricao;
    @Column(name = "nm_imagem", columnDefinition = "TEXT")
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "sg_tecnologia")
    private Tecnologia tecnologia;

}
