package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void impedirOperacaoComBancoDeDados(){

        Produto produto = entityManager.find(Produto.class, 1);
        entityManager.detach(produto); //desanexa  um entidade do entity manager

        entityManager.getTransaction().begin();
        produto.setNome("Notebook Gamer Avell NVIDIA GEFORCE RTX 4060");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Notebook Gamer Alienware", produtoVerificacao.getNome());
    }

    @Test
    public void mostarDiferencaPersisteMerge(){
        Produto produtoPersist = new Produto();

        produtoPersist.setId(5);
        produtoPersist.setNome("Notebook Samsung Book 2");
        produtoPersist.setDescricao("Design elegante e poderoso");
        produtoPersist.setPreco(new BigDecimal(2500));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist);
        produtoPersist.setNome("Notebook Samsung Book 3");
        entityManager.getTransaction().commit();

        entityManager.clear(); //limpar a memória e vai no banco de novo // não muito recomendado

        Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
        Assert.assertNotNull(produtoVerificacaoPersist);

        System.out.println("======================================================================\n");

        Produto produtoMerge = new Produto();

        produtoMerge.setId(6);
        produtoMerge.setNome("Notebook Gamer Alienware m16");
        produtoMerge.setDescricao("Máximo Desempenho Para Seu Jogo");
        produtoMerge.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Notebook Dell G15");
        entityManager.getTransaction().commit();

        entityManager.clear(); //limpar a memória e vai no banco de novo // não muito recomendado

        Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
        Assert.assertNotNull(produtoVerificacaoMerge);

    }

    @Test
    public void inserirObjetoComMerge(){
        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("Notebook Avell A65 ION");
        produto.setDescricao("Poderoso. Potente. Eficiente.\n" +
                "O notebook perfeito para profissionais com rotinas eletrizantes!");
        produto.setPreco(new BigDecimal(9998.89));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear(); //limpar a memória e vai no banco de novo // não muito recomendado

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);

    }

    @Test
    public void atualizarObjetoGerenciado(){

        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Notebook Gamer Avell NVIDIA GEFORCE RTX 4060");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Notebook Gamer Avell NVIDIA GEFORCE RTX 4060", produtoVerificacao.getNome());
    }

    @Test
    public void atualizarObjeto(){

        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Notebook Gamer Lenovo Gaming 3i");
        produto.setDescricao("Preparece para uma experiência nunca vista antes.");
        produto.setPreco(new BigDecimal(3509.90));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
        Assert.assertEquals("Notebook Gamer Lenovo Gaming 3i", produto.getNome());
    }

    @Test
    public void removerObjeto(){

        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

       // entityManager.clear(); //Não é necessário na asserção para operação de remoção.

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNull(produtoVerificacao);
    }

    @Test
    public void inserirOPrimeiroObjeto(){
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Notebook Gamer Dell G15");
        produto.setDescricao("Notebooks Série G NVIDIA GeForce RTX 4050");
        produto.setPreco(new BigDecimal(7699));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear(); //limpar a memória e vai no banco de novo // não muito recomendado

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);

    }

    @Test
    public void abrirEFecharATransacao(){

        Produto produto = new Produto(); // somente para não mostar erros

        entityManager.getTransaction().begin();

      //  entityManager.persist(produto);
      //  entityManager.merge(produto);
      //  entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }
}
