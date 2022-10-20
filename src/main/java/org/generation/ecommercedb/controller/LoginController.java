package org.generation.ecommercedb.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.generation.ecommercedb.jwt.config.JwtFilter;
import org.generation.ecommercedb.model.User;
import org.generation.ecommercedb.model.Token;
import org.generation.ecommercedb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/login/")
@CrossOrigin(origins = "*")
@Api(tags = "Login de usuarios", description = "Al ingresar devuelve el token JTW para poder ingresar a otros controller")
public class LoginController {
    private final UserService userService;
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value="Ingresar como usuario", response= Token.class)
    @PostMapping
    public Token Login(@ApiParam(value = "Ingresa usuario y contraseña") @RequestBody User user) throws ServletException {
        if (userService.login(user.getUsername(),user.getPassword())){
            return new Token(generateToken(user.getUsername()));
        }//if
        throw new ServletException("Nombre de usuario y contraseña incorrectos");
    }

    private String generateToken(String username) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 10);
        return Jwts.builder().setSubject(username).claim("role", "user").setIssuedAt(new Date())
                .setExpiration(calendar.getTime()).signWith(SignatureAlgorithm.HS256, JwtFilter.secret).compact();
    }// generateToken
}
