package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Table(name = "categoria")
@Entity
public class Categoria {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private String nome;

    //Auto-relacionamento
    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;  // Dono-relacinamento ()

    @OneToMany(mappedBy = "categoriaPai") // Não-Dono-relacinamento ()
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;

}
