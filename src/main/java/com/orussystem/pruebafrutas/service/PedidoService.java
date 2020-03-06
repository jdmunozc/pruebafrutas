package com.orussystem.pruebafrutas.service;

import com.orussystem.pruebafrutas.dao.FrutaDAO;
import com.orussystem.pruebafrutas.dao.PedidoDAO;
import com.orussystem.pruebafrutas.model.Fruta;
import com.orussystem.pruebafrutas.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoDAO pedidoDao;
    private final FrutaDAO frutaDao;

    @Autowired
    public PedidoService(@Qualifier("h2p") PedidoDAO pedidoDAO, FrutaDAO frutaDao) {
        this.pedidoDao = pedidoDAO;
        this.frutaDao = frutaDao;
    }

    public int addPedido(Pedido pedido) {
        pedido.setPrecio(0);
        Optional<Fruta> frutaAsociada = frutaDao.selectFrutaById(pedido.getFrutaId());
        final Optional<Integer> integer = frutaAsociada.map(
                fruta -> {
                    pedido.setPrecio(fruta.getPrecio() * pedido.getCantidad());
                    if (pedido.getCantidad() > 5) {
                        pedido.setPrecio(0.9 * pedido.getPrecio());
                    }
                    return pedidoDao.insertPedido(pedido);
                });
        return 0;
    }

    public List<Pedido> getAllPedidos(){
        return pedidoDao.selectAllPedidos();
    }

    public Optional<Pedido> getPedidoById(String id){
        return pedidoDao.selectPedidoById(id);
    }

    public int deletePedidoById(String id) {
        return pedidoDao.deletePedidoById(id);
    }

    public int updatePedidoById(String id, Pedido newPedido) {
        newPedido.setPrecio(0);
        Optional<Fruta> frutaAsociada = frutaDao.selectFrutaById(newPedido.getFrutaId());
        final Optional<Integer> integer = frutaAsociada.map(
                fruta -> {
                    newPedido.setPrecio(fruta.getPrecio() * newPedido.getCantidad());
                    if (newPedido.getCantidad() > 5) {
                        newPedido.setPrecio(0.9 * newPedido.getPrecio());
                    }
                    return pedidoDao.updatePedidoByID(id, newPedido);
                });
        return 0;
    }

}
