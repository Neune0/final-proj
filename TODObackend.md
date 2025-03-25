# todo backend

1. definire una lista di endpoint che servono al frontend

## Authentication Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/auth/login` | Authenticate any user | Public |
| POST | `/api/auth/register/client` | Register as client | Public |
| POST | `/api/auth/register/professional` | Register as professional | Public |

## Client Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/clients/profile` | Get current client profile | Client |
| PUT | `/api/clients/profile` | Update current client profile | Client |
| PUT | `/api/clients/profile/image` | Upload/update profile image | Client |
| GET | `/api/clients/{id}` | Get client by ID | Admin |
| GET | `/api/clients` | Get all clients | Admin |
| DELETE | `/api/clients/{id}` | Delete client | Admin |

## Professional Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/professionals` | Get all professionals | Public/Client |
| GET | `/api/professionals/{id}` | Get professional details | Public/Client |
| GET | `/api/professionals/search` | Search professionals by criteria | Public/Client |
| GET | `/api/professionals/profile` | Get current professional profile | Professional |
| PUT | `/api/professionals/profile` | Update professional profile | Professional |
| PUT | `/api/professionals/profile/image` | Upload/update profile image | Professional |
| POST | `/api/professionals/availability` | Add availability slots | Professional |
| GET | `/api/professionals/availability` | Get own availability | Professional |
| DELETE | `/api/professionals/availability/{id}` | Remove availability slot | Professional |

## Meeting Request Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/requests` | Create meeting request | Client |
| GET | `/api/requests/sent` | Get requests sent by client | Client |
| GET | `/api/requests/received` | Get requests received by professional | Professional |
| GET | `/api/requests/{id}` | Get specific request details | Client/Professional |
| PUT | `/api/requests/{id}/accept` | Accept request | Professional |
| PUT | `/api/requests/{id}/reject` | Reject request | Professional |
| PUT | `/api/requests/{id}/cancel` | Cancel request | Client |

## Admin Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/admin/clients` | Get all clients | Admin |
| GET | `/api/admin/professionals` | Get all professionals | Admin |
| GET | `/api/admin/requests` | Get all requests | Admin |
| DELETE | `/api/admin/users/{id}` | Delete any user | Admin |
| GET | `/api/admin/stats` | Get system statistics | Admin |