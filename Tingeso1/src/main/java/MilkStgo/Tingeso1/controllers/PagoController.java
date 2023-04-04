package MilkStgo.Tingeso1.controllers;

import MilkStgo.Tingeso1.servicies.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PagoController {
    @Autowired
    private PagoService pagoService;
}
