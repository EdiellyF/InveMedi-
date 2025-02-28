package com.br.InveMedi.inveMedi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;



@Entity
@Table(name = "tipo_item_hospitalar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class TipoItemHospitalar {


    @Id
    private Long id;


    private String nomeTipo;
}
