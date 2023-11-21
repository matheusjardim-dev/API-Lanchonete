package br.com.lanchonete.apilanchonete;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    @GetMapping("/admin")
    public String showMainPage(){
        return "index";
    }

    @GetMapping("/ve")
    public String showVendedorPage(){
        return "mainPage";
    }
}
