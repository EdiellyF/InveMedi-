package com.br.InveMedi.inveMedi.controllers;

import com.br.InveMedi.inveMedi.models.ItemEstoqueHospitalar;
import com.br.InveMedi.inveMedi.models.projection.ItemProjection;
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
    public ResponseEntity<List<ItemProjection>> findAllUserId(){
        List<ItemProjection> objs = this.itemEstoqueService.findAllByUser();
        return ResponseEntity.ok().body(objs);
    }

    @GetMapping("/username")
    public ResponseEntity<List<String>> findAllUserName() {
        List<String> objs = this.itemEstoqueService.findUsernameByUser(); // Assuming the service returns the full entity
        return ResponseEntity.ok().body(objs);
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ItemEstoqueHospitalar itemEstoqueHospitalar){
        itemEstoqueService.create(itemEstoqueHospitalar);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(itemEstoqueHospitalar.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Void> update(@RequestBody ItemEstoqueHospitalar itemEstoqueHospitalar, @PathVariable Long id){
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
