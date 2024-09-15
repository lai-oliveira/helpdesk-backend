package br.com.sistema.helpdesk.services;

import br.com.sistema.helpdesk.domain.damain.Pessoa;
import br.com.sistema.helpdesk.domain.damain.Tecnico;
import br.com.sistema.helpdesk.domain.dtos.TecnicoDTO;
import br.com.sistema.helpdesk.repositories.PessoaRepository;
import br.com.sistema.helpdesk.repositories.TecnicoRepository;
import br.com.sistema.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.sistema.helpdesk.services.exceptions.ObjNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final PessoaRepository pessoaRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository,
                          PessoaRepository pessoaRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundExceptions("Objeto não encontrado. Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorcpfEEmail(objDTO);
        Tecnico obj = new Tecnico(objDTO);
        return tecnicoRepository.save(obj);
    }

    private void validaPorcpfEEmail(TecnicoDTO objDTO) {
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
