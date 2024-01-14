package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void analisarMapeamentoObjetoEmbutido() {

        EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
        endereco.setCep("72000-000");
        endereco.setLogradouro("West 42nd Street");
        endereco.setNumero("234");
        endereco.setComplemento("Hilton New York Times Square");
        endereco.setCidade("New York");
        endereco.setEstado("NY");

        Pedido pedido = new Pedido();
       // pedido.setId(5);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setEnderecoEntrega(endereco);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear(); //Só necessário para testar a ido no banco e demonstrar

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertEquals(pedidoVerificacao.getEnderecoEntrega().getLogradouro(), "West 42nd Street");

    }
}
