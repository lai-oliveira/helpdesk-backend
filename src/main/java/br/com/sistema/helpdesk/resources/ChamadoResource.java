package br.com.sistema.helpdesk.resources;

import br.com.sistema.helpdesk.domain.damain.Chamado;
import br.com.sistema.helpdesk.domain.dtos.ChamadoDTO;
import br.com.sistema.helpdesk.services.ChamadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    private final ChamadoService chamadoService;

    public ChamadoResource(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

}
