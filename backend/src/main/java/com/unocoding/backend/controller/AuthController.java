package com.unocoding.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.unocoding.backend.service.AdminService;
import com.unocoding.backend.service.ClientService;
import com.unocoding.backend.service.ProfessionalService;

import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final ClientService userService;
    private final AdminService adminService;
    private final ProfessionalService professionalService;
    private final AuthenticationManager authenticationManager;

    // admin non si puo registrare solo login
    // admin non si puo registrare solo login
    @PostMapping("/login/admin")
    public ResponseEntity<?> loginAdmin(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Verifichiamo che sia effettivamente un admin
            if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Admin login successful");
                response.put("username", username);
                response.put("role", "ADMIN");
                
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(403).body("User is not an admin");
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
    // registrazione differenziata tra client e professional, mi servono piu dati per il professional

    // login per tutti ma probabilmente da root diverse vedi lato frontend


    // @PostMapping("/register/employee")
    // public String registerEmployee(@RequestParam String username, @RequestParam String password, 
    //                                @RequestParam String department) {
    //     userService.registerEmployee(username, password, department);
    //     return "Employee registered successfully";
    // }

    // @PostMapping("/register/customer")
    // public String registerCustomer(@RequestParam String username, @RequestParam String password, 
    //                                @RequestParam String address) {
    //     userService.registerCustomer(username, password, address);
    //     return "Customer registered successfully";
    // }

    // @PostMapping("/login")
    // public String login(@RequestParam String username, @RequestParam String password) {
    //     Authentication authentication = authenticationManager.authenticate(
    //         new UsernamePasswordAuthenticationToken(username, password)
    //     );
    //     return authentication.isAuthenticated() ? "Login successful" : "Login failed";
    // }
}