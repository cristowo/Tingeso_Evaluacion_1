package MilkStgo.Tingeso1.controllers;

import MilkStgo.Tingeso1.entities.LlegadaEntity;
import MilkStgo.Tingeso1.servicies.LlegadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping
public class LlegadaController {
    @Autowired
    private LlegadaService llegadaService;

    @GetMapping("/subirArchivo")
    public String main(){
        return "subirArchivo";
    }

    @PostMapping("/subirArchivo")
    public String upload(@RequestParam("file")MultipartFile archivo, RedirectAttributes redirectAttributes){
        llegadaService.guardar(archivo);
        redirectAttributes.addFlashAttribute("mensaje", llegadaService.leerCsv("Acopio.csv"));
        return "redirect:/subirArchivo";
    }

    @GetMapping("/informacionArchivo")
    public String listar(Model model){
        ArrayList<LlegadaEntity> datos = llegadaService.obtenerDatos();
        model.addAttribute("datos", datos);
        return "informacionArchivo";
    }
}
