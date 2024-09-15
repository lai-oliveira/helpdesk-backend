package br.com.sistema.helpdesk.services;

import br.com.sistema.helpdesk.domain.damain.Chamado;
import br.com.sistema.helpdesk.repositories.ChamadoRepository;
import br.com.sistema.helpdesk.services.exceptions.ObjNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundExceptions("Chamado não encontrado. Id: " + id));
    }
}
