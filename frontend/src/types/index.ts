export enum Role {
  USER = 'USER',
  SUPPORT_AGENT = 'SUPPORT_AGENT',
  ADMIN = 'ADMIN'
}

export enum TicketStatus {
  OPEN = 'OPEN',
  IN_PROGRESS = 'IN_PROGRESS',
  RESOLVED = 'RESOLVED',
  CLOSED = 'CLOSED'
}

export enum Priority {
  LOW = 'LOW',
  MEDIUM = 'MEDIUM',
  HIGH = 'HIGH',
  URGENT = 'URGENT'
}

export interface User {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  role: Role;
  createdAt: string;
  updatedAt: string;
}

export interface Ticket {
  id: number;
  subject: string;
  description: string;
  priority: Priority;
  status: TicketStatus;
  owner: User;
  assignee?: User;
  createdAt: string;
  updatedAt: string;
  resolvedAt?: string;
  comments?: Comment[];
}

export interface Comment {
  id: number;
  content: string;
  author: User;
  ticket: Ticket;
  createdAt: string;
  updatedAt: string;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface SignupRequest {
  username: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  role?: Role;
}

export interface JwtResponse {
  token: string;
  type: string;
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  roles: string[];
}

export interface TicketRequest {
  subject: string;
  description: string;
  priority: Priority;
}

export interface CommentRequest {
  content: string;
}
