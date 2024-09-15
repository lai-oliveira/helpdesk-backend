package br.com.sistema.helpdesk.domain.enums;

public enum Status {
    ABERTO(1, "ABERTO"),
    ANDAMENTO(2, "ANDAMENTO"),
    ENCERRADO(3, "ENCERRADO");

    private Integer cod;
    String descricao;

    private Status(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Status perfil : Status.values()) {
            if (cod.equals(perfil.getCod())) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + cod);
    }
}
