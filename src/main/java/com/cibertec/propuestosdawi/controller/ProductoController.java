package com.cibertec.propuestosdawi.controller;


import com.cibertec.propuestosdawi.models.Producto;
import com.cibertec.propuestosdawi.repository.ICategoriaRepository;
import com.cibertec.propuestosdawi.repository.IProductoRepository;
import com.cibertec.propuestosdawi.repository.IProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    IProductoRepository repoProducto;

    @Autowired
    ICategoriaRepository repoCategoria;

    @Autowired
    IProveedorRepository repoProveedor;

    @GetMapping("/listado")
    public String listadoProductos(Model model){

        List<Producto> productos = repoProducto.findAll();
        model.addAttribute("productos",productos);
        return  "listadoProductos";
    }

    @GetMapping("/cargar")
    public String cargarFormProducto(Model model){
    model.addAttribute("producto",new Producto());
        model.addAttribute("proveedores",repoProveedor.findAll());
        model.addAttribute("categorias",repoCategoria.findAll());

        return "formProducto";
    }

    @PostMapping("/guardar")
    public String guardarFormProducto(@ModelAttribute(name = "producto") Producto producto, Model model){


        System.out.println(producto.toString());
        if (Objects.isNull(producto)){
            model.addAttribute("proveedores",repoProveedor.findAll());
            model.addAttribute("categorias",repoCategoria.findAll());
            model.addAttribute("mensajeError","No se Envio el Producto");
            return "formProducto";
        }


        Producto obj = repoProducto.save(producto);
        model.addAttribute("proveedores",repoProveedor.findAll());
        model.addAttribute("categorias",repoCategoria.findAll());
        model.addAttribute("mensajeExito","Se Guardó el producto.");

        return "formProducto";
    }

    @GetMapping("/editar/{id}")
    public String cargarProductoFormProducto(@PathVariable String id,Model model){

        Optional<Producto> producto = repoProducto.findById(id);
        System.out.println(producto.toString());
        if (producto.isEmpty()){
            model.addAttribute("proveedores",repoProveedor.findAll());
            model.addAttribute("categorias",repoCategoria.findAll());
            model.addAttribute("producto",new Producto());
            return "redirect:/producto/listado";
        }

        model.addAttribute("producto",producto);
        model.addAttribute("proveedores",repoProveedor.findAll());
        model.addAttribute("categorias",repoCategoria.findAll());
        return "formProducto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable String id,Model model){

        Optional<Producto> producto = repoProducto.findById(id);
        if (producto.isEmpty()){
            return "redirect:/producto/listado";
        }else{
            List<Producto> productos = repoProducto.findAll();
            model.addAttribute("productos",productos);
            repoProducto.delete(producto.get());
            return "redirect:/producto/listado";
        }
    }
}
