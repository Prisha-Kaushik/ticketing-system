import axios from 'axios';
import { 
  LoginRequest, 
  SignupRequest, 
  JwtResponse, 
  Ticket, 
  TicketRequest, 
  Comment, 
  CommentRequest,
  User,
  TicketStatus 
} from '@/types';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
});

// Request interceptor to add auth token
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor to handle token expiration
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      window.location.href = '/auth/login';
    }
    return Promise.reject(error);
  }
);

// Auth API
export const authAPI = {
  login: (data: LoginRequest): Promise<JwtResponse> =>
    api.post('/auth/signin', data).then(res => res.data),
  
  signup: (data: SignupRequest): Promise<string> =>
    api.post('/auth/signup', data).then(res => res.data),
};

// Ticket API
export const ticketAPI = {
  getAll: (): Promise<Ticket[]> =>
    api.get('/tickets').then(res => res.data),
  
  getMy: (): Promise<Ticket[]> =>
    api.get('/tickets/my-tickets').then(res => res.data),
  
  getAssigned: (): Promise<Ticket[]> =>
    api.get('/tickets/assigned-to-me').then(res => res.data),
  
  getById: (id: number): Promise<Ticket> =>
    api.get(`/tickets/${id}`).then(res => res.data),
  
  create: (data: TicketRequest): Promise<Ticket> =>
    api.post('/tickets', data).then(res => res.data),
  
  updateStatus: (id: number, status: TicketStatus): Promise<Ticket> =>
    api.put(`/tickets/${id}/status`, status).then(res => res.data),
  
  assign: (id: number, assigneeId: number): Promise<Ticket> =>
    api.put(`/tickets/${id}/assign`, assigneeId).then(res => res.data),
  
  getComments: (id: number): Promise<Comment[]> =>
    api.get(`/tickets/${id}/comments`).then(res => res.data),
  
  addComment: (id: number, data: CommentRequest): Promise<Comment> =>
    api.post(`/tickets/${id}/comments`, data).then(res => res.data),
};

// Admin API
export const adminAPI = {
  getAllUsers: (): Promise<User[]> =>
    api.get('/admin/users').then(res => res.data),
  
  getUserById: (id: number): Promise<User> =>
    api.get(`/admin/users/${id}`).then(res => res.data),
  
  createUser: (data: SignupRequest): Promise<User> =>
    api.post('/admin/users', data).then(res => res.data),
  
  updateUser: (id: number, data: Partial<User>): Promise<User> =>
    api.put(`/admin/users/${id}`, data).then(res => res.data),
  
  deleteUser: (id: number): Promise<string> =>
    api.delete(`/admin/users/${id}`).then(res => res.data),
  
  getUsersByRole: (role: string): Promise<User[]> =>
    api.get(`/admin/users/role/${role}`).then(res => res.data),
  
  getAllTickets: (): Promise<Ticket[]> =>
    api.get('/admin/tickets').then(res => res.data),
  
  updateTicketStatus: (id: number, status: TicketStatus): Promise<Ticket> =>
    api.put(`/admin/tickets/${id}/status`, status).then(res => res.data),
  
  assignTicket: (id: number, assigneeId: number): Promise<Ticket> =>
    api.put(`/admin/tickets/${id}/assign`, assigneeId).then(res => res.data),
  
  deleteTicket: (id: number): Promise<string> =>
    api.delete(`/admin/tickets/${id}`).then(res => res.data),
};

export default api;
