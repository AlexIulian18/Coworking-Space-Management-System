# Coworking Space Management System

O aplicație web full-stack dezvoltată pentru administrarea eficientă a unui spațiu de coworking. Aplicația permite administrarea resurselor (birouri, săli de ședințe) și a fluxului de utilizatori.

## 🚀 Tehnologii Folosite

### Backend
* **Java 21**
* **Spring Boot (3.5.7)**
* **Spring Data JPA**
* **PostgreSQL** (Bază de date)
* **Maven** (Managementul dependențelor)
* **Lombok** (Pentru reducerea codului boilerplate)

### Frontend
* **React 19**
* **Axios** (Pentru request-uri HTTP către API)

## ⚙️ Structura Proiectului

Proiectul este împărțit în două module principale:
* `/coworking_backend` - API-ul RESTful construit cu Spring Boot.
* `/coworking_frontend` - Interfața utilizator construită cu React.

## 🛠️ Instalare și Rulare Locală

Pentru a rula acest proiect local, ai nevoie de [Java 21](https://jdk.java.net/21/), [Node.js](https://nodejs.org/) și [PostgreSQL](https://www.postgresql.org/) instalate pe mașina ta.

### 1. Configurarea Bazei de Date (PostgreSQL)
Aplicația necesită o bază de date PostgreSQL. Crează o bază de date locală corespunzătoare setărilor din fișierul de configurare din backend (ex: port 5432, username, password).
*(Notă: Găsești un fișier `backup_coworking.sql` în rădăcina proiectului cu datele/structura inițială.)*

### 2. Pornirea Backend-ului (Spring Boot)
Navighează în folderul de backend și rulează aplicația:
```bash
cd coworking_backend/coworking_backend
./mvnw spring-boot:run
```
*(Backend-ul va rula implicit pe `http://localhost:8080`, sau pe portul configurat)*

### 3. Pornirea Frontend-ului (React)
Deschide un terminal nou, navighează în folderul de frontend și instalează pachetele:
```bash
cd coworking_frontend
npm install
npm start
```
*(Frontend-ul va rula pe `http://localhost:3000`)*

## 📝 Documentație Adițională
Documentația extinsă a proiectului se regăsește în fișierul `DocumentatieProiectBD_Voicu_Alexandru_333AA.pdf`.
