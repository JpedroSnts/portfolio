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
@Table(name = "tb_tecnologia")
public class Tecnologia {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)", name = "cd_tecnologia")
    @Type(type = "uuid-char")
    private UUID id;
    @Column(name = "nm_tecnologia")
    private String nome;
    @Column(name = "nm_icone")
    private String icone;

}
