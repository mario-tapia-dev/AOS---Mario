package com.api.alquilaya.services;

import com.api.alquilaya.models.AlquilerModel;
import com.api.alquilaya.repositories.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    public List<AlquilerModel> obtenerTodos() {
        return alquilerRepository.findAll();
    }

    public AlquilerModel obtenerPorId(Long id) {
        return alquilerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Alquiler no encontrado con id: " + id));
    }

    public AlquilerModel crear(AlquilerModel alquiler) {
        alquiler.setPrecioTotal(calcularPrecioTotal(alquiler));
        return alquilerRepository.save(alquiler);
    }

    public AlquilerModel actualizar(Long id, AlquilerModel alquiler) {
        AlquilerModel existente = obtenerPorId(id);
        existente.setVehiculo(alquiler.getVehiculo());
        existente.setCliente(alquiler.getCliente());
        existente.setFechaAlquiler(alquiler.getFechaAlquiler());
        existente.setFechaDevolucion(alquiler.getFechaDevolucion());
        existente.setPrecioTotal(calcularPrecioTotal(existente));
        return alquilerRepository.save(existente);
    }

    public void eliminar(Long id) {
        AlquilerModel existente = obtenerPorId(id);
        alquilerRepository.delete(existente);
    }

    // El precio total se calcula automáticamente: tarifa diaria del vehículo x días de alquiler.
    private BigDecimal calcularPrecioTotal(AlquilerModel alquiler) {
        long dias = ChronoUnit.DAYS.between(alquiler.getFechaAlquiler(), alquiler.getFechaDevolucion());
        if (dias <= 0) {
            dias = 1;
        }
        return alquiler.getVehiculo().getTarifaDiaria().multiply(BigDecimal.valueOf(dias));
    }
}
