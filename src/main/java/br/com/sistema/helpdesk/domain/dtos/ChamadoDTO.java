package br.com.sistema.helpdesk.domain.dtos;

import br.com.sistema.helpdesk.domain.damain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    @NotNull(message = "O campo PRIORIDADE é requerido")
    private Integer prioridade;

    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;

    @NotNull(message = "O campo TITULO é requerido")
    private String titulo;

    @NotNull(message = "O campo OBSERVACOES é requerido")
    private String observacoes;

    @NotNull(message = "O campo TECNICO é requerido")
    private Integer tecnico;

    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCod();
        this.status = obj.getStatus().getCod();
        this.titulo = obj.getTitulo();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.observacoes = obj.getObservacoes();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }

    @Override
    public String toString() {
        return "ChamadoDTO{" +
                "id=" + id +
                ", dataAbertura=" + dataAbertura +
                ", dataFechamento=" + dataFechamento +
                ", prioridade=" + prioridade +
                ", status=" + status +
                ", titulo='" + titulo + '\'' +
                ", observacoes='" + observacoes + '\'' +
                ", tecnico=" + tecnico +
                ", cliente=" + cliente +
                ", nomeTecnico='" + nomeTecnico + '\'' +
                ", nomeCliente='" + nomeCliente + '\'' +
                '}';
    }
}
