package com.br.InveMedi.inveMedi.models.projection;


import com.br.InveMedi.inveMedi.models.User;

public interface ItemProjection {

   Long getId();

     Integer getQuantidadeEstoque();

    Integer getquantidadeMinima();

    String getNomeitem();

}
