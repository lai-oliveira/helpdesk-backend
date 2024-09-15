package br.com.sistema.helpdesk.services;

import br.com.sistema.helpdesk.domain.damain.Tecnico;
import br.com.sistema.helpdesk.repositories.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElse(null);
    }
}
