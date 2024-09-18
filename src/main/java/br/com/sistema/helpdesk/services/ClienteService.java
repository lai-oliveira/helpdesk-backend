package br.com.sistema.helpdesk.services;

import br.com.sistema.helpdesk.domain.damain.Pessoa;
import br.com.sistema.helpdesk.domain.damain.Cliente;
import br.com.sistema.helpdesk.domain.dtos.ClienteDTO;
import br.com.sistema.helpdesk.repositories.PessoaRepository;
import br.com.sistema.helpdesk.repositories.ClienteRepository;
import br.com.sistema.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.sistema.helpdesk.services.exceptions.ObjNotFoundExceptions;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PessoaRepository pessoaRepository;

    public ClienteService(ClienteRepository clienteRepository,
                          PessoaRepository pessoaRepository) {
        this.clienteRepository = clienteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundExceptions("Objeto não encontrado. Id: " + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        validaPorcpfEEmail(objDTO);
        Cliente obj = new Cliente(objDTO);
        return clienteRepository.save(obj);
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
        objDTO.setId(id);
        findById(id);
        Cliente oldObj;
        validaPorcpfEEmail(objDTO);
        oldObj = new Cliente(objDTO);
        return clienteRepository.save(oldObj);

    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (!obj.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço. Id: " + obj.getId());
        }
        clienteRepository.deleteById(id);
    }

    private void validaPorcpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())){
            throw new DataIntegrityViolationException("CPF já cadastrado. Id: " + obj.get().getId());
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())){
            throw new DataIntegrityViolationException("Email já cadastrado. Id: " + obj.get().getId());
        }
    }


}
