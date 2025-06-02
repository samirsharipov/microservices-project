# Microservices Project 

Bu loyiha mikroservis arxitekturasi asosida tashkil etilgan bo‘lib, quyidagi komponentlardan iborat:

## 📦 Arxitektura

microservices-project/ 

/ api-gateway 

/ discovery-service

/ auth-service

/ classificator-service

## 🔧 Texnologiyalar

- Java 17
- Spring Boot 3.x
- Spring Cloud (Gateway, Eureka)
- Spring Security + JWT
- PostgreSQL
- Lombok, MapStruct

## 🧩 Servislar

### ✅ Discovery Service (Eureka)
- Port: `8761`
- Boshqa servislar shu yerda ro‘yxatdan o‘tadi

### ✅ API Gateway
- Spring Cloud Gateway
- JWT tokenni `auth-service` orqali `/me` endpoint bilan tekshiradi
- Noto‘g‘ri token — `401 Unauthorized`

### ✅ Auth Service
- Port: `8081`
- Endpoints:
    - `POST /register`
    - `POST /login`
    - `GET /me`

### ✅ Classificator Service
- Port: `8082`
- Entitylar: `Country`, `Region`, `District`
- Endpoints:
    - `CRUD`: `/countries`, `/regions`, `/districts`
    - `POST /init` — JSON fayldan boshlang‘ich ma’lumotlarni yuklaydi

## 🔐 Xavfsizlik

- Barcha endpointlar JWT token orqali himoyalangan
- Header format: `Authorization: Bearer <token>`

## ▶️ Loyihani ishga tushirish

Har bir servis alohida Spring Boot ilova sifatida ishga tushiriladi. Avval PostgreSQL DB tayyor holatda bo‘lishi kerak.

1. PostgreSQL DB yaratish (`classificator` va `auth` uchun alohida)
2. Har bir servisni `application.yml` orqali moslab olish
3. Servislarni ketma-ket ishga tushirish:
    - discovery-service
    - auth-service
    - classificator-service
    - api-gateway

## 🧪 Testlash ketma-ketligi

1. `POST /register` orqali foydalanuvchi yaratish
2. `POST /login` bilan JWT token olish
3. Gateway orqali `classificator` servisiga token bilan so‘rov yuborish

---

## ✍️ Muallif

Samir Sharipov  
🔗 https://github.com/samirsharipov/microservices-project.git