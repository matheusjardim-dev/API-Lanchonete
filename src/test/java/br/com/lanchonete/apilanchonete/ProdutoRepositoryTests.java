package br.com.lanchonete.apilanchonete;

import br.com.lanchonete.apilanchonete.produto.Produto;
import br.com.lanchonete.apilanchonete.produto.ProdutoRepository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class ProdutoRepositoryTests {
    @Autowired private ProdutoRepository repo;

    @Test
    public void testAddNewProd(){
        Produto produto = new Produto();
        produto.setNome("Esfiha de Frango");
        produto.setValor(7.99);
        produto.setDisponivel(true);
        
        Produto savedProduto = repo.save(produto);

        Assertions.assertThat(savedProduto).isNotNull();
        Assertions.assertThat(savedProduto.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Produto> produtos = repo.findAll();

        Assertions.assertThat(produtos).hasSizeGreaterThan(0);

        for(Produto produto : produtos){
            System.out.println(produto);
        }
    }

    @Test
    public void testUpdate(){
        Integer produtoId = 1;
        Optional<Produto> optionalProduto = repo.findById(produtoId);
        Produto produto = optionalProduto.get();
        produto.setDisponivel(false);
        repo.save(produto);

        Produto updatedProduto = repo.findById(produtoId).get();
        Assertions.assertThat(updatedProduto.isDisponivel()).isEqualTo(false);
    }

    @Test
    public void testGet(){
        Integer produtoId = 1;
        Optional<Produto> optionalProduto = repo.findById(produtoId);
        Assertions.assertThat(optionalProduto).isPresent();
        System.out.println(optionalProduto.get());
    }

    @Test 
    public void testDelete(){
        Integer produtoId = 1;
        repo.deleteById(produtoId);

        Optional<Produto> optionalProduto = repo.findById(produtoId);
        Assertions.assertThat(optionalProduto).isNotPresent();

    }
}
