package com.orussystem.pruebafrutas.service;

import com.orussystem.pruebafrutas.dao.FrutaDAO;
import com.orussystem.pruebafrutas.model.Fruta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrutaService {

    private final FrutaDAO frutaDao;

    @Autowired
    public FrutaService(@Qualifier("h2") FrutaDAO frutaDAO) {
        this.frutaDao = frutaDAO;
    }

    public int addFruta(Fruta fruta) {
        return frutaDao.insertFruta(fruta);
    }

    public List<Fruta> getAllFrutas(){
        return frutaDao.selectAllFrutas();
    }

    public Optional<Fruta> getFrutaByID(String id){
        return frutaDao.selectFrutaById(id);
    }

    public int deleteFrutaById(String id) {
        return frutaDao.deleteFrutaById(id);
    }

    public int updateFrutaById(String id, Fruta newFruta) {
        return frutaDao.updateFrutaByID(id, newFruta);
    }

}
