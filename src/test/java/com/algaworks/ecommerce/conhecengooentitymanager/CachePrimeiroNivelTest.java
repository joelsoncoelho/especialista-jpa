package com.algaworks.ecommerce.conhecengooentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

public class CachePrimeiroNivelTest extends EntityManagerTest {

    @Test
    public void verificarCache(){
        Produto produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());

        System.out.println("--------------------------------------------");

       // entityManager.clear(); // remove da memória

      // Outra forma de remover da memória
      //  entityManager.close();
      //  entityManager = entityManagerFactory.createEntityManager();

        Produto produtoResgatadoNaMemoria = entityManager.find(Produto.class, produto.getId());
        System.out.println(produtoResgatadoNaMemoria.getNome());

    }
}
