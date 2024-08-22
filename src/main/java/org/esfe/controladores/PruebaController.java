package org.esfe.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {
    @GetMapping
    public String probar(){
        return "Éxito";
    }
}
