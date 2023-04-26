package com.cibertec.propuestosdawi.controller;

import com.cibertec.propuestosdawi.models.Usuario;
import com.cibertec.propuestosdawi.repository.IUsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    IUsuarioRespository repoUsuario;

    @GetMapping("/listado")
    public String listadoUsuarios(Model model) {
        model.addAttribute("usuarios", repoUsuario.findAll());
        return "listadoUsuarios";
    }

    @GetMapping("/cargar")
    public String cargarFormUsuario(Model model) {

        model.addAttribute("usuario", new Usuario());
        return "formUsuario";
    }

    @PostMapping("/guardar")
    public String guardaUsuario(@ModelAttribute(name = "usuario") Usuario usuario, Model model) {

        if (Objects.isNull(usuario)) {
            model.addAttribute("mensajeError", "No se Envio el Usuario.");
            return "formUsuario";
        }
        repoUsuario.save(usuario);
        model.addAttribute("mensajeExito", "Se Guardó el usuario.");
        return "formUsuario";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable int id, Model model) {
        Optional<Usuario> usuario = repoUsuario.findById(id);

        if (usuario.isEmpty()) {
            model.addAttribute("usuario", new Usuario());
            return "redirect:/usuario/listado";
        }
        model.addAttribute("usuario", usuario);
        return "formUsuario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id, Model model) {
        Optional<Usuario> usuario = repoUsuario.findById(id);
        if (usuario.isEmpty()){
            return "redirect:/usuario/listado";
        }
        repoUsuario.delete(usuario.get());
        return "redirect:/usuario/listado";
    }
}
