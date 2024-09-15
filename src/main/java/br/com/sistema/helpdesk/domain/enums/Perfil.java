package br.com.sistema.helpdesk.domain.enums;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE"),
    TECNICO(3, "ROLE_TECNICO");

    private Integer cod;
    String descricao;

    private Perfil(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Perfil perfil : Perfil.values()) {
            if (cod.equals(perfil.getCod())) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Perfil inválido: " + cod);
    }
}
