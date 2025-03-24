package com.unocoding.backend.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unocoding.backend.repository.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ClientService(ClientRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // public Admin registerAdmin(String username, String password, String adminCode) {
    //     Admin admin = new Admin();
    //     admin.setUsername(username);
    //     admin.setPassword(passwordEncoder.encode(password));
    //     admin.setAdminCode(adminCode);
    //     admin.getRoles().add("ROLE_ADMIN");
    //     return userRepository.save(admin);
    // }

    // public Employee registerEmployee(String username, String password, String department) {
    //     Employee employee = new Employee();
    //     employee.setUsername(username);
    //     employee.setPassword(passwordEncoder.encode(password));
    //     employee.setDepartment(department);
    //     employee.getRoles().add("ROLE_EMPLOYEE");
    //     return userRepository.save(employee);
    // }

    // public Customer registerCustomer(String username, String password, String address) {
    //     Customer customer = new Customer();
    //     customer.setUsername(username);
    //     customer.setPassword(passwordEncoder.encode(password));
    //     customer.setAddress(address);
    //     customer.getRoles().add("ROLE_CUSTOMER");
    //     return userRepository.save(customer);
    // }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     User user = userRepository.findByUsername(username)
    //         .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    //     return User.withUsername(user.getUsername())
    //         .password(user.getPassword())
    //         .roles(user.getRoles().toArray(new String[0]))
    //         .build();
    // }
}