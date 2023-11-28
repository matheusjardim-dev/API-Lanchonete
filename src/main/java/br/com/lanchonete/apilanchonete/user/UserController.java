package br.com.lanchonete.apilanchonete.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.lanchonete.apilanchonete.produto.ProdutoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired private UserService service;
    @Autowired private ProdutoService prodservice;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/admin/users")
    public String showUserList(Model model){
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            List<User> listUsers = service.listAll();
            model.addAttribute("listUsers", listUsers);
            return "users";
        } else {
            return "redirect:/login";  
        }
    }

    @GetMapping("/admin/users/new")
    public String showNewForm(Model model){
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", new User());
            model.addAttribute("pageTitle", "Cadastrar Usuário");
            return "userForm";
        } else {
            return "redirect:/login";  
        }
        
        
    }
    
    @PostMapping("/admin/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            service.save(user);
            ra.addFlashAttribute("message", "Usuário salvo com sucesso!");
            return "redirect:/admin/users"; 
        } else {
            return "redirect:/login";  
        }
         
    }
    
    @GetMapping("/admin/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            try {
                User user = service.get(id);
                model.addAttribute("user", user);
                model.addAttribute("pageTitle", "Editar Usuário (ID: " + id + ")");
                return "userForm";
            } catch (UserNotFoundException e) {
                ra.addFlashAttribute("message", e.getMessage());
                return "redirect:/admin/users";  
            }
        } else {
            return "redirect:/login";  
        }
        
    }
    
    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            try {
                service.delete(id);
                ra.addFlashAttribute("message", "O usuário com ID " + id + " foi deletado.");
            } catch (UserNotFoundException e) {
                ra.addFlashAttribute("message", e.getMessage());
            }
            return "redirect:/admin/users";
        } else {
            return "redirect:/login";  
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Login");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("idUser");
        session.removeAttribute("nivelUser");
        return "redirect:/login";  
    }

    @PostMapping("/login/auth")
    public String authUser(User user, RedirectAttributes rea, Model model){
        Integer ra = user.getRA();
        String password = user.getPassword();

        if(service.autenticar(ra, password)){
            try {
                user = service.getDados(ra);
                rea.addFlashAttribute("message", "Usuário logado com sucesso!");
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("idUser", user.getId());
                session.setAttribute("nivelUser", user.getNivel());

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
    
    @GetMapping("/vendedor")
    public String showVendedorPage(Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            if ((Integer)session.getAttribute("nivelUser") == 2) {
                Integer idUser = (Integer) session.getAttribute("idUser");
                model.addAttribute("idUser", idUser);
                return "mainPage";
            } else {
                return "redirect:/login";  
            }
        } else {
            return "redirect:/login";  
        }

        
        
    }

    @GetMapping("/admin")
    public String showAdminPage(){
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            if ((Integer)session.getAttribute("nivelUser") == 1) {
                return "admin";
            } else {
                return "redirect:/login";  
            }
        } else {
            return "redirect:/login";  
        }
    }
} 
