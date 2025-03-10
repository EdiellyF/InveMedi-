package com.br.InveMedi.inveMedi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = ItemEstoqueHospitalar.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = "nome_item")
})

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemEstoqueHospitalar {

    public static final String TABLE_NAME = "item_estoque_hospitalar";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "nome_item", unique = true)
    @NotBlank
    private String nomeitem;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;


    @Column(name = "quantidade_minima", nullable = false)
    private Integer quantidadeMinima;


    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tipo_item", joinColumns = @JoinColumn(name = "item_estoque_id"))
    @Column(name = "tipos", nullable = false)
    private Set<Integer> itens = new HashSet<>();




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemEstoqueHospitalar that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(getNomeitem(), that.getNomeitem()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getNomeitem(), getUser());
    }
}




