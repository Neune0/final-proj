package com.unocoding.backend.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.unocoding.backend.service.AdminService;
import com.unocoding.backend.service.ClientService;
import com.unocoding.backend.service.ProfessionalService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final ClientService userService;
    private final AdminService adminService;
    private final ProfessionalService professionalService;
    private final AuthenticationManager authenticationManager;

    // admin non si puo registrare solo login

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