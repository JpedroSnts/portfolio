package dev.jpedrosnts.portifolio.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contato")
public class Contato {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)", name = "cd_contato")
    @Type(type = "uuid-char")
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
