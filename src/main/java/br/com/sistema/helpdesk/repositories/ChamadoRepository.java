package br.com.sistema.helpdesk.repositories;

import br.com.sistema.helpdesk.domain.damain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
