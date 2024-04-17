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

    @GetMapping("/{id}")
    public ResponseEntity<CachorroEntity> getCachorroById(@PathVariable Long id) {
        CachorroEntity cachorro = cachorroService.getCachorroById(id);
        if (cachorro == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cachorro, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CachorroEntity> saveCachorro(@RequestBody CachorroEntity cachorro) {
        CachorroEntity savedCachorro = cachorroService.saveOrUpdateCachorro(cachorro);
        return new ResponseEntity<>(savedCachorro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CachorroEntity> updateCachorro(@PathVariable Long id, @RequestBody CachorroEntity cachorro) {
        CachorroEntity existingCachorro = cachorroService.getCachorroById(id);
        if (existingCachorro == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cachorro.setId(id);
        CachorroEntity updatedCachorro = cachorroService.saveOrUpdateCachorro(cachorro);
        return new ResponseEntity<>(updatedCachorro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCachorro(@PathVariable Long id) {
        CachorroEntity existingCachorro = cachorroService.getCachorroById(id);
        if (existingCachorro == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cachorroService.deleteCachorro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
