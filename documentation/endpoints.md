# API Documentation

## Table of Contents
- [Introduction](#introduction)
- [Authentication Endpoints](#authentication-endpoints)
- [Admin Endpoints](#admin-endpoints)
- [Client Endpoints](#client-endpoints)
- [Professional Endpoints](#professional-endpoints)
- [Request Endpoints](#request-endpoints)

## Introduction

This document provides comprehensive information about all the API endpoints available in the system. Each endpoint is described with its HTTP method, URL path, access control, request/response formats, and example payloads where applicable.

## Authentication Endpoints

| Method | Endpoint | Description | Access | Request Body | Response |
|--------|----------|-------------|--------|-------------|----------|
| POST | `/api/auth/login` | Authenticate any user | Public | `AuthRequest` (username, password) | `AuthResponse` (accessToken, tokenType, username, role) |
| POST | `/api/auth/register/client` | Register as client | Public | `RegisterRequest` (username, password, email, firstName, lastName) | Success message |
| POST | `/api/auth/register/professional` | Register as professional | Public | `RegisterRequest` (username, password, email, profession, company) | Success message |

### Request & Response Examples

#### Login

```json
// Request
{
  "username": "johndoe",
  "password": "password123"
}

// Response
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "username": "johndoe",
  "role": "CLIENT"
}
```

#### Register Client

```json
// Request
{
  "username": "johndoe",
  "password": "password123",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe"
}

// Response
"Client registered successfully"
```

#### Register Professional

```json
// Request
{
  "username": "dr.smith",
  "password": "password123",
  "email": "smith@example.com",
  "profession": "Therapist",
  "company": "Wellness Center"
}

// Response
"Professional registered successfully"
```

## Admin Endpoints

| Method | Endpoint | Description | Access | Response |
|--------|----------|-------------|--------|----------|
| GET | `/api/admin/clients` | Get all clients | Admin | List of `ClientProfileDto` |
| GET | `/api/admin/clients/{id}` | Get client by ID | Admin | `ClientProfileDto` |
| DELETE | `/api/admin/clients/{id}` | Delete client | Admin | Success message |
| GET | `/api/admin/professionals` | Get all professionals | Admin | List of `ProfessionalProfileDto` |
| GET | `/api/admin/professionals/{id}` | Get professional by ID | Admin | `ProfessionalProfileDto` |
| DELETE | `/api/admin/professionals/{id}` | Delete professional | Admin | Success message |
| GET | `/api/admin/requests` | Get all meeting requests | Admin | List of `MeetingRequestDto` |
| DELETE | `/api/admin/requests/{id}` | Delete meeting request | Admin | Success message |
| GET | `/api/admin/stats` | Get system statistics | Admin | System statistics (JSON) |

### Response Examples

#### Get System Statistics

```json
{
  "clientCount": 45,
  "professionalCount": 18,
  "pendingRequestsCount": 12
}
```

#### Get All Clients

```json
[
  {
    "id": 1,
    "username": "johndoe",
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "profileImageBase64": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgA..."
  },
  {
    "id": 2,
    "username": "janedoe",
    "email": "jane@example.com",
    "firstName": "Jane",
    "lastName": "Doe",
    "profileImageBase64": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgA..."
  }
]
```

## Client Endpoints

| Method | Endpoint | Description | Access | Request Body | Response |
|--------|----------|-------------|--------|-------------|----------|
| GET | `/api/clients/profile` | Get current client profile | Client | - | `ClientProfileDto` |
| PUT | `/api/clients/profile` | Update current client profile | Client | `UpdateClientProfileDto` | Updated `ClientProfileDto` |
| PUT | `/api/clients/profile/image` | Update profile image | Client | Base64 encoded image string | Updated `ClientProfileDto` |

### Request & Response Examples

#### Get Client Profile

```json
// Response
{
  "id": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "profileImageBase64": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgA..."
}
```

#### Update Client Profile

```json
// Request
{
  "email": "newemail@example.com",
  "firstName": "Jonathan",
  "lastName": "Doe"
}

// Response
{
  "id": 1,
  "username": "johndoe",
  "email": "newemail@example.com",
  "firstName": "Jonathan",
  "lastName": "Doe",
  "profileImageBase64": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgA..."
}
```

## Professional Endpoints

| Method | Endpoint | Description | Access | Request Body | Response |
|--------|----------|-------------|--------|-------------|----------|
| GET | `/api/professionals` | Get all professionals | Public | - | List of `ProfessionalProfileDto` |
| GET | `/api/professionals/{id}` | Get professional by ID | Public | - | `ProfessionalProfileDto` |
| GET | `/api/professionals/search` | Search professionals | Public | `SearchProfessionalDto` (query params) | List of matching `ProfessionalProfileDto` |
| GET | `/api/professionals/profile` | Get current professional profile | Professional | - | `ProfessionalProfileDto` |
| PUT | `/api/professionals/profile` | Update professional profile | Professional | `UpdateProfessionalProfileDto` | Updated `ProfessionalProfileDto` |
| PUT | `/api/professionals/profile/image` | Update profile image | Professional | Base64 encoded image string | Updated `ProfessionalProfileDto` |
| POST | `/api/professionals/availability` | Add availability slot | Professional | `AvailabilityDto` | Updated `ProfessionalProfileDto` |
| GET | `/api/professionals/availability` | Get availability slots | Professional | - | List of `Disponibilita` |
| DELETE | `/api/professionals/availability/{index}` | Remove availability slot | Professional | - | Updated `ProfessionalProfileDto` |

### Request & Response Examples

#### Get Professional Profile

```json
// Response
{
  "id": 1,
  "username": "dr.smith",
  "email": "smith@example.com",
  "profession": "Therapist",
  "company": "Wellness Center",
  "pricePerHour": 75.0,
  "description": "Licensed therapist with 10+ years of experience",
  "profileImageBase64": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgA...",
  "disponibilita": [
    {
      "weekDay": "MONDAY",
      "timeDay": "MORNING"
    },
    {
      "weekDay": "WEDNESDAY",
      "timeDay": "AFTERNOON"
    }
  ]
}
```

#### Search Professionals

Query Parameters:
- `profession`: Filter by profession
- `company`: Filter by company
- `maxPricePerHour`: Maximum price per hour
- `availabilityDay`: Filter by day availability
- `availabilityTime`: Filter by time availability

```
GET /api/professionals/search?profession=Therapist&maxPricePerHour=100&availabilityDay=MONDAY
```

#### Update Professional Profile

```json
// Request
{
  "email": "dr.smith@wellness.com",
  "profession": "Clinical Psychologist",
  "company": "Wellness Center",
  "pricePerHour": 85.0,
  "description": "Licensed clinical psychologist specializing in anxiety and depression"
}

// Response - Updated professional profile with new values
```

#### Add Availability

```json
// Request
{
  "weekDay": "FRIDAY",
  "timeDay": "AFTERNOON"
}

// Response - Updated professional profile with new availability slot
```

## Request Endpoints

| Method | Endpoint | Description | Access | Request Body | Response |
|--------|----------|-------------|--------|-------------|----------|
| POST | `/api/requests` | Create meeting request | Client | `CreateMeetingRequestDto` | `MeetingRequestDto` |
| GET | `/api/requests/client` | Get client's requests | Client | - | List of `MeetingRequestDto` |
| GET | `/api/requests/professional` | Get professional's requests | Professional | - | List of `MeetingRequestDto` |
| PUT | `/api/requests/{id}` | Update request status | Professional | `UpdateMeetingRequestDto` | Updated `MeetingRequestDto` |
| DELETE | `/api/requests/{id}` | Cancel request | Client | - | Success message |

### Request & Response Examples

#### Create Meeting Request

```json
// Request
{
  "professionalId": 1,
  "proposedMeetingTime": "2025-04-01T14:00:00",
  "message": "I would like to schedule a session to discuss anxiety management"
}

// Response
{
  "id": 1,
  "clientId": 3,
  "clientUsername": "johndoe",
  "professionalId": 1,
  "professionalUsername": "dr.smith",
  "requestDate": "2025-03-26T10:30:45",
  "proposedMeetingTime": "2025-04-01T14:00:00",
  "message": "I would like to schedule a session to discuss anxiety management",
  "status": "PENDING"
}
```

#### Update Request Status

```json
// Request
{
  "status": "ACCEPTED",
  "message": "Looking forward to our session. I'll prepare some resources for you."
}

// Response
{
  "id": 1,
  "clientId": 3,
  "clientUsername": "johndoe",
  "professionalId": 1,
  "professionalUsername": "dr.smith",
  "requestDate": "2025-03-26T10:30:45",
  "proposedMeetingTime": "2025-04-01T14:00:00",
  "message": "Looking forward to our session. I'll prepare some resources for you.",
  "status": "ACCEPTED"
}
```

#### Get Client's Requests

```json
// Response
[
  {
    "id": 1,
    "clientId": 3,
    "clientUsername": "johndoe",
    "professionalId": 1,
    "professionalUsername": "dr.smith",
    "requestDate": "2025-03-26T10:30:45",
    "proposedMeetingTime": "2025-04-01T14:00:00",
    "message": "I would like to schedule a session to discuss anxiety management",
    "status": "PENDING"
  },
  {
    "id": 2,
    "clientId": 3,
    "clientUsername": "johndoe",
    "professionalId": 5,
    "professionalUsername": "dr.johnson",
    "requestDate": "2025-03-25T15:20:10",
    "proposedMeetingTime": "2025-03-30T11:00:00",
    "message": "Need consultation about career counseling",
    "status": "ACCEPTED"
  }
]
```

#### Get Professional's Requests

```json
// Response
[
  {
    "id": 1,
    "clientId": 3,
    "clientUsername": "johndoe",
    "professionalId": 1,
    "professionalUsername": "dr.smith",
    "requestDate": "2025-03-26T10:30:45",
    "proposedMeetingTime": "2025-04-01T14:00:00",
    "message": "I would like to schedule a session to discuss anxiety management",
    "status": "PENDING"
  },
  {
    "id": 3,
    "clientId": 4,
    "clientUsername": "mikebrown",
    "professionalId": 1,
    "professionalUsername": "dr.smith",
    "requestDate": "2025-03-26T09:15:32",
    "proposedMeetingTime": "2025-04-02T10:30:00",
    "message": "I'm interested in discussing work-related stress management",
    "status": "REJECTED"
  }
]
```
