package com.api.alquilaya.services;

import com.api.alquilaya.models.MantenimientoModel;
import com.api.alquilaya.repositories.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    public List<MantenimientoModel> obtenerTodos() {
        return mantenimientoRepository.findAll();
    }

    public MantenimientoModel obtenerPorId(Long id) {
        return mantenimientoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Mantenimiento no encontrado con id: " + id));
    }

    public MantenimientoModel crear(MantenimientoModel mantenimiento) {
        return mantenimientoRepository.save(mantenimiento);
    }

    public MantenimientoModel actualizar(Long id, MantenimientoModel mantenimiento) {
        MantenimientoModel existente = obtenerPorId(id);
        existente.setVehiculo(mantenimiento.getVehiculo());
        existente.setFechaMantenimiento(mantenimiento.getFechaMantenimiento());
        existente.setDescripcion(mantenimiento.getDescripcion());
        existente.setCosto(mantenimiento.getCosto());
        return mantenimientoRepository.save(existente);
    }

    public void eliminar(Long id) {
        MantenimientoModel existente = obtenerPorId(id);
        mantenimientoRepository.delete(existente);
    }
}
