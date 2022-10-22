package dev.jpedrosnts.portifolio.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "tb_tipo_projeto")
public class TipoProjeto {

    @Id
    @Column(name = "cd_tipo_projeto")
    private UUID id;
    @Column(name = "nm_tipo_projeto")
    private String nome;

}
