export interface LoginResponse {
  authenticationToken: string;
  expiresAt: number;
  email: string;
  role: string;
}
