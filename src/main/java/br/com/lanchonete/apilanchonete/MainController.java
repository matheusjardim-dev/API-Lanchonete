package br.com.lanchonete.apilanchonete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.lanchonete.apilanchonete.produto.Produto;
import br.com.lanchonete.apilanchonete.produto.ProdutoService;

@Controller
public class MainController {
    @Autowired private ProdutoService serviceProd;
    @GetMapping("/")
    public String showMainPage(Model model){
        List<Produto> listProdutos = serviceProd.listAll();
        model.addAttribute("listProdutos", listProdutos);
        return "index";
    }
}
