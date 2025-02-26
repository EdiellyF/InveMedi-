package com.br.InveMedi.inveMedi.services;

import org.springframework.beans.BeanUtils;
import com.br.InveMedi.inveMedi.models.User;
import com.br.InveMedi.inveMedi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;




    public User findById(Long id){
       Optional<User> user = this.userRepository.findById(id);
       return user.orElseThrow(
               () -> new RuntimeException("Usuario não encontrando! ID" + id + ", Tipo: " + User.class.getName()) );
    }


    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }


    @Transactional
    public User update(User user) {
        User newObj = findById(user.getId());

        if (user.getPassword() != null) {
            newObj.setPassword(user.getPassword());
        }

        if (user.getEmail() != null) {
            newObj.setEmail(user.getEmail());
        }

        return this.userRepository.save(newObj);
    }


    @Transactional
    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        }catch (Exception e){
            throw  new RuntimeException("Não é possivel excluir entidade relacionada");
        }
    }

}
