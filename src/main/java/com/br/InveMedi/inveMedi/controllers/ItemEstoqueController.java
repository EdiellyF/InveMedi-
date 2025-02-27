package com.br.InveMedi.inveMedi.controllers;

import com.br.InveMedi.inveMedi.models.ItemEstoqueHospitalar;
import com.br.InveMedi.inveMedi.services.ItemEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/itens")
@Validated
public class ItemEstoqueController {

    @Autowired
    private ItemEstoqueService itemEstoqueService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemEstoqueHospitalar> findById(@PathVariable Long id){
        ItemEstoqueHospitalar obj =  itemEstoqueService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Validated @RequestBody ItemEstoqueHospitalar itemEstoqueHospitalar){
        itemEstoqueService.create(itemEstoqueHospitalar);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(itemEstoqueHospitalar.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }



}
