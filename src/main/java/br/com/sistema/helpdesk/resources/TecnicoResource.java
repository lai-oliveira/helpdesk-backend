package br.com.sistema.helpdesk.resources;

import br.com.sistema.helpdesk.domain.damain.Tecnico;
import br.com.sistema.helpdesk.domain.dtos.TecnicoDTO;
import br.com.sistema.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    private final TecnicoService tecnicoService;

    public TecnicoResource(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico tecnico = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));

    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(TecnicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
        Tecnico newObj = tecnicoService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico obj = tecnicoService.update(id,objDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
