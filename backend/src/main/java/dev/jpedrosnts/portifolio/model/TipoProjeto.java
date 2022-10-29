package dev.jpedrosnts.portifolio.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)", name = "cd_tipo_projeto")
    @Type(type = "uuid-char")
    private UUID id;
    @Column(name = "nm_tipo_projeto")
    private String nome;

}
