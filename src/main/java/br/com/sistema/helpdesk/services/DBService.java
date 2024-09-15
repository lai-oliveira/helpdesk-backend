package br.com.sistema.helpdesk.services;

import br.com.sistema.helpdesk.domain.damain.Chamado;
import br.com.sistema.helpdesk.domain.damain.Cliente;
import br.com.sistema.helpdesk.domain.damain.Tecnico;
import br.com.sistema.helpdesk.domain.enums.Perfil;
import br.com.sistema.helpdesk.domain.enums.Prioridade;
import br.com.sistema.helpdesk.domain.enums.Status;
import br.com.sistema.helpdesk.repositories.ChamadoRepository;
import br.com.sistema.helpdesk.repositories.ClienteRepository;
import br.com.sistema.helpdesk.repositories.TecnicoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class DBService {

    private final TecnicoRepository tecnicoRepository;
    private final ChamadoRepository chamadoRepository;
    private final ClienteRepository clienteRepository;

    public DBService(TecnicoRepository tecnicoRepository, ChamadoRepository chamadoRepository, ClienteRepository clienteRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.chamadoRepository = chamadoRepository;
        this.clienteRepository = clienteRepository;
    }


    public void instantiateTestDatabase() {

        Tecnico tec1 = new Tecnico(null, "Rogerio Silva", "111.111.111-11", "rogerio@mail.com", "123");
        tec1.addPerfis(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Mariana Silva", "175.111.111-12", "mariana@mail.com", "321");
        cli1.addPerfis(Perfil.CLIENTE);

        Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Primeiro chamado", tec1, cli1);
        log.info("CHAMADO INICIADO [{}]",chamado1.toString());
        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(chamado1));
    }
}
