package com.unocoding.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unocoding.backend.dto.CreateMeetingRequestDto;
import com.unocoding.backend.dto.MeetingRequestDto;
import com.unocoding.backend.dto.UpdateMeetingRequestDto;
import com.unocoding.backend.models.Client;
import com.unocoding.backend.models.PendingRequest;
import com.unocoding.backend.models.Professional;
import com.unocoding.backend.models.RequestStatus;
import com.unocoding.backend.repository.ClientRepository;
import com.unocoding.backend.repository.PendingRequestRepository;
import com.unocoding.backend.repository.ProfessionalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PendingRequestService {

    private final PendingRequestRepository requestRepository;
    private final ClientRepository clientRepository;
    private final ProfessionalRepository professionalRepository;

    // Create a meeting request (client to professional)
    public MeetingRequestDto createRequest(String clientUsername, CreateMeetingRequestDto createDto) {
        Client client = clientRepository.findByUsername(clientUsername)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        
        Professional professional = professionalRepository.findById(createDto.getProfessionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        
        PendingRequest request = new PendingRequest();
        request.setClient(client);
        request.setProfessional(professional);
        request.setRequestDate(LocalDateTime.now());
        request.setProposedMeetingTime(createDto.getProposedMeetingTime());
        request.setMessage(createDto.getMessage());
        request.setStatus(RequestStatus.PENDING);
        
        request = requestRepository.save(request);
        
        return mapToDto(request);
    }

    // Get all requests (admin only)
    public List<MeetingRequestDto> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get all requests for a client
    public List<MeetingRequestDto> getClientRequests(String clientUsername) {
        Client client = clientRepository.findByUsername(clientUsername)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        
        return requestRepository.findByClientId(client.getId()).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get all requests for a professional
    public List<MeetingRequestDto> getProfessionalRequests(String professionalUsername) {
        Professional professional = professionalRepository.findByUsername(professionalUsername)
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        
        return requestRepository.findByProfessionalId(professional.getId()).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Update a request status (accept/reject by professional)
    public MeetingRequestDto updateRequestStatus(Long requestId, String professionalUsername, UpdateMeetingRequestDto updateDto) {
        Professional professional = professionalRepository.findByUsername(professionalUsername)
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        
        PendingRequest request = requestRepository.findByIdAndProfessionalId(requestId, professional.getId())
                .orElseThrow(() -> new RuntimeException("Request not found or not addressed to this professional"));
        
        request.setStatus(updateDto.getStatus());
        if (updateDto.getMessage() != null && !updateDto.getMessage().isEmpty()) {
            request.setMessage(updateDto.getMessage());
        }
        
        request = requestRepository.save(request);
        
        return mapToDto(request);
    }

    // Cancel a request (by client)
    public void cancelRequest(Long requestId, String clientUsername) {
        Client client = clientRepository.findByUsername(clientUsername)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        
        PendingRequest request = requestRepository.findByIdAndClientId(requestId, client.getId())
                .orElseThrow(() -> new RuntimeException("Request not found or not created by this client"));
        
        if (request.getStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Cannot cancel a request that is not pending");
        }
        
        requestRepository.delete(request);
    }

    // Delete a request (admin only)
    public void deleteRequest(Long requestId) {
        if (!requestRepository.existsById(requestId)) {
            throw new RuntimeException("Request not found");
        }
        requestRepository.deleteById(requestId);
    }

    // Get counts of requests by status (for admin dashboard)
    public long getPendingRequestsCount() {
        return requestRepository.findByStatus(RequestStatus.PENDING).size();
    }

    // Helper method to map PendingRequest to MeetingRequestDto
    private MeetingRequestDto mapToDto(PendingRequest request) {
        return new MeetingRequestDto(
                request.getId(),
                request.getClient().getId(),
                request.getClient().getUsername(),
                request.getProfessional().getId(),
                request.getProfessional().getUsername(),
                request.getRequestDate(),
                request.getProposedMeetingTime(),
                request.getMessage(),
                request.getStatus()
        );
    }
}