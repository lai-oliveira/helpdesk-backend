package br.com.sistema.helpdesk.services;

import br.com.sistema.helpdesk.domain.damain.Chamado;
import br.com.sistema.helpdesk.domain.damain.Cliente;
import br.com.sistema.helpdesk.domain.damain.Tecnico;
import br.com.sistema.helpdesk.domain.dtos.ChamadoDTO;
import br.com.sistema.helpdesk.domain.enums.Prioridade;
import br.com.sistema.helpdesk.domain.enums.Status;
import br.com.sistema.helpdesk.repositories.ChamadoRepository;
import br.com.sistema.helpdesk.services.exceptions.ObjNotFoundExceptions;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final TecnicoService tecnicoService;
    private final ClienteService clienteService;

    public ChamadoService(ChamadoRepository chamadoRepository, TecnicoService tecnicoService, ClienteService clienteService) {
        this.chamadoRepository = chamadoRepository;
        this.tecnicoService = tecnicoService;
        this.clienteService = clienteService;
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

    public Chamado create(ChamadoDTO objDTO) {
        return chamadoRepository.save(newChamado(objDTO));
    }

    public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);
        return chamadoRepository.save(oldObj);

    }

    private Chamado newChamado(ChamadoDTO objDTO) {
        Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());
        Cliente cliente = clienteService.findById(objDTO.getCliente());

        Chamado chamado = new Chamado();
        if (objDTO.getId() != null){
            chamado.setId(objDTO.getId());
        }

        if(objDTO.getStatus().equals(2)){
            //Data atual de encerramento do chamado
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(objDTO.getStatus()));
        chamado.setTitulo(objDTO.getTitulo());
        chamado.setObservacoes(objDTO.getObservacoes());
        return chamado;
    }


}
