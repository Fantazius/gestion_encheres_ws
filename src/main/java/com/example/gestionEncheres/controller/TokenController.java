package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.models.Token;
import com.example.gestionEncheres.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tokens")
public class TokenController {
    @Autowired(required=true)
    TokenService tokenService;

    @GetMapping
    private List<Token> getAllTokens() {
        return tokenService.getAllTokens();
    }

    @GetMapping("/{tokensid}")
    private Token getTokens(@PathVariable("modeleid") int tokensid) {
        return tokenService.getTokensById(tokensid);
    }

    @DeleteMapping("/{tokensid}")
    private void deleteBook(@PathVariable("tokensid") int tokensid) {
        tokenService.delete(tokensid);
    }

    @PostMapping
    private int saveToken(@RequestBody Token token) {
        tokenService.saveOrUpdate(token);
        return token.getIdToken();
    }

    //creating put mapping that updates the avion detail
    @PutMapping
    private Token update(@RequestBody Token token) {
        tokenService.saveOrUpdate(token);
        return token;
    }
}
