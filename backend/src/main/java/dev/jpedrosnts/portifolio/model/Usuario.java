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
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "nm_usuario")
    private String nome;
    @Column(name = "nm_email")
    private String email;
    @Column(name = "nm_senha")
    private String senha;

}
