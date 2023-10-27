package br.com.lanchonete.apilanchonete.produto;

import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
    public Long countById(Integer id);
}