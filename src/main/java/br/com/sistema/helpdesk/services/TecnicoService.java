package br.com.sistema.helpdesk.services;

import br.com.sistema.helpdesk.domain.damain.Tecnico;
import br.com.sistema.helpdesk.repositories.TecnicoRepository;
import br.com.sistema.helpdesk.services.exceptions.ObjNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundExceptions("Objeto não encontrado. Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }
}
