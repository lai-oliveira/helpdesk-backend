package br.com.sistema.helpdesk.services;


import javax.validation.Valid;

import br.com.sistema.helpdesk.domain.damain.Pessoa;
import br.com.sistema.helpdesk.domain.damain.Tecnico;
import br.com.sistema.helpdesk.domain.dtos.TecnicoDTO;
import br.com.sistema.helpdesk.repositories.PessoaRepository;
import br.com.sistema.helpdesk.repositories.TecnicoRepository;
import br.com.sistema.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.sistema.helpdesk.services.exceptions.ObjNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TecnicoService {


    private final TecnicoRepository repository;

    private final PessoaRepository pessoaRepository;

    private final BCryptPasswordEncoder encoder;

    public TecnicoService(TecnicoRepository repository, PessoaRepository pessoaRepository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
        this.encoder = encoder;
    }

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundExceptions("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return repository.save(newObj);
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico oldObj = findById(id);

        if(!objDTO.getSenha().equals(oldObj.getSenha()))
            objDTO.setSenha(encoder.encode(objDTO.getSenha()));

        validaPorCpfEEmail(objDTO);
        oldObj = new Tecnico(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);

        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }

        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

}