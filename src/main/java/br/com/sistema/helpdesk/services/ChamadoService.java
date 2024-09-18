package br.com.sistema.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.sistema.helpdesk.domain.damain.Chamado;
import br.com.sistema.helpdesk.domain.damain.Cliente;
import br.com.sistema.helpdesk.domain.damain.Tecnico;
import br.com.sistema.helpdesk.domain.dtos.ChamadoDTO;
import br.com.sistema.helpdesk.domain.enums.Prioridade;
import br.com.sistema.helpdesk.domain.enums.Status;
import br.com.sistema.helpdesk.repositories.ChamadoRepository;
import br.com.sistema.helpdesk.resources.exceptions.ObjectnotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ChamadoService {


    private final ChamadoRepository repository;

    private final TecnicoService tecnicoService;

    private final ClienteService clienteService;

    public ChamadoService(ChamadoRepository repository, TecnicoService tecnicoService, ClienteService clienteService) {
        this.repository = repository;
        this.tecnicoService = tecnicoService;
        this.clienteService = clienteService;
    }

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(ChamadoDTO obj) {
        return repository.save(newChamado(obj));
    }

    public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);
        return repository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDTO obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if(obj.getId() != null) {
            chamado.setId(obj.getId());
        }

        if(obj.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }

}