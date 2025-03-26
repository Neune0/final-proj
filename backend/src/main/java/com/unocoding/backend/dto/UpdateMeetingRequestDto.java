package com.unocoding.backend.dto;

import com.unocoding.backend.models.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMeetingRequestDto {
    private RequestStatus status;
    private String message;
}