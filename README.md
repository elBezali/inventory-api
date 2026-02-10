# Inventory API (Spring Boot) - Assignment 33

API sederhana untuk kebutuhan assignment deployment ke VPS menggunakan Docker + Docker Compose + GitHub Actions (CI/CD).

## Tech Stack
- Java 17
- Spring Boot
- Spring Web, Spring Data JPA, Validation
- Actuator (healthcheck)
- H2 Database
- Docker, Docker Compose
- GitHub Actions (deploy otomatis ke VPS)
- Postman / cURL untuk pengujian

---

## Cara Clone & Menjalankan di Lokal

### 1) Clone repository
git clone <LINK_REPO_GITHUB_KAMU>
cd inventory-api

### 2) Jalankan aplikasi
mvn spring-boot:run

Default local:
- http://localhost:8080

Cek health:
- http://localhost:8080/actuator/health

---

## Endpoint Utama
Base URL (VPS): http://203.194.115.210:9004

### Users (endpoint utama assignment)
- GET /api/users
- POST /api/users

Contoh body POST /api/users:
{
  "name": "Budi",
  "email": "budi9004@mail.com"
}

### Items (tambahan)
- GET /api/items

### Healthcheck
- GET /actuator/health

---

## Step by Step Deployment ke VPS (Docker + GitHub Actions)

### 1) File yang disiapkan di repository
Pastikan repository berisi:
- Dockerfile
- docker-compose.yml
- .github/workflows/deploy.yml
- README.md

Aplikasi membaca port dari environment:
server.port=${APP_PORT:8080}

### 2) Setup GitHub Secrets
Di GitHub: Settings -> Secrets and variables -> Actions -> New repository secret

Isi secrets berikut:
- VPS_HOST
- VPS_USER
- VPS_SSH_KEY
- DOCKERHUB_USERNAME
- DOCKERHUB_TOKEN

### 3) Setup VPS
Login VPS:
ssh student@203.194.115.210

Pastikan Docker tersedia:
docker --version
docker compose version

Siapkan folder deploy dan file .env:
mkdir -p /home/student/spring-app/student4
cd /home/student/spring-app/student4
nano .env

Isi .env (contoh):
APP_PORT=9004
DOCKERHUB_USERNAME=<USERNAME_DOCKERHUB>

### 4) Deploy otomatis lewat GitHub Actions
Deploy berjalan otomatis ketika ada push ke branch main.

Workflow melakukan:
1. Build aplikasi (Maven)
2. Build & push Docker image ke DockerHub
3. Copy docker-compose.yml ke VPS
4. docker compose pull
5. docker compose down && docker compose up -d

---

## Link API Publik (VPS)
- GET Users: http://203.194.115.210:9004/api/users
- POST Users: http://203.194.115.210:9004/api/users
- GET Items: http://203.194.115.210:9004/api/items
- Health: http://203.194.115.210:9004/actuator/health

---

## Pengujian API (Postman / cURL)

### Postman
Lakukan pengujian dan ambil screenshot:
1) GET http://203.194.115.210:9004/api/users (status 200 OK)
2) POST http://203.194.115.210:9004/api/users (status 200/201, response success)

### cURL
GET /api/users:
curl http://203.194.115.210:9004/api/users

POST /api/users:
curl -X POST http://203.194.115.210:9004/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Budi","email":"budi9004@mail.com"}'
