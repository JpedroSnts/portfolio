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
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    private UUID id;
    @Column(name = "nm_usuario")
    private String nome;
    @Column(name = "nm_email")
    private String email;
    @Column(name = "nm_senha")
    private String senha;

}
