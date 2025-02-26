package com.br.InveMedi.inveMedi.repositories;

import com.br.InveMedi.inveMedi.models.ItemEstoqueHospitalar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemEstoqueHospitalarRepository extends JpaRepository<ItemEstoqueHospitalar, Long> {


//    List<ItemEstoqueHospitalar> findByUser_Id(Long id);

//    @Query(value = "SELECT i from ItemEstoqueHospitalar i where  i.user.id = :user_id")
//    List<ItemEstoqueHospitalar> findByUser_Id(@Param("user_id") Long id);


    @Query(value = "SELECT * FROM  item_estoque_hospitalar i WHERE i.user_id = :user_id" ,nativeQuery = true)
    List<ItemEstoqueHospitalar>  findByUser_Id(@Param("user_id") Long id);

}
