package com.api.alquilaya.services;

import com.api.alquilaya.models.ClienteModel;
import com.api.alquilaya.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteModel> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public ClienteModel obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado con id: " + id));
    }

    public ClienteModel crear(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public ClienteModel actualizar(Long id, ClienteModel cliente) {
        ClienteModel existente = obtenerPorId(id);
        existente.setNombre(cliente.getNombre());
        existente.setApellido(cliente.getApellido());
        existente.setEmail(cliente.getEmail());
        existente.setLicenciaConducir(cliente.getLicenciaConducir());
        existente.setTelefono(cliente.getTelefono());
        return clienteRepository.save(existente);
    }

    public void eliminar(Long id) {
        ClienteModel existente = obtenerPorId(id);
        clienteRepository.delete(existente);
    }
}
