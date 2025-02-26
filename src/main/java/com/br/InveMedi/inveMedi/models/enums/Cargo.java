package com.br.InveMedi.inveMedi.models.enums;

public enum Cargo {
    ADMIN(1, "Administrador"),
    FARMACEUTICO(2, "Farmacêutico"),
    ESTOQUISTA(3, "Estoquista");


    private final int codigo;
    private final String descricao;


    Cargo(int i, String descricao) {
        this.codigo = i;
        this.descricao = descricao;
    }

    public int getCodigo(){
        return codigo;
    }

    public String getDescricao(){
        return descricao;
    }

    public static Cargo porCodigo(int codigo) {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.getCodigo() == codigo) {
                return cargo;
            }
        }
        throw new IllegalArgumentException("Código de cargo inválido: " + codigo);
    }
}
