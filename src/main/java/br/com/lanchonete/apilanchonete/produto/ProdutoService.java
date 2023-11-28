package br.com.lanchonete.apilanchonete.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired private ProdutoRepository repo;

    public List<Produto> listAll(){
        return (List<Produto>) repo.findAll();
    }

    public void save(Produto produto) {
        repo.save(produto);
    }

    public Produto get(Integer id) throws ProdutoNotFoundException{
        Optional<Produto> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ProdutoNotFoundException("Nenhum produto foi encontrado com o id " + id);
    }

    public void delete(Integer id) throws ProdutoNotFoundException{
        Long count = repo.countById(id);
        if(count == null || count == 0){
             throw new ProdutoNotFoundException("Nenhum produto foi encontrado com o id " + id);
        }
        repo.deleteById(id);
    }

    public List<Produto> listProd(Integer idUser) {
        return repo.findByUser_Id(idUser);
    }

    public void deleteAllByUser_Id(Integer idUser) {
        repo.deleteAllByUser_Id(idUser);
    }

}
