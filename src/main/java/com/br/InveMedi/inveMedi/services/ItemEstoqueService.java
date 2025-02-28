package com.br.InveMedi.inveMedi.services;

import com.br.InveMedi.inveMedi.models.ItemEstoqueHospitalar;
import com.br.InveMedi.inveMedi.models.User;
import com.br.InveMedi.inveMedi.repositories.ItemEstoqueHospitalarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemEstoqueService {

    @Autowired
    private ItemEstoqueHospitalarRepository itemEstoqueHospitalarRepository;

    @Autowired
    private UserService userService;


    public ItemEstoqueHospitalar findById(Long id){
        Optional<ItemEstoqueHospitalar> item = this.itemEstoqueHospitalarRepository.findById(id);
        return item.orElseThrow(() -> new RuntimeException("ItemHospital não encontrado +  id:" + id + ", Tipo: " + ItemEstoqueHospitalar.class.getName()));
    }

    @Transactional
    public ItemEstoqueHospitalar create(ItemEstoqueHospitalar obj){
        User user = this.userService.findById(obj.getUser().getId());
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
            throw new RuntimeException("Não é possivel deletar estoque do item");
        }
    }

    public List<ItemEstoqueHospitalar> findAllByUserId(Long userId){
        List<ItemEstoqueHospitalar> itens = itemEstoqueHospitalarRepository.findByUser_Id(userId);
        return itens;
    }

}
