package MilkStgo.Tingeso1.controllers;

import MilkStgo.Tingeso1.entities.PagoEntity;
import MilkStgo.Tingeso1.servicies.LlegadaService;
import MilkStgo.Tingeso1.servicies.PagoService;
import MilkStgo.Tingeso1.servicies.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping
public class PagoController {
    @Autowired
    private PagoService pagoService;
    @Autowired
    private LlegadaService llegadaService;
    @Autowired
    private ResultadoService resultadoService;

    @GetMapping("/subirDosArchivo")
    public String subirDosArchivo(){
        return "subirDosArchivo";
    }

    @PostMapping("/subirDosArchivo")
    public String upload(@RequestParam("fileLlegada") MultipartFile archivoLlegada,
                         @RequestParam("fileResultado")MultipartFile archivoResultado, RedirectAttributes redirectAttributes){
        llegadaService.guardar(archivoLlegada);
        llegadaService.guardar(archivoResultado);
        String mensajeLlegada = llegadaService.leerCsv("Acopio.csv");
        String mensajeResultado = resultadoService.leerCsv("Resultados.csv");
        redirectAttributes.addFlashAttribute("mensajeLlegada", mensajeLlegada );
        redirectAttributes.addFlashAttribute("mensajeResultado", mensajeResultado);
        pagoService.crearAll();
        return "redirect:/opcionesGestion";
    }

    @GetMapping("/plantillasPago/{codigo}")
    public String showPlantilla(Model model, @PathVariable("codigo") String codigo){
        List<PagoEntity> pago = pagoService.buscarPagos(codigo);
        Collections.reverse(pago);
        model.addAttribute("pago", pago);
        return "plantillasPago";
    }

}
