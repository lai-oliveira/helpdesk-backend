package br.com.sistema.helpdesk.repositories;

import br.com.sistema.helpdesk.domain.damain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
