package MilkStgo.Tingeso1.controllers;

import MilkStgo.Tingeso1.entities.ProveedorEntity;
import MilkStgo.Tingeso1.servicies.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    // Direccionamiento hacia la pagina opcionesProveedor
    @GetMapping("/opcionesProveedor")
    public String opcionesProveedorMap(){return "opcionesProveedor";}

    // Creaciones de nuevos Proveedores en la base de datos
    @GetMapping("/nuevoProveedor")
    public String provedor(){return "nuevoProveedor";}
    @PostMapping("/crearNuevoProveedor")
    public String crearNuevoProveedor(@RequestParam("nombre") String nombre,
                                  @RequestParam("codigo") String codigo,
                                  @RequestParam("categoria") String categoria,
                                  @RequestParam("retencion") String retencion){
        proveedorService.guardarProveedor(nombre, codigo, categoria, retencion);
        return "redirect:/listaProveedores";
    }

    // Mostrar Listas de los proveedores existentes en la base de datos
    @GetMapping("/listaProveedores")
    public String listar(Model model){
        List<ProveedorEntity> proveedores = proveedorService.showListaProveedores();
        //asigna un nombre utilizado para poder verse en la vista
        model.addAttribute("proveedores", proveedores);
        return "listaProveedores";
    }

}
