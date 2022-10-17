package dev.jpedrosnts.portifolio.model.entity;

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
@Table(name = "tb_tecnologia")
public class Tecnologia {

    @Id
    @Column(name = "sg_tecnologia")
    private String id;
    @Column(name = "nm_tecnologia")
    private String nome;
    @Column(name = "nm_icone")
    private String icone;

}
