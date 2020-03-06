package com.orussystem.pruebafrutas.dao;

import com.orussystem.pruebafrutas.model.Fruta;
import com.orussystem.pruebafrutas.model.Pedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoDAO {

    int insertPedido(String id, Pedido pedido);

    default int insertPedido(Pedido pedido){
        UUID id = UUID.randomUUID();
        return insertPedido(id.toString(), pedido);
    }

    List<Pedido> selectAllPedidos();

    Optional<Pedido> selectPedidoById(String id);

    int deletePedidoById(String id);

    int updatePedidoByID(String id, Pedido update);

}
