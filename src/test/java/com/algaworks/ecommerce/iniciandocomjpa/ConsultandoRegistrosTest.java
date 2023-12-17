package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.*;

public class ConsultandoRegistrosTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador(){
        Produto produto = entityManager.find(Produto.class, 1);

      // Produto produto = entityManager.getReference(Produto.class, 1);

       // System.out.println("Ainda não buscou!!!");

        Assert.assertNotNull(produto);
        Assert.assertEquals("Notebook Gamer Alienware", produto.getNome());
    }

    @Test
    public void atualizarAReferencia(){
        Produto produto = entityManager.find(Produto.class, 1);

        produto.setNome("Monitor Dell");
        System.out.println(produto.getNome());
        entityManager.refresh(produto);
        System.out.println(produto.getNome());
        Assert.assertEquals("Notebook Gamer Alienware", produto.getNome());
    }

}
