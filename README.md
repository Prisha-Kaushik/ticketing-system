# Ticketing System

A comprehensive full-stack ticketing system designed for IT support and customer service operations. Built with modern technologies and following best practices for scalability and maintainability.

## 🚀 Features

### Core Functionality
- **User Authentication & Authorization**: JWT-based authentication with role-based access control
- **Ticket Management**: Complete ticket lifecycle from creation to resolution
- **Comment System**: Thread-based communication on tickets
- **Role-based Access**: Admin, Support Agent, and User roles with appropriate permissions
- **Real-time Updates**: Modern UI with instant feedback

### Must-Have Features ✅
- [x] Authentication & Authorization with JWT
- [x] Role-based access control (User, Admin, Support Agent)
- [x] User Dashboard for ticket management
- [x] Ticket creation with subject, description, and priority
- [x] Ticket lifecycle management (Open → In Progress → Resolved → Closed)
- [x] Comment system with timestamps and user info
- [x] Admin Panel for user and ticket management
- [x] Access control enforcement

### Good-to-Have Features 🎯
- [ ] Email notifications (framework ready)
- [ ] Advanced search and filtering
- [ ] File attachments
- [ ] Ticket rating system
- [ ] Advanced reporting and analytics

## 🏗️ Architecture

### Backend (Spring Boot)
- **Framework**: Spring Boot 3.2.0
- **Security**: Spring Security with JWT
- **Database**: PostgreSQL with JPA/Hibernate
- **Architecture**: RESTful APIs with proper error handling
- **Validation**: Bean validation with custom error messages

### Frontend (Next.js)
- **Framework**: Next.js 15 with App Router
- **Language**: TypeScript for type safety
- **Styling**: Tailwind CSS for responsive design
- **State Management**: React Context for authentication
- **Form Handling**: React Hook Form with Yup validation

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Tokens)
- Maven

### Frontend
- Next.js 15
- TypeScript
- Tailwind CSS
- React Hook Form
- Axios
- React Hot Toast
- Lucide React

## 📋 Prerequisites

- Java 17+
- Node.js 18+
- PostgreSQL 12+
- Maven 3.6+
- npm or yarn

## 🚀 Quick Start

### 1. Database Setup

```sql
CREATE DATABASE ticketing_db;
CREATE USER ticketing_user WITH PASSWORD 'ticketing_pass';
GRANT ALL PRIVILEGES ON DATABASE ticketing_db TO ticketing_user;
```

### 2. Backend Setup

```bash
cd backend
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### 3. Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

The frontend will start on `http://localhost:3000`

### 4. Demo Accounts

The system creates default accounts on startup:

| Role | Username | Password |
|------|----------|----------|
| Admin | admin | Admin@2024 |
| Support Agent | agent1 | Agent@2024 |
| User | user1 | User@2024 |

## 📁 Project Structure

```
ticketing-system/
├── backend/                 # Spring Boot backend
│   ├── src/main/java/      # Java source code
│   │   └── com/ticketing/system/
│   │       ├── controller/ # REST controllers
│   │       ├── service/    # Business logic
│   │       ├── repository/ # Data access layer
│   │       ├── entity/     # JPA entities
│   │       ├── dto/        # Data transfer objects
│   │       ├── security/   # Security configuration
│   │       └── config/     # Application configuration
│   └── src/main/resources/ # Configuration files
└── frontend/               # Next.js frontend
    ├── src/
    │   ├── app/           # Next.js App Router pages
    │   ├── components/    # Reusable React components
    │   ├── lib/           # Utilities and API client
    │   └── types/         # TypeScript definitions
    └── public/            # Static assets
```

## 🔌 API Endpoints

### Authentication
- `POST /api/auth/signin` - User login
- `POST /api/auth/signup` - User registration

### Tickets
- `GET /api/tickets` - Get tickets (role-based filtering)
- `POST /api/tickets` - Create new ticket
- `GET /api/tickets/{id}` - Get ticket details
- `PUT /api/tickets/{id}/status` - Update ticket status
- `PUT /api/tickets/{id}/assign` - Assign ticket
- `POST /api/tickets/{id}/comments` - Add comment
- `GET /api/tickets/{id}/comments` - Get comments

### Admin
- `GET /api/admin/users` - Get all users
- `POST /api/admin/users` - Create user
- `PUT /api/admin/users/{id}` - Update user
- `DELETE /api/admin/users/{id}` - Delete user
- `GET /api/admin/tickets` - Get all tickets

## 🔐 Security Features

- JWT-based authentication
- Role-based authorization
- Password encryption with BCrypt
- CORS configuration
- Input validation and sanitization
- SQL injection prevention through JPA

## 🎨 UI/UX Features

- Responsive design for all devices
- Intuitive navigation with role-based menus
- Real-time feedback with toast notifications
- Form validation with clear error messages
- Loading states and error handling
- Modern, clean interface design

## 🧪 Testing

### Backend Testing
```bash
cd backend
mvn test
```

### Frontend Testing
```bash
cd frontend
npm run test
```

## 📈 Future Enhancements

1. **Email Notifications**: SMTP integration for ticket updates
2. **File Attachments**: Secure file upload/download system
3. **Advanced Search**: Full-text search with filters
4. **Reporting**: Analytics dashboard with charts
5. **API Documentation**: Swagger/OpenAPI integration
6. **Docker Support**: Containerization for easy deployment
7. **CI/CD Pipeline**: Automated testing and deployment

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the documentation in each module's README
- Review the API documentation

---

Built with ❤️ using Spring Boot and Next.js
