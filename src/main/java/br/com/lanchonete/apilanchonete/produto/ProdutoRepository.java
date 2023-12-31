package br.com.lanchonete.apilanchonete.produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    public Long countById(Integer id);
    List<Produto> findByUser_Id(Integer idUser);
    public void deleteAllByUser_Id(Integer idUser);
}