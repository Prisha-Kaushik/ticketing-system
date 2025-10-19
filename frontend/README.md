# Ticketing System - Frontend

A modern, responsive frontend for the Ticketing System built with Next.js, TypeScript, and Tailwind CSS.

## Features

- **Authentication**: Login and signup with form validation
- **Role-based Access**: Different interfaces for Admin, Support Agent, and User roles
- **Dashboard**: User dashboard for managing personal tickets
- **Admin Panel**: Comprehensive admin interface for user and ticket management
- **Responsive Design**: Mobile-first design with Tailwind CSS
- **Real-time Updates**: Toast notifications for user feedback

## Tech Stack

- Next.js 15 (App Router)
- TypeScript
- Tailwind CSS
- React Hook Form
- Yup (validation)
- Axios (API client)
- React Hot Toast (notifications)
- Lucide React (icons)

## Getting Started

### Prerequisites

- Node.js 18+ 
- npm or yarn
- Backend server running on localhost:8080

### Installation

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

The application will start on `http://localhost:3000`

## Usage

### Demo Accounts

- **Admin**: username: `admin`, password: `admin123`
- **Support Agent**: username: `agent1`, password: `agent123`  
- **User**: username: `user1`, password: `user123`

### Features by Role

#### User
- Create new tickets
- View own tickets
- Add comments to tickets
- Track ticket status

#### Support Agent
- All User features
- View assigned tickets
- Update ticket status
- Assign tickets

#### Admin
- All Support Agent features
- View all tickets and users
- Manage user accounts
- Full system oversight

## Project Structure

```
src/
├── app/                    # Next.js App Router pages
│   ├── auth/              # Authentication pages
│   ├── dashboard/         # User dashboard
│   └── admin/             # Admin panel
├── components/            # Reusable components
├── lib/                   # Utilities and API client
└── types/                 # TypeScript type definitions
```

## API Integration

The frontend communicates with the Spring Boot backend through REST APIs:

- Authentication endpoints (`/api/auth/*`)
- Ticket management (`/api/tickets/*`)
- Admin operations (`/api/admin/*`)

All API calls are handled through the centralized `api.ts` client with automatic token management and error handling.

## Development

### Available Scripts

- `npm run dev` - Start development server
- `npm run build` - Build for production
- `npm run start` - Start production server
- `npm run lint` - Run ESLint

### Environment Variables

Create a `.env.local` file for environment-specific configuration:

```
NEXT_PUBLIC_API_URL=http://localhost:8080/api
```
