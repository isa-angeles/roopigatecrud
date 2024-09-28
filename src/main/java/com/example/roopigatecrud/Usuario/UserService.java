package com.example.roopigatecrud.Usuario;

import java.util.List;
import java.util.Optional; 

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    // Crear usuario
    public void createUsuario(User user) {
        userRepo.save(user);
    }

    // Obtener todos los usuarios
    public List<User> getUsuarios() {
        return userRepo.findAll(); 
    }

    // Buscar Usuario por ID
    public Optional<User> getUsuarioById(Long id) {
        return userRepo.findById(id);
    } 

    //Actualización de datos de Usuario
    public User updatUserById(User request, Long id) {
        Optional<User> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserName(request.getUserName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            return userRepo.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    //Eliminación de Usuaro
    public Boolean deleteUser(Long id) {
        try {
            userRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
