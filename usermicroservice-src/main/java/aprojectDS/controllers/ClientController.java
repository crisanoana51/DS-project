package aprojectDS.controllers;


import aprojectDS.entities.User;
import aprojectDS.payload.response.MessageResponse;
import aprojectDS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/client")
@PreAuthorize("hasRole('CLIENT')")
public class ClientController {

    @Autowired
    private UserService userService;


    @GetMapping("/getAdmins")
    public List<User> getAdmins(){
        return userService.findAllAdmins();
    }

}
