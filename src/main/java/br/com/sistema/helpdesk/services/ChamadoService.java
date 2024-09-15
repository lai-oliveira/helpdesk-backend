package br.com.sistema.helpdesk.services;

import br.com.sistema.helpdesk.domain.damain.Chamado;
import br.com.sistema.helpdesk.repositories.ChamadoRepository;
import br.com.sistema.helpdesk.services.exceptions.ObjNotFoundExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundExceptions("Chamado não encontrado. Id: " + id));
    }

    public List<Chamado> findAll() {
        List<Chamado> chamado = chamadoRepository.findAll();
        log.info("Buscando todos os chamados [{}]", chamado.toString());
        return chamado;
    }
}
