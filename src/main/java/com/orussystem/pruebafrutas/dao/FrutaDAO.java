package com.orussystem.pruebafrutas.dao;

import com.orussystem.pruebafrutas.model.Fruta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FrutaDAO {

    int insertFruta(String id, Fruta fruta);

    default int insertFruta(Fruta fruta){
        UUID id = UUID.randomUUID();
        return insertFruta(id.toString(), fruta);
    }

    List<Fruta> selectAllFrutas();

    Optional<Fruta> selectFrutaById(String id);

    int deleteFrutaById(String id);

    int updateFrutaByID(String id, Fruta person);

}
