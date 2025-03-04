package com.br.InveMedi.inveMedi.services;

import com.br.InveMedi.inveMedi.models.ItemEstoqueHospitalar;
import com.br.InveMedi.inveMedi.models.User;
import com.br.InveMedi.inveMedi.models.enums.ProfileEnum;
import com.br.InveMedi.inveMedi.repositories.UserRepository;
import com.br.InveMedi.inveMedi.services.exceptions.DataBindingViolationException;
import com.br.InveMedi.inveMedi.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private UserRepository userRepository;




    public User findById(Long id){
       Optional<User> user = this.userRepository.findById(id);
       return user.orElseThrow(
               () -> new ObjectNotFoundException("Usuario não encontrando! ID" + id + ", Tipo: " + User.class.getName()) );
    }


    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
        obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        obj = this.userRepository.save(obj);
        return obj;
    }


    @Transactional
    public User update(User user) {
        User newObj = findById(user.getId());


        if (user.getPassword() != null) {
            newObj.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
            throw  new DataBindingViolationException("Não é possivel excluir entidade relacionada");
        }
    }

}
