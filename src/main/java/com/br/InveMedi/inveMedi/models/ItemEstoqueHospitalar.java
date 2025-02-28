package com.br.InveMedi.inveMedi.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = ItemEstoqueHospitalar.TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ItemEstoqueHospitalar {

    public static final String TABLE_NAME = "item_estoque_hospitalar";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome_item", nullable = false)
    private String nomeItem;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;


    @Column(name = "quantidade_minima", nullable = false)
    private Integer quantidadeMinima;


    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemEstoqueHospitalar that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(getNomeItem(), that.getNomeItem()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getNomeItem(), getUser());
    }
}




