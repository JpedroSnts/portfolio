package dev.jpedrosnts.portifolio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "tb_projeto")
public class Projeto {

    @Id
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