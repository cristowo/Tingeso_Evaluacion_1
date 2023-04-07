package MilkStgo.Tingeso1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/")
    public String main(){
        return "index";
    }

    @GetMapping("/opcionesGestion")
    public String llegadaResultado(){
        return "opcionesGestion";
    }

}