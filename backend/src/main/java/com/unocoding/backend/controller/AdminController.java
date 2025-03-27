package com.unocoding.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unocoding.backend.dto.ClientProfileDto;
import com.unocoding.backend.dto.MeetingRequestDto;
import com.unocoding.backend.dto.ProfessionalProfileDto;
import com.unocoding.backend.service.ClientService;
import com.unocoding.backend.service.PendingRequestService;
import com.unocoding.backend.service.ProfessionalService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final ClientService clientService;
    private final ProfessionalService professionalService;
    private final PendingRequestService pendingRequestService;

    // CLIENT MANAGEMENT

    @GetMapping("/clients")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ClientProfileDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/clients/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ClientProfileDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @DeleteMapping("/clients/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client deleted successfully");
    }

    // PROFESSIONAL MANAGEMENT

    @GetMapping("/professionals")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ProfessionalProfileDto>> getAllProfessionals() {
        return ResponseEntity.ok(professionalService.getAllProfessionals());
    }

    @GetMapping("/professionals/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProfessionalProfileDto> getProfessionalById(@PathVariable Long id) {
        return ResponseEntity.ok(professionalService.getProfessionalById(id));
    }

    @DeleteMapping("/professionals/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
        return ResponseEntity.ok("Professional deleted successfully");
    }

    // REQUEST MANAGEMENT

    @GetMapping("/requests")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<MeetingRequestDto>> getAllRequests() {
        return ResponseEntity.ok(pendingRequestService.getAllRequests());
    }

    @DeleteMapping("/requests/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteRequest(@PathVariable Long id) {
        pendingRequestService.deleteRequest(id);
        return ResponseEntity.ok("Request deleted successfully");
    }

    // DASHBOARD AND STATISTICS

    @GetMapping("/stats")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> getSystemStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Basic statistics
        int clientCount = clientService.getAllClients().size();
        stats.put("clientCount", clientCount);
        
        int professionalCount = professionalService.getAllProfessionals().size();
        stats.put("professionalCount", professionalCount);
        
        // Request statistics
        long pendingRequestsCount = pendingRequestService.getPendingRequestsCount();
        stats.put("pendingRequestsCount", pendingRequestsCount);
        
        return ResponseEntity.ok(stats);
    }
}