package com.br.InveMedi.inveMedi.models.projection;


public interface ItemProjection {

   Long getId();

   Integer getQuantidadeEstoque();
   Integer getQuantidadeMinima();

    String getNomeItem();

}
