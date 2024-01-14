package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

public class EstrategiaChavePrimariaTest extends EntityManagerTest {

    // GenerationType.AUTO, a implementação da especificação escolherá uma estratégia para geração dos ids.
    // GenerationType.TABLE, o Hibernate utilizará uma tabela para gerar as chaves primárias. É um pouco menos performatico.
    // GenerationType.SEQUENCE, o Hibernate utilizará as sequences do banco de dados para gerar as chaves primárias.
    // GenerationType.IDENTITY o Hibernate utilizará como estratégia a geração AUTO_INCREMENT. Já, se o banco de dados for o Postgres, o Hibernate gerará uma coluna do tipo SERIAL.

    // @GeneratedValue(strategy = GenerationType.AUTO)

    /*
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "categoria_sequencias_chave_primaria", initialValue = 0, allocationSize = 50)
     */

    /*
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tabela")
    @TableGenerator(name = "tabela", table = "hibernate_sequeces",  pkColumnName = "sequence_name", pkColumnValue = "categoria",
            valueColumnName = "next_val", initialValue = 0, allocationSize = 50)
     */

    @Test
    public void testarGeraçaoDeChavesComJPA(){

        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear(); //Só necessário para testar a ido no banco e demonstrar

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertNotNull(categoriaVerificacao);

    }

}
