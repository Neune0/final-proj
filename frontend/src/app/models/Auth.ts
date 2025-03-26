export interface User {
  username: string;
  role: string;
  token: string;
}

export interface AuthResponse {
  accessToken: string;
  username: string;
  role: string;
}

export interface RegisterRequest {
  username: string;
  password: string;
  email: string;
  firstName?: string;
  lastName?: string;
  profession?: string;
  company?: string;
}

export interface AuthRequest {
  username: string;
  password: string;
}
