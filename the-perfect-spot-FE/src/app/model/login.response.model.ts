import {AuthorityModel} from './AuthorityModel';

export interface LoginResponse {
  authenticationToken: string;
  expiresAt: number;
  email: string;
  roles: Array<AuthorityModel>;
}
