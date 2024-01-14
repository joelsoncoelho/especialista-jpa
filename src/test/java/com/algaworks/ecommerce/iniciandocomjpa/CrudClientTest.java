package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class CrudClientTest extends EntityManagerTest {

    @Test
    public void mostarDiferencaPersisteMerge(){
        Cliente clientePersist = new Cliente();

        clientePersist.setId(null);
        clientePersist.setNome("Zelda");

        entityManager.getTransaction().begin();
        entityManager.persist(clientePersist);
        clientePersist.setNome("Teobaldo");
        entityManager.getTransaction().commit();

        entityManager.clear(); //limpar a memória e vai no banco de novo // não muito recomendado

        Cliente clienteVerificacaoPersist = entityManager.find(Cliente.class, clientePersist.getId());
        Assert.assertNotNull(clienteVerificacaoPersist);

        System.out.println("======================================================================\n");

        Cliente clienteMerge = new Cliente();

        clienteMerge.setId(null);
        clienteMerge.setNome("Malala");

        entityManager.getTransaction().begin();
        clienteMerge = entityManager.merge(clienteMerge);
        clienteMerge.setNome("Jujuba");
        entityManager.getTransaction().commit();

        entityManager.clear(); //limpar a memória e vai no banco de novo // não muito recomendado

        Cliente clienteVerificacaoMerge = entityManager.find(Cliente.class, clienteMerge.getId());
        Assert.assertNotNull(clienteVerificacaoMerge);

    }

    @Test
    public void inserirObjetoComMerge(){
        Cliente cliente = entityManager.find(Cliente.class, 3);

        //cliente.setId(3);
        cliente.setNome("Cabocla dos Anjos");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear(); //limpar a memória e vai no banco de novo // não muito recomendado

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);

    }

    @Test
    public void atualizarObjetoGerenciado(){

        Cliente cliente = entityManager.find(Cliente.class, 2);

        cliente.setNome("Luffy");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("Luffy", clienteVerificacao.getNome());
    }

    @Test
    public void atualizar(){

        Cliente cliente = entityManager.find(Cliente.class, 1);
        //cliente.setId(1);
        cliente.setNome("Patricia Poeta");
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
        Assert.assertEquals("Patricia Poeta", clienteVerificacao.getNome());
    }

    @Test
    public void remove(){

        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

       // entityManager.clear(); //Não é necessário na asserção para operação de remoção.

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNull(clienteVerificacao);
    }

    @Test
    public void insert(){
        Cliente cliente = new Cliente();

        //cliente.setId(3);
        cliente.setNome("Samuel L. Jackson");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear(); //limpar a memória e vai no banco de novo // não muito recomendado

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);

    }

}
