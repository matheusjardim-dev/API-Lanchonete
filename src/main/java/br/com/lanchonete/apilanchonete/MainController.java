package br.com.lanchonete.apilanchonete;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    @GetMapping("/")
    public String showMainPage(){
        return "index";
    }

    @GetMapping("/vendedor")
    public String showVendedorPage(){
        return "mainPage";
    }
}
