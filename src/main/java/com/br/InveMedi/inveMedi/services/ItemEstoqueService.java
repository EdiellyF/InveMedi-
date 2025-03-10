package com.br.InveMedi.inveMedi.services;

import com.br.InveMedi.inveMedi.models.ItemEstoqueHospitalar;
import com.br.InveMedi.inveMedi.models.User;
import com.br.InveMedi.inveMedi.models.enums.ProfileEnum;
import com.br.InveMedi.inveMedi.models.projection.ItemProjection;
import com.br.InveMedi.inveMedi.repositories.ItemEstoqueHospitalarRepository;
import com.br.InveMedi.inveMedi.security.UserSpringSecurity;
import com.br.InveMedi.inveMedi.services.exceptions.AuthorizationException;
import com.br.InveMedi.inveMedi.services.exceptions.DataBindingViolationException;
import com.br.InveMedi.inveMedi.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ItemEstoqueService {

    private ItemEstoqueHospitalarRepository itemEstoqueHospitalarRepository ;
    private UserService userService;

    public ItemEstoqueService(ItemEstoqueHospitalarRepository itemEstoqueHospitalarRepository,UserService userService){
        this.itemEstoqueHospitalarRepository = itemEstoqueHospitalarRepository;
        this.userService = userService;
    }


    public ItemEstoqueHospitalar findById(Long id){

        ItemEstoqueHospitalar item = this.itemEstoqueHospitalarRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("ItemHospital não encontrado +  id:" + id + ", Tipo: " + ItemEstoqueHospitalar.class.getName()));

        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if(Objects.isNull(userSpringSecurity)
                || !userSpringSecurity.hasRole(ProfileEnum.ADMIN)
                && !userHasItemEstoque(userSpringSecurity, item)){
            throw new AuthorizationException("Acesso negado");
        }


        return item;
    }

    @Transactional
    public ItemEstoqueHospitalar create(ItemEstoqueHospitalar obj){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if(Objects.isNull(userSpringSecurity)){
            throw new AuthorizationException("Acesso negado");
        }



        User user = this.userService.findById(userSpringSecurity.getId());
        obj.setUser(user);
        obj.setId(null);
        obj = this.itemEstoqueHospitalarRepository.save(obj);
        return obj;

    }

    @Transactional
    public ItemEstoqueHospitalar update(ItemEstoqueHospitalar obj){
            ItemEstoqueHospitalar newObj = findById(obj.getId());
            newObj.setQuantidadeEstoque(obj.getQuantidadeEstoque());
            return this.itemEstoqueHospitalarRepository.save(newObj);
    }


    @Transactional
    public void delete(Long id){
        findById(id);
        try {
            this.itemEstoqueHospitalarRepository.deleteById(id);
        }catch (Exception e){
            throw new DataBindingViolationException("Não é possivel deletar estoque do item");
        }
    }

    public List<ItemProjection> findAllByUser(){

        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if(Objects.isNull(userSpringSecurity)){
            throw new AuthorizationException("Acesso negado");
        }
        List<ItemProjection> itens = itemEstoqueHospitalarRepository.findByUser_Id(userSpringSecurity.getId());

        return itens;
    }


    private Boolean userHasItemEstoque(UserSpringSecurity userSpringSecurity, ItemEstoqueHospitalar itemEstoqueHospitalar){
       return itemEstoqueHospitalar.getUser().getId().equals(userSpringSecurity.getId());
    }



}
