package br.com.lanchonete.apilanchonete.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/admin/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/admin/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Cadastrar Usuário");
        return "userForm";
    }
    
    @PostMapping("/admin/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message", "Usuário salvo com sucesso!");
        return "redirect:/admin/users";  
    }
    
    @GetMapping("/admin/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Editar Usuário (ID: " + id + ")");
            return "userForm";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/users";  
        }
    }
    
    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "O usuário com ID " + id + " foi deletado.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Login");
        return "login";
    }

    @PostMapping("/login/auth")
    public String authUser(User user, RedirectAttributes rea){
        Integer ra = user.getRA();
        String password = user.getPassword();
        if(service.autenticar(ra, password)){
            try {
                user = service.getDados(ra);
                rea.addFlashAttribute("message", "Usuário logado com sucesso!");
                if(user.getNivel() == 1){
                    return "redirect:/admin/users";
                } else if (user.getNivel() == 2) {
                    return "redirect:/vendedor";
                } else {
                    return "redirect:/login";  
                }
                
            } catch (UserNotFoundException e) {
                rea.addFlashAttribute("message", e.getMessage());
                return "redirect:/login";  
            }
        } else {
            return "redirect:/login";
        }
    }
} 
