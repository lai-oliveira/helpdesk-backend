package br.com.sistema.helpdesk.services;

import java.util.Optional;

import br.com.sistema.helpdesk.domain.damain.Pessoa;
import br.com.sistema.helpdesk.repositories.PessoaRepository;
import br.com.sistema.helpdesk.security.UserSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{


    private final PessoaRepository repository;

    public UserDetailsServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = repository.findByEmail(email);
        if(user.isPresent()) {
            return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
        }
        throw new UsernameNotFoundException(email);
    }

}