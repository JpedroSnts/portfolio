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
@Table(name = "tb_tipo_projeto")
public class TipoProjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_tipo_projeto")
    private UUID id;
    @Column(name = "nm_tipo_projeto")
    private String nome;

}
