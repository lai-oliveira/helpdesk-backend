package br.com.sistema.helpdesk.repositories;

import br.com.sistema.helpdesk.domain.damain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
