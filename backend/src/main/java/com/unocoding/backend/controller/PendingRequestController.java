package com.unocoding.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.unocoding.backend.dto.CreateMeetingRequestDto;
import com.unocoding.backend.dto.MeetingRequestDto;
import com.unocoding.backend.dto.UpdateMeetingRequestDto;
import com.unocoding.backend.service.PendingRequestService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/requests")
@CrossOrigin
public class PendingRequestController {

    private final PendingRequestService pendingRequestService;

    // Client creates a meeting request
    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<MeetingRequestDto> createRequest(@RequestBody CreateMeetingRequestDto createDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(pendingRequestService.createRequest(username, createDto));
    }

    // Client gets their requests
    @GetMapping("/client")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<List<MeetingRequestDto>> getClientRequests() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(pendingRequestService.getClientRequests(username));
    }

    // Professional gets their requests
    @GetMapping("/professional")
    @PreAuthorize("hasAuthority('PROFESSIONAL')")
    public ResponseEntity<List<MeetingRequestDto>> getProfessionalRequests() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(pendingRequestService.getProfessionalRequests(username));
    }

    // Professional updates request status (accept/reject)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PROFESSIONAL')")
    public ResponseEntity<MeetingRequestDto> updateRequestStatus(
            @PathVariable Long id,
            @RequestBody UpdateMeetingRequestDto updateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(pendingRequestService.updateRequestStatus(id, username, updateDto));
    }

    // Client cancels their request
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<?> cancelRequest(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        pendingRequestService.cancelRequest(id, username);
        return ResponseEntity.ok("Request cancelled successfully");
    }
}