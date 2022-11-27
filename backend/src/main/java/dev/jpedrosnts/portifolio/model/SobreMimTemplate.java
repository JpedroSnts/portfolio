package dev.jpedrosnts.portifolio.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sobre_mim_template")
public class SobreMimTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String tecnologias;
    @Column(columnDefinition = "TEXT")
    private String texto;
    private String minhaFoto;
}

