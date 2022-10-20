package org.generation.ecommercedb.service;

import org.generation.ecommercedb.model.User;
import org.generation.ecommercedb.utils.SHAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean login(String username, String password){
        boolean res = false;
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            System.out.println("Password SHA " + SHAUtil.createHash(password));
            if (user.get().getPassword().equals(SHAUtil.createHash(password))){
                res = true;
            }
        }
        /*else {
            throw new IllegalStateException("El username: "+ username+", no existe");
        }*/
        return res;
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("El producto con el ID: " + id + " no existe."));
    }
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new IllegalStateException("El usuario con ID: " + id + ", no existe!");
        }
    }

    public void createUser(User user) {
        Optional<User> userByUsername = userRepository.findByUsername(user.getUsername());
        if (userByUsername.isPresent()){
            throw new IllegalStateException("El username: " + user.getUsername() + " ya existe.");
        } else {
            user.setPassword(SHAUtil.createHash(user.getPassword()));
            userRepository.save(user);
        }
    }

    public void updateUser(Long id, String currentPassword, String newPassword) {
        System.out.println(currentPassword.length());
        System.out.println(newPassword.length());
        if (!userRepository.existsById(id)){
            throw new IllegalStateException("El user no existe");
        }
        if (currentPassword.length()==0 || newPassword.length() == 0){
            throw new IllegalStateException("Parametros de contraseña vacios");
        }
        User u = userRepository.getReferenceById(id);
        if (!u.getPassword().equals(SHAUtil.createHash(currentPassword))){
            throw new IllegalStateException("Contraseña incorrecta");
        }
        if (SHAUtil.verifyPassword(currentPassword,newPassword)){
            throw new IllegalStateException("Es la misma contraseña que la anterior");
        }
        u.setPassword(SHAUtil.createHash(newPassword));
        userRepository.save(u);
    }

}
