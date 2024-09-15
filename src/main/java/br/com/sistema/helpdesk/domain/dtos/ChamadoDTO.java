package br.com.sistema.helpdesk.domain.dtos;

import br.com.sistema.helpdesk.domain.damain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer prioridade;
    private Integer status;
    private String titulo;
    private String observacoes;
    private Integer tecnico;
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(Chamado obj) {
        id = obj.getId();
        dataAbertura = obj.getDataAbertura();
        dataFechamento = obj.getDataFechamento();
        prioridade = obj.getPrioridade().getCod();
        status = obj.getStatus().getCod();
        tecnico = obj.getTecnico().getId();
        cliente = obj.getCliente().getId();
        observacoes = obj.getObservacoes();
        nomeTecnico = obj.getTecnico().getNome();
        nomeCliente = obj.getCliente().getNome();
    }

}
