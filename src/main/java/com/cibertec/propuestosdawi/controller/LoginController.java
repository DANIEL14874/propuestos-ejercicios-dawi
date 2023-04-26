package com.cibertec.propuestosdawi.controller;

import com.cibertec.propuestosdawi.models.Login;
import com.cibertec.propuestosdawi.models.Usuario;
import com.cibertec.propuestosdawi.repository.IUsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    IUsuarioRespository repoUsuario;

    @GetMapping("/logeo")
    public String cargarLogin(Model model){
        model.addAttribute("Credenciales",new Login());
        return "login";
    }

    @PostMapping("/sign")
    public String entrarProductos(@ModelAttribute(name = "Credenciales")Login login,Model model){
        System.out.println(login.getUsername());
        System.out.println(login.getPassword());

        Optional<Usuario> obj = repoUsuario.findByUsernameAndPassword(login.getUsername(),login.getPassword());
        System.out.println(obj.toString());
        if (obj.isEmpty()){
            model.addAttribute("MensajeError","No existen las credenciales");
            return "login";
        }

        return "redirect:/producto/listado";
    }

}
