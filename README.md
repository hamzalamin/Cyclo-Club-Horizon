# 🚴 Cycling Competition Management System (CCH)

## 📝 Description
CCH (Cyclo Club Horizon) is a REST API for managing cycling time trial competitions. This application facilitates the organization and tracking of cycling competitions, rider management, and automatic ranking calculations.

![Cycling Competition Illustration](./assets/cycling-competition.png)

## 📁 Project Structure
CCH/
├── helpsSql/
│   └── Trigger.sql
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/wora/
│   │   │       ├── config/
│   │   │       ├── controllers/
│   │   │       ├── exception/
│   │   │       ├── mappers/
│   │   │       ├── models/
│   │   │       │   ├── dtos/
│   │   │       │   └── entities/
│   │   │       ├── repositories/
│   │   │       └── services/
│   │   └── resources/
│   └── webapp/
└── test/

## 🚀 Features
### 🏆 Team Management
- Create and modify teams
- View team list with sorting options
- Delete teams

### 🚴 Cyclist Management
- Register new cyclists with personal details
- Update cyclist information
- Remove cyclists from the system
- View cyclists list (sortable by name, nationality, team)

### 🏁 Competition Management
- Create and organize competitions
- Manage registrations
- Filter competitions by date or location
- Stage management within competitions

### 📊 Results and Rankings
- Record cyclist times
- Automatic ranking calculation (via SQL triggers)
- Individual and cumulative rankings
- Detailed reporting system

## 🛠 Technical Stack
### Backend
- **Java**
- **Spring Framework**
  - Spring IoC
  - Spring MVC
  - Spring Data JPA
- **Maven**

### Database
- **PostgreSQL** with custom triggers

### Testing
- **JUnit**
- **Mockito**

## 📚 API Endpoints
### Teams
- `GET`    /api/v1/teams
- `GET`    /api/v1/teams/{id}
- `POST`   /api/v1/teams
- `PUT`    /api/v1/teams/{id}
- `DELETE` /api/v1/teams/{id}

### Cyclists
- `GET`    /api/v1/cyclists
- `GET`    /api/v1/cyclists/{id}
- `POST`   /api/v1/cyclists
- `PUT`    /api/v1/cyclists/{id}
- `DELETE` /api/v1/cyclists/{id}

### Competitions
- `GET`    /api/v1/competitions
- `GET`    /api/v1/competitions/{id}
- `POST`   /api/v1/competitions
- `PUT`    /api/v1/competitions/{id}
- `DELETE` /api/v1/competitions/{id}

### Stages
- `GET`    /api/v1/stages
- `GET`    /api/v1/stages/{id}
- `POST`   /api/v1/stages
- `PUT`    /api/v1/stages/{id}
- `DELETE` /api/v1/stages/{id}

### Results
- `GET`    /api/v1/general-results
- `GET`    /api/v1/general-results/{competitionId}/{cyclistId}
- `POST`   /api/v1/general-results
- `DELETE` /api/v1/general-results/{competitionId}/{cyclistId}

- `GET`    /api/v1/stage-results
- `GET`    /api/v1/stage-results/{stageId}/{cyclistId}
- `POST`   /api/v1/stage-results
- `DELETE` /api/v1/stage-results/{stageId}/{cyclistId}

## 💻 Development
### Code Organization
- **config/**: Contains Spring configuration classes
- **controllers/**: REST endpoints implementation
- **models/**: Data models (entities and DTOs)
- **repositories/**: Database access layer
- **services/**: Business logic implementation
- **mappers/**: DTO-Entity conversion logic
- **exception/**: Custom exception handling

