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

}
