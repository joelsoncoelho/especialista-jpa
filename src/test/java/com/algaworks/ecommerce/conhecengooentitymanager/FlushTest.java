package com.algaworks.ecommerce.conhecengooentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Test;

public class FlushTest extends EntityManagerTest {

    @Test(expected = Exception.class)
    public void chamarFlush(){
        try {
            entityManager.getTransaction().begin();

            Pedido pedido = entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedido.PAGO);

            entityManager.flush();

           // entityManager.clear();

            if(pedido.getPagamento() == null){
                throw new RuntimeException("Pedido ainda não foi pego.");
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
