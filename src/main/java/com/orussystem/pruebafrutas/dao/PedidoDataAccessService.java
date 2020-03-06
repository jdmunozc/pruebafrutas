package com.orussystem.pruebafrutas.dao;

import com.orussystem.pruebafrutas.model.Fruta;
import com.orussystem.pruebafrutas.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("h2p")
public class PedidoDataAccessService implements PedidoDAO{

    @Autowired
    private JdbcTemplate template;

    @Override
    public int insertPedido(String id, Pedido pedido) {
        template.execute("INSERT INTO PEDIDO VALUES('"+id+"', "+pedido.getCantidad()+",'"+pedido.getFrutaId()+"',"+pedido.getPrecio()+")");
        return 1;
    }

    @Override
    public List<Pedido> selectAllPedidos() {
        String sql = "SELECT * FROM PEDIDO";
        List<Pedido> pedidos = template.query(sql,
                (rs, rowNum) ->
                        new Pedido(
                                rs.getString("id"),
                                rs.getInt("cantidad"),
                                rs.getString("fruta_Id"),
                                rs.getDouble("precio")
                        ));
        return pedidos;
    }

    @Override
    public Optional<Pedido> selectPedidoById(String id) {
        List<Pedido> DB = selectAllPedidos();
        return DB.stream()
                .filter(pedido -> pedido.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePedidoById(String id) {
        Optional<Pedido> pedidoMaybe = selectPedidoById(id);
        if (pedidoMaybe.isEmpty()){
            return 0;
        }
        String sql = "DELETE FROM PEDIDO WHERE ID='"+pedidoMaybe.get().getId()+"'";
        template.execute(sql);
        return 1;
    }

    @Override
    public int updatePedidoByID(String id, Pedido update) {
        return selectPedidoById(id)
                .map(pedido -> {
                    if (pedido.equals(null)){
                        return 0;
                    }
                    String sql = "UPDATE PEDIDO SET CANTIDAD ="+update.getCantidad()+", FRUTA_ID ='"+update.getFrutaId()+"', PRECIO = "+update.getPrecio()+" WHERE ID = '"+pedido.getId()+"'";
                    template.execute(sql);
                    return 1;
                })
                .orElse(0);
    }
}
