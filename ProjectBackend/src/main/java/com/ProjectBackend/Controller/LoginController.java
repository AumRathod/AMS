package com.ProjectBackend.Controller;

import com.ProjectBackend.Repo.LoginRepo;
import com.ProjectBackend.model.LoginColl;
import com.ProjectBackend.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    LoginRepo repo;
    
    @GetMapping("")
    public ResponseEntity<String> testConnection()
    {
        System.out.println("hello.");
        return  new ResponseEntity<String>("Hello",HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest req)
    {
    	LoginColl c =  repo.findByName(req.getName());

    	if(c==null || !c.getPassword().equals(req.getPassword()))
    		return new ResponseEntity<String>("Sorry",HttpStatus.NO_CONTENT);
    	if(c.getType().equals("Admin"))
    		return new ResponseEntity<String>("Admin",HttpStatus.OK);
    	return new ResponseEntity<String>("User",HttpStatus.OK) ;
    }
}
