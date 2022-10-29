package dev.jpedrosnts.portifolio.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_projeto")
    private UUID id;
    @Column(name = "nm_projeto")
    private String nome;
    @Column(name = "ds_projeto")
    private String descricao;
    @Column(name = "nm_link_projeto")
    private String link;
    @Column(name = "nm_imagem")
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "cd_tipo_projeto")
    private TipoProjeto tipoProjeto;

    @ManyToMany
    @JoinTable(name = "tb_tecnologia_projeto", joinColumns = @JoinColumn(name = "cd_projeto"), inverseJoinColumns = @JoinColumn(name = "cd_tecnologia"))
    private List<Tecnologia> tecnologias = new ArrayList<>();

}