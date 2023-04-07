package MilkStgo.Tingeso1.controllers;

import MilkStgo.Tingeso1.entities.LlegadaEntity;
import MilkStgo.Tingeso1.entities.ResultadoEntity;
import MilkStgo.Tingeso1.servicies.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping
public class ResultadoController {
    @Autowired
    private ResultadoService resultadoService;

    //@GetMapping("/subirArchivo") -> esta en Llegada

    @PostMapping("/subirArchivoResultado")
    public String upload(@RequestParam("file") MultipartFile archivo, RedirectAttributes redirectAttributes){
        resultadoService.guardarResultado(archivo);
        redirectAttributes.addFlashAttribute("mensajeResultado", resultadoService.leerCsv("Resultados.csv"));
        return "redirect:/subirArchivo";
    }

    @GetMapping("/informacionArchivoResultado")
    public String listar(Model model){
        ArrayList<ResultadoEntity> datos = resultadoService.obtenerDatosResultado();
        model.addAttribute("datos", datos);
        return "informacionArchivoResultado";
    }
}
