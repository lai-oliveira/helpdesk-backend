package br.com.sistema.helpdesk.domain.damain;

import br.com.sistema.helpdesk.domain.dtos.TecnicoDTO;
import br.com.sistema.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Tecnico extends Pessoa {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfis(Perfil.TECNICO);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfis(Perfil.TECNICO);
    }
    public Tecnico(TecnicoDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(Perfil::getCod).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

}
