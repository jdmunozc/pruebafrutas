package com.orussystem.pruebafrutas.api;

import com.orussystem.pruebafrutas.model.Fruta;
import com.orussystem.pruebafrutas.model.Pedido;
import com.orussystem.pruebafrutas.service.FrutaService;
import com.orussystem.pruebafrutas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/pedido")
@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RequestMapping("/add")
    @PostMapping
    public void addPedido(@Valid @NonNull @RequestBody Pedido pedido){
        pedidoService.addPedido(pedido);
    }

    @RequestMapping("/all")
    @GetMapping
    public List<Pedido> getAllPedidos(){
        return pedidoService.getAllPedidos();
    }

    @GetMapping(path = "{id}")
    public Pedido getPedidoById(@PathVariable("id") String id){
        return pedidoService.getPedidoById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deletePedidoById(@PathVariable("id") String id) {
        pedidoService.deletePedidoById(id);
    }

    @PutMapping (path = "/update/{id}")
    public void updatePedidoById(@PathVariable("id") String id, @Valid @NonNull @RequestBody Pedido pedidoToUpdate) {
        pedidoService.updatePedidoById(id, pedidoToUpdate);
    }

}
