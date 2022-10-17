package dev.jpedrosnts.portifolio.model.entity;

import java.time.LocalDateTime;
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
@Table(name = "tb_contato")
public class Contato {
    @Id
    @Column(name = "cd_contato")
    private UUID id;
    @Column(name = "nm_pessoa")
    private String nome;
    @Column(name = "nm_email_pessoa")
    private String email;
    @Column(name = "nm_mensagem_pessoa")
    private String mensagem;
    @Column(name = "dt_envio")
    private LocalDateTime data = LocalDateTime.now();
}
