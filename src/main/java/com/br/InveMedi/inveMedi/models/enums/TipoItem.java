package com.br.InveMedi.inveMedi.models.enums;

import java.util.Objects;

public enum TipoItem {
    MEDICAMENTO(1, "Medicamento"),
    EQUIPAMENTO(2, "Equipamento"),
    SUPRIMENTO(3, "Suprimento"),
    MATERIAL(4, "Material");

    private final Integer code;
    private final String description;

    // Construtor
    TipoItem(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getter para o código
    public Integer getCode() {
        return code;
    }

    // Getter para a descrição
    public String getDescription() {
        return description;
    }

    // Método que converte o código para o enum correspondente
    public static TipoItem toEnum(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }

        for (TipoItem tipo : TipoItem.values()) {
            if (code.equals(tipo.getCode())) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Código de tipo de item inválido: " + code);
    }
}
