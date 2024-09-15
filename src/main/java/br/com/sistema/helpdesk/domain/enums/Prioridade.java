package br.com.sistema.helpdesk.domain.enums;

public enum Prioridade {
    BAIXA(1, "BAIXA"),
    MEDIA(2, "MEDIA"),
    ALTA(3, "ALTA");

    private Integer cod;
    String descricao;

    private Prioridade(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Prioridade perfil : Prioridade.values()) {
            if (cod.equals(perfil.getCod())) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Prioridade inválido: " + cod);
    }
}
