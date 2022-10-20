package org.generation.ecommercedb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.generation.ecommercedb.model.User;
import org.generation.ecommercedb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
@Api(tags = "Administrador de usuarios", description = "Operaciones CRUD de usuarios")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value="Ver las lista de todos los usuarios disponibles", response=List.class)
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @ApiOperation(value="Ver un producto específico", response= User.class)
    @GetMapping("{id}")
    public User getUser(@ApiParam(value = "Ingrese Id", example = "4") @PathVariable("id") Long id){
        return userService.getUser(id);
    }
    @ApiOperation(value="Eliminar un usuario específico")
    @DeleteMapping("{id}")
    public void deleteUser(@ApiParam(value = "Ingrese Id") @PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @ApiOperation(value="Crear un nuevo usuario")
    @PostMapping()
    public void createUser(@ApiParam(value = "Ingresa usuario y contraseña")@RequestBody User user){
        userService.createUser(user);
    }
    @ApiOperation(value="Actualizar un usuario específico")
    @PutMapping("{id}")
    public void updateUser(
            @ApiParam(value = "Ingrese Id")
            @PathVariable("id") Long id,
            @RequestParam(required = false) String currentPassword,
            @RequestParam(required = false) String newPassword
    ){
        userService.updateUser(id,currentPassword,newPassword);
    }
}
