# Ticketing System - Backend

A full-stack ticketing system built with Spring Boot and PostgreSQL.

## Features

- **Authentication & Authorization**: JWT-based authentication with role-based access control
- **User Management**: Support for Admin, Support Agent, and User roles
- **Ticket Management**: Complete ticket lifecycle management
- **Comment System**: Thread-based comments on tickets
- **RESTful APIs**: Well-structured REST endpoints

## Tech Stack

- Java 17
- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT for authentication

## Setup Instructions

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+

### Database Setup

1. Create a PostgreSQL database:
```sql
CREATE DATABASE ticketing_db;
CREATE USER ticketing_user WITH PASSWORD 'ticketing_pass';
GRANT ALL PRIVILEGES ON DATABASE ticketing_db TO ticketing_user;
```

### Running the Application

1. Clone the repository and navigate to the backend directory
2. Update `application.yml` with your database credentials if needed
3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Default Users

The application creates default users on startup:

- **Admin**: username: `admin`, password: `admin123`
- **Support Agent**: username: `agent1`, password: `agent123`
- **User**: username: `user1`, password: `user123`

## API Endpoints

### Authentication
- `POST /api/auth/signin` - User login
- `POST /api/auth/signup` - User registration

### Tickets
- `GET /api/tickets` - Get all tickets (user-specific or all for admin)
- `POST /api/tickets` - Create new ticket
- `GET /api/tickets/{id}` - Get ticket by ID
- `PUT /api/tickets/{id}/status` - Update ticket status
- `PUT /api/tickets/{id}/assign` - Assign ticket to agent
- `POST /api/tickets/{id}/comments` - Add comment to ticket
- `GET /api/tickets/{id}/comments` - Get ticket comments

### Admin
- `GET /api/admin/users` - Get all users
- `POST /api/admin/users` - Create user
- `PUT /api/admin/users/{id}` - Update user
- `DELETE /api/admin/users/{id}` - Delete user
- `GET /api/admin/tickets` - Get all tickets

## Database Schema

The application uses the following main entities:

- **User**: Users with roles (ADMIN, SUPPORT_AGENT, USER)
- **Ticket**: Support tickets with status, priority, owner, and assignee
- **Comment**: Comments on tickets with author and timestamp information
