package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotafiscalListener;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners({ GerarNotafiscalListener.class, GenericoListener.class})
@Setter
@Getter
@Table(name = "pedido")
@Entity
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // No modo EAGER, quando o relacionamento está anotado com @OneToOne ou @ManyToOne ele é carregado
    // de modo Eager ou seja, quando fizemos qualquer tipo de busca na entidade.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY) //No modo LAZY só carregar as informações quando chamado o metódo.
    private List<ItemPedido> itens;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private BigDecimal total;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamento;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    public boolean isPago(){
        return StatusPedido.PAGO.equals(status);
    }

    public void calculatTotal(){
        if (itens != null){
            total = itens.stream().map(ItemPedido::getPrecoProduto).reduce(BigDecimal.ZERO, BigDecimal::add);
           // System.out.println(total);
        }
    }

   @PrePersist
    public void aoPersistir(){
        dataCriacao = LocalDateTime.now();
        calculatTotal();
    }

   @PreUpdate
    public void aoAtualizar(){
        dataUltimaAtualizacao = LocalDateTime.now();
        calculatTotal();
    }

    @PostPersist
    public void aposPersistir(){
        System.out.println("Após persistir Pedido.");
    }

    @PostUpdate
    public void aposAtualizar(){
        System.out.println("Após atualizar Pedido.");
    }

    @PreRemove
    public void aoRemover(){
        System.out.println("Antes de remover Pedido.");
    }

    @PostRemove
    public void aposRemover(){
        System.out.println("Após remover o Pedido.");
    }

    @PostLoad
    public void aoCarregar(){
        System.out.println("Após carregar o Pedido.");
    }

}
