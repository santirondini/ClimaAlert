package ar.edu.utn.frba.ddsi.common.models.repositories;

import ar.edu.utn.frba.ddsi.common.models.entities.RegistroClima;

public interface IClimaRepository {
    void guardarRegistroClimatico(RegistroClima registro);
    RegistroClima obtenerUltimoRegistro();
}
