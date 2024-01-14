package com.algaworks.ecommerce.conhecengooentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Test;

public class GerenciamentoTransacoesTest extends EntityManagerTest {

    @Test(expected = Exception.class) // lança a exceção porque o pedido Id 1 não tem Pedido com status PAGO
    public void abrirFecharCancelarTransacao() {
        try {
            entityManager.getTransaction().begin();
            metodoDeNegocio();
            entityManager.getTransaction().commit();
        } catch (Exception exception){
            entityManager.getTransaction().rollback();
            throw exception;
        }
    }

    private  void metodoDeNegocio(){
        Pedido pedido = entityManager.find(Pedido.class, 1);
        pedido.setStatus(StatusPedido.PAGO);

        if(pedido.getPagamento() == null){
            throw new RuntimeException("Pedido ainda não fo pago.");
        }
    }

    @Test
    public void abrirFecharCancelarTransacaoSemRollback() {

        Pedido pedido = entityManager.find(Pedido.class, 1);

        entityManager.getTransaction().begin();

        if(pedido.getPagamento() != null){
            pedido.setStatus(StatusPedido.PAGO);
        }

        entityManager.getTransaction().commit();
    }




}
