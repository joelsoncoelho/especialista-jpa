package com.algaworks.ecommerce.conhecengooentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ContextoDePersistenciaTest extends EntityManagerTest {

    @Test
    public void usarContextoPersistencia(){
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setPreco(new BigDecimal(100));

        Produto produto2 = new Produto();
        produto2.setNome("Caneca para café");
        produto2.setPreco(new BigDecimal(BigInteger.TEN));
        produto2.setDescricao("Caneca de porcelana");
        entityManager.persist(produto2);

        Produto produto3 = new Produto();
        produto3.setNome("Caneca para chá");
        produto3.setPreco(new BigDecimal(BigInteger.TEN));
        produto3.setDescricao("Caneca de porcelana");
        produto3 = entityManager.merge(produto2);

        entityManager.flush();

        produto3.setDescricao("Alterar descrição");

        entityManager.getTransaction().commit();



    }
}