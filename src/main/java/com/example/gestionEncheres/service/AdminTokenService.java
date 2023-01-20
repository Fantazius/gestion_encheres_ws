package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.AdminToken;
import com.example.gestionEncheres.repository.AdminTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

@Service
public class AdminTokenService {
    @Autowired(required=true)
    AdminTokenRepository adminTokenRepository;
    private static final String ALGORITHM = "SHA-1";
    private static final String BYTE_ENCODE = "UTF-8";
    private static final String HEX_ENCODE = "%02x";

    private static String toHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format(HEX_ENCODE, b);
        }
        String hex = formatter.toString();
        formatter.close();
        return hex;
    }

    public static String sha1(String str) throws Exception {
        MessageDigest cryptage = MessageDigest.getInstance(ALGORITHM);
        cryptage.reset();
        cryptage.update(str.getBytes(BYTE_ENCODE));
        return toHex(cryptage.digest());
    }

    public static String generateToken(String id_user) throws Exception {
        LocalTime now = LocalTime.now();
        String token = sha1(id_user.concat(now.toString()));
        return token;
    }

    public List<AdminToken> getAllTokens()
    {
        List<AdminToken> tokens = new ArrayList<>();
        adminTokenRepository.findAll().forEach(tokens::add);
        return tokens;
    }

    //getting a specific record by using the method findById() of JpaRepository
    public AdminToken getTokensById(int id)
    {
        return adminTokenRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of JpaRepository
    public void saveOrUpdate(AdminToken token)
    {
        adminTokenRepository.save(token);
    }
    //deleting a specific record by using the method deleteById() of JpaRepository
    public void delete(int id)
    {
        adminTokenRepository.deleteById(id);
    }
    //updating a record
    public void update(AdminToken token, int bookid)
    {
        adminTokenRepository.save(token);
    }

    public boolean isTokenValid(String token){
        return adminTokenRepository.isAdminTokenValid(token);
    }

    public AdminToken getTokenByToken(String token){
        return adminTokenRepository.getTokenByToken(token);
    }

    public void deconnexion(AdminToken token){
        adminTokenRepository.deconnexion(token.getToken());
    }
}
