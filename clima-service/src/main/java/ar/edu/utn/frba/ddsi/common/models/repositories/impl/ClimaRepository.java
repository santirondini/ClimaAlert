package ar.edu.utn.frba.ddsi.common.models.repositories.impl;

import ar.edu.utn.frba.ddsi.common.models.entities.RegistroClima;
import ar.edu.utn.frba.ddsi.common.models.repositories.IClimaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClimaRepository implements IClimaRepository{

    private List<RegistroClima> registroClimas;

    public void guardarRegistroClimatico(RegistroClima registro) {
        this.registroClimas.add(registro);
    }
}
