# Inventory API (Spring Boot)

## 📌 Deskripsi

Project ini adalah REST API sederhana untuk kebutuhan Assignment 33,
yang dideploy ke VPS menggunakan Docker, Docker Compose, dan GitHub
Actions (CI/CD).

------------------------------------------------------------------------

## 🧰 Tech Stack

-   Java 17
-   Spring Boot
-   Spring Web
-   Spring Data JPA
-   Validation
-   Spring Boot Actuator
-   H2 Database
-   Docker
-   Docker Compose
-   GitHub Actions
-   Postman / cURL

------------------------------------------------------------------------

## 🚀 Cara Clone & Menjalankan di Lokal

### 1️⃣ Clone Repository

``` bash
git clone <LINK_REPOSITORY_GITHUB>
cd inventory-api
```

### 2️⃣ Jalankan Aplikasi

``` bash
mvn spring-boot:run
```

### 3️⃣ Akses Lokal

``` bash
http://localhost:8080
```

❤️ Health Check

``` bash
http://localhost:8080/actuator/health
```

------------------------------------------------------------------------

## 🏗 Arsitektur Project

### 🎮 Controller Layer

-   UserController
-   ItemController

### 🧠 Service Layer

-   UserService
-   ItemService

### 🗄 Repository Layer

-   UserRepository
-   ItemRepository

------------------------------------------------------------------------

## ⚙️ Environment Config

``` properties
server.port=${APP_PORT:8080}
```

📌 Artinya: - Jika APP_PORT tersedia → gunakan port tersebut\
- Jika tidak → default port 8080

------------------------------------------------------------------------

## 🐳 Step Deployment ke VPS

### 📁 File Wajib di Repository

-   Dockerfile
-   docker-compose.yml
-   .github/workflows/deploy.yml
-   README.md

------------------------------------------------------------------------

### 🔐 Setup GitHub Secrets

Repository → Settings → Secrets → Actions

Tambahkan: - VPS_HOST - VPS_USER - VPS_SSH_KEY - DOCKERHUB_USERNAME -
DOCKERHUB_TOKEN

------------------------------------------------------------------------

### 🖥 Setup VPS

Login:

``` bash
ssh student@203.194.115.210
```

Cek Docker:

``` bash
docker --version
docker compose version
```

Buat folder deployment:

``` bash
mkdir -p /home/student/spring-app/student4
cd /home/student/spring-app/student4
```

Buat file `.env`:

``` env
APP_PORT=9004
DOCKERHUB_USERNAME=<USERNAME_DOCKERHUB>
```

------------------------------------------------------------------------

### 🤖 Deploy Otomatis

Deployment berjalan saat push ke branch main.

CI/CD akan: - Build Maven
- Build Docker Image
- Push ke DockerHub
- Copy docker-compose ke VPS
- Pull image terbaru
- Restart container

------------------------------------------------------------------------

## 🌐 Link API Publik

Base URL:

    http://203.194.115.210:9004

### ❤️ Health

    GET /actuator/health

### 👤 Users

    GET /api/users
    POST /api/users

### 📦 Items

    GET /api/items
    POST /api/items
    GET /api/items/{id}
    PUT /api/items/{id}
    DELETE /api/items/{id}

------------------------------------------------------------------------

## 📮 Contoh Request API

### 👤 POST Users

``` json
{
  "name": "Budi",
  "email": "budi9004@mail.com"
}
```

### 📦 POST Items

``` json
{
  "sku": "SKU-001",
  "name": "Ikan Tuna",
  "stock": 100
}
```

------------------------------------------------------------------------

## 🧪 Testing API

### 📮 Postman Test

-   GET /api/users
-   POST /api/users
-   GET /api/items
-   POST /api/items

------------------------------------------------------------------------

### 🧪 cURL Test

GET Users:

``` bash
curl http://203.194.115.210:9004/api/users
```

POST Users:

``` bash
curl -X POST http://203.194.115.210:9004/api/users \
 -H "Content-Type: application/json" \
 -d '{"name":"Budi","email":"budi9004@mail.com"}'
```

POST Items:

``` bash
curl -X POST http://203.194.115.210:9004/api/items \
 -H "Content-Type: application/json" \
 -d '{"sku":"SKU-001","name":"Ikan Tuna","stock":100}'
```

------------------------------------------------------------------------

## ✅ Format Response

``` json
{
  "success": true,
  "message": "...",
  "data": {...}
}
```

------------------------------------------------------------------------

## 📝 Catatan

-   Jika POST Users gagal → gunakan email berbeda\
-   Jika API tidak bisa diakses → cek container

Cek container:

``` bash
docker ps
```

Cek log:

``` bash
docker logs spring-deploy-student4-app
```

------------------------------------------------------------------------

## 👨‍💻 Author

Bezaleel Firman L\
Assignment 33 --- Deployment REST API\
Spring Boot + Docker + GitHub Actions + VPS
