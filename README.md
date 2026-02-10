Inventory API (Spring Boot) - Assignment 33

Deskripsi:
Project ini adalah REST API sederhana untuk kebutuhan Assignment 33, yang dideploy ke VPS menggunakan Docker, Docker Compose, dan GitHub Actions (CI/CD).

Tech Stack:
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Validation
- Spring Boot Actuator
- H2 Database
- Docker
- Docker Compose
- GitHub Actions
- Postman / cURL

=====================================================
CARA CLONE DAN MENJALANKAN DI LOKAL
=====================================================

1. Clone Repository
git clone <LINK_REPOSITORY_GITHUB>
cd inventory-api

2. Jalankan Aplikasi
mvn spring-boot:run

3. Akses Lokal
http://localhost:8080

Health Check:
http://localhost:8080/actuator/health

=====================================================
ARSITEKTUR PROJECT
=====================================================

Controller Layer:
- UserController
- ItemController

Service Layer:
- UserService
- ItemService

Repository Layer:
- UserRepository
- ItemRepository

=====================================================
ENVIRONMENT CONFIG
=====================================================

Aplikasi membaca port dari environment variable:

server.port=${APP_PORT:8080}

Artinya:
- Jika APP_PORT ada → gunakan port tersebut
- Jika tidak → default 8080

=====================================================
STEP DEPLOYMENT KE VPS
=====================================================

1. Pastikan File Ada di Repository:
- Dockerfile
- docker-compose.yml
- .github/workflows/deploy.yml
- README.md

2. Setup GitHub Secrets:
Masuk ke:
Repository → Settings → Secrets → Actions

Tambahkan:
VPS_HOST
VPS_USER
VPS_SSH_KEY
DOCKERHUB_USERNAME
DOCKERHUB_TOKEN

3. Setup VPS

Login:
ssh student@203.194.115.210

Pastikan Docker ada:
docker --version
docker compose version

Buat folder deploy:
mkdir -p /home/student/spring-app/student4
cd /home/student/spring-app/student4

Buat file .env:
nano .env

Isi:
APP_PORT=9004
DOCKERHUB_USERNAME=<USERNAME_DOCKERHUB>

4. Deploy Otomatis

Deployment berjalan otomatis saat push ke branch main.

Workflow akan:
- Build Maven
- Build Docker Image
- Push ke DockerHub
- Copy docker-compose ke VPS
- Pull Image di VPS
- Restart Container

=====================================================
LINK API PUBLIK (VPS)
=====================================================

Base URL:
http://203.194.115.210:9004

Health:
GET /actuator/health

Users:
GET /api/users
POST /api/users

Items:
GET /api/items
POST /api/items
GET /api/items/{id}
PUT /api/items/{id}
DELETE /api/items/{id}

=====================================================
CONTOH REQUEST API
=====================================================

POST USERS
POST /api/users
Body:
{
  "name": "Budi",
  "email": "budi9004@mail.com"
}

POST ITEMS
POST /api/items
Body:
{
  "sku": "SKU-001",
  "name": "Ikan Tuna",
  "stock": 100
}

=====================================================
PENGUJIAN API
=====================================================

POSTMAN TEST

1. GET Users
http://203.194.115.210:9004/api/users

2. POST Users
http://203.194.115.210:9004/api/users

3. GET Items
http://203.194.115.210:9004/api/items

4. POST Items
http://203.194.115.210:9004/api/items

=====================================================
cURL TEST
=====================================================

GET USERS
curl http://203.194.115.210:9004/api/users

POST USERS
curl -X POST http://203.194.115.210:9004/api/users \
 -H "Content-Type: application/json" \
 -d '{"name":"Budi","email":"budi9004@mail.com"}'

POST ITEMS
curl -X POST http://203.194.115.210:9004/api/items \
 -H "Content-Type: application/json" \
 -d '{"sku":"SKU-001","name":"Ikan Tuna","stock":100}'

=====================================================
OUTPUT YANG DIHARAPKAN
=====================================================

Response Success:
{
  "success": true,
  "message": "...",
  "data": {...}
}

=====================================================
CATATAN
=====================================================

Jika POST Users gagal karena email sudah ada:
Ganti email menjadi unik.

Jika API tidak bisa diakses:
Pastikan container running:
docker ps

Cek logs:
docker logs spring-deploy-student4-app

=====================================================
AUTHOR
=====================================================

Bezaleel Firman L
Assignment 33 Deployment API
Spring Boot + Docker + GitHub Actions + VPS
