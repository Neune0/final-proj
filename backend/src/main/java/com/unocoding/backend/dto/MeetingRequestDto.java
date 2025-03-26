package com.unocoding.backend.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.unocoding.backend.models.RequestStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRequestDto {
    private Long id;
    private Long clientId;
    private String clientUsername;
    private Long professionalId;
    private String professionalUsername;
    private LocalDateTime requestDate;
    private String proposedMeetingTime;
    private String message;
    private RequestStatus status;
}
