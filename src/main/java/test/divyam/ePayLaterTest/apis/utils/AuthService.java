package test.divyam.ePayLaterTest.apis.utils;

import java.util.HashMap;

import org.jose4j.jws.JsonWebSignature;
public class AuthService 
{ 
    private static AuthService single_instance = null; 
    public HashMap<String,JsonWebSignature> repo = new HashMap<>(); 
    
    public void addKey(String id, JsonWebSignature token) {
    	repo.put(id,token);
    }
    
    public JsonWebSignature getValue(String id) {
    	return repo.get(id);
    }
    
    public HashMap<String,JsonWebSignature> getTokenRepo(){
    	return repo;
    }

    private AuthService() 
    { 
        
    } 
    public static AuthService getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new AuthService(); 
  
        return single_instance; 
    } 
} 
