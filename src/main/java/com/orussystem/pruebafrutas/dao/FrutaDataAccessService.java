package com.orussystem.pruebafrutas.dao;

import com.orussystem.pruebafrutas.model.Fruta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("h2")
public class FrutaDataAccessService implements FrutaDAO{

    @Autowired
    private JdbcTemplate template;

    @Override
    public int insertFruta(String id, Fruta fruta) {
        template.execute("INSERT INTO FRUTA VALUES('"+id+"', "+fruta.getCantidad()+","+fruta.getPrecio()+",'"+fruta.getTipo()+"')");
        return 1;
    }

    @Override
    public List<Fruta> selectAllFrutas() {
        String sql = "SELECT * FROM FRUTA";
        List<Fruta> frutas = template.query(sql,
                (rs, rowNum) ->
                        new Fruta(
                                rs.getString("id"),
                                rs.getString("tipo"),
                                rs.getInt("cantidad"),
                                rs.getInt("precio")
                        ));
        return frutas;
    }

    @Override
    public Optional<Fruta> selectFrutaById(String id) {
        List<Fruta> DB = selectAllFrutas();
        return DB.stream()
                .filter(fruta -> fruta.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteFrutaById(String id) {
        Optional<Fruta> frutaMaybe = selectFrutaById(id);
        if (frutaMaybe.isEmpty()){
            return 0;
        }
        String sql = "DELETE FROM FRUTA WHERE ID='"+frutaMaybe.get().getId()+"'";
        template.execute(sql);
        return 1;
    }

    @Override
    public int updateFrutaByID(String id, Fruta update) {
        return selectFrutaById(id)
                .map(fruta -> {
                    if (fruta.equals(null)){
                        return 0;
                    }
                    String sql = "UPDATE FRUTA SET CANTIDAD ="+update.getCantidad()+", PRECIO ="+update.getPrecio()+", TIPO = '"+update.getTipo()+"' WHERE ID = '"+fruta.getId()+"'";
                    template.execute(sql);
                    return 1;
                })
                .orElse(0);
    }

}
