package com.example.roopigatecrud.Usuario;

import java.util.List;
import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Crear Usuario
    @PostMapping
    public ResponseEntity<Void> createUsuario(@RequestBody User user) {
        userService.createUsuario(user);
        return ResponseEntity.ok().build();
    }

    // Obtener lista de usuarios
    @GetMapping
    public ResponseEntity<List<User>> getUsuarios() {
        List<User> usuarios = userService.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Búsqueda de usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUsuario(@PathVariable Long id) {
        Optional<User> usuarioOpt = userService.getUsuarioById(id);
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Actualización de los datos de usuario basados en su id
    @PutMapping(path = "/{id}")
    public User updatUserById(@RequestBody User request, @PathVariable("id") Long id) {
        return userService.updatUserById(request, id);
    }

    //Eliminación de usuario basada en su id
    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean ok = userService.deleteUser(id);

        if (ok) {
            return "El usuario con el id " + id + " ha sido eliminado";
        } else {
            return "No se pudo eliminar el usuario con el id " +id;
        }
    }
}
