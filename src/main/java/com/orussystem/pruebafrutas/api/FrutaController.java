package com.orussystem.pruebafrutas.api;

import com.orussystem.pruebafrutas.model.Fruta;
import com.orussystem.pruebafrutas.service.FrutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/fruta")
@RestController
public class FrutaController {

    private final FrutaService frutaService;

    @Autowired
    public FrutaController(FrutaService frutaService) {
        this.frutaService = frutaService;
    }

    @RequestMapping("/add")
    @PostMapping
    public void addFruta(@Valid @NonNull @RequestBody Fruta fruta){
        frutaService.addFruta(fruta);
    }

    @RequestMapping("/all")
    @GetMapping
    public List<Fruta> getAllPersons(){
        return frutaService.getAllFrutas();
    }

    @GetMapping(path = "{id}")
    public Fruta getFrutaByID(@PathVariable("id") String id){
        return frutaService.getFrutaByID(id)
                .orElse(null);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteFrutaById(@PathVariable("id") String id) {
        frutaService.deleteFrutaById(id);
    }

    @PutMapping (path = "/update/{id}")
    public void updateFrutaById(@PathVariable("id") String id, @Valid @NonNull @RequestBody Fruta frutaToUpdate) {
        frutaService.updateFrutaById(id, frutaToUpdate);
    }
}
