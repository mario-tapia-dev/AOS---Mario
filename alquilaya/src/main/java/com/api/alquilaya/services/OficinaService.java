package com.api.alquilaya.services;

import com.api.alquilaya.models.OficinaModel;
import com.api.alquilaya.repositories.OficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OficinaService {

    @Autowired
    private OficinaRepository oficinaRepository;

    public List<OficinaModel> obtenerTodos() {
        return oficinaRepository.findAll();
    }

    public OficinaModel obtenerPorId(Long id) {
        return oficinaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Oficina no encontrada con id: " + id));
    }

    public OficinaModel crear(OficinaModel oficina) {
        return oficinaRepository.save(oficina);
    }

    public OficinaModel actualizar(Long id, OficinaModel oficina) {
        OficinaModel existente = obtenerPorId(id);
        existente.setNombreOficina(oficina.getNombreOficina());
        existente.setDireccion(oficina.getDireccion());
        existente.setCiudad(oficina.getCiudad());
        existente.setRegion(oficina.getRegion());
        return oficinaRepository.save(existente);
    }

    public void eliminar(Long id) {
        OficinaModel existente = obtenerPorId(id);
        oficinaRepository.delete(existente);
    }
}
