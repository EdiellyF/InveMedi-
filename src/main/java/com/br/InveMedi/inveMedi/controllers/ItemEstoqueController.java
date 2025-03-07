package com.br.InveMedi.inveMedi.controllers;

import com.br.InveMedi.inveMedi.models.ItemEstoqueHospitalar;
import com.br.InveMedi.inveMedi.services.ItemEstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/itens")
@Validated
public class ItemEstoqueController {

    private ItemEstoqueService itemEstoqueService;

    public ItemEstoqueController (ItemEstoqueService itemEstoqueService) {
        this.itemEstoqueService = itemEstoqueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEstoqueHospitalar> findById(@PathVariable Long id){
        ItemEstoqueHospitalar obj =  itemEstoqueService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/user")
    public ResponseEntity<List<ItemEstoqueHospitalar>> findAllUserId(){
        List<ItemEstoqueHospitalar> objs = this.itemEstoqueService.findAllByUser();
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Validated @RequestBody ItemEstoqueHospitalar itemEstoqueHospitalar){
        itemEstoqueService.create(itemEstoqueHospitalar);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(itemEstoqueHospitalar.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public  ResponseEntity<Void> update(@Validated @RequestBody ItemEstoqueHospitalar itemEstoqueHospitalar, @PathVariable Long id){
        itemEstoqueHospitalar.setId(id);
        itemEstoqueService.update(itemEstoqueHospitalar);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Validated
    public ResponseEntity<Void> delete(@Validated @PathVariable Long id){
        itemEstoqueService.delete(id);
        return ResponseEntity.noContent().build();
    }





}
