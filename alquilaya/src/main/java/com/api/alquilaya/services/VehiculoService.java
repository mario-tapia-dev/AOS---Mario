package com.api.alquilaya.services;

import com.api.alquilaya.models.VehiculoModel;
import com.api.alquilaya.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<VehiculoModel> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    public VehiculoModel obtenerPorId(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vehículo no encontrado con id: " + id));
    }

    public VehiculoModel crear(VehiculoModel vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public VehiculoModel actualizar(Long id, VehiculoModel vehiculo) {
        VehiculoModel existente = obtenerPorId(id);
        existente.setMarca(vehiculo.getMarca());
        existente.setModelo(vehiculo.getModelo());
        existente.setAnio(vehiculo.getAnio());
        existente.setMatricula(vehiculo.getMatricula());
        existente.setTarifaDiaria(vehiculo.getTarifaDiaria());
        existente.setEstado(vehiculo.getEstado());
        existente.setOficina(vehiculo.getOficina());
        return vehiculoRepository.save(existente);
    }

    public void eliminar(Long id) {
        VehiculoModel existente = obtenerPorId(id);
        vehiculoRepository.delete(existente);
    }
}
