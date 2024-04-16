package app.AbrigoCanino.controllers;

import app.AbrigoCanino.entities.CachorroEntity;
import app.AbrigoCanino.service.CachorroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cachorros")
public class CachorroController {

    @Autowired
    private CachorroService cachorroService;

    @GetMapping
    public ResponseEntity<List<CachorroEntity>> getAllCachorros() {
        List<CachorroEntity> cachorros = cachorroService.getAllCachorros();
        return new ResponseEntity<>(cachorros, HttpStatus.OK);
    }


}
