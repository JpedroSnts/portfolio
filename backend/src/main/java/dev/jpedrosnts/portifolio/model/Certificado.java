package dev.jpedrosnts.portifolio.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_certificado")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
