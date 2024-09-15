package br.com.sistema.helpdesk.resources;

import br.com.sistema.helpdesk.domain.damain.Tecnico;
import br.com.sistema.helpdesk.services.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    private final TecnicoService tecnicoService;

    public TecnicoResource(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tecnico> findById(@PathVariable Integer id) {
        try {
            Tecnico tecnico = tecnicoService.findById(id);
            return ResponseEntity.ok().body(tecnico);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
