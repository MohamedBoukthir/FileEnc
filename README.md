# File Encryption
## Prerequisites

Ensure the following tools are installed on your machine:

- **Java Development Kit (JDK)** (version 21).
- **Node.js** and **npm** (latest LTS version recommended).
- **Maven** (if the project does not include Maven Wrapper).
- **Docker**.

---

## Getting Started

### 1. Clone the Repository

Run the following commands in your terminal:

```bash
git clone https://github.com/MohamedBoukthir/FileEnc.git
cd FileEnc
```
### 2. Pull PostgreSQL image from docker
```dockerfile
docker-compose up
```
### 3. Backend Setup
Navigate to the Backend Directory
```bash
cd backend
```
Build the Backend
```bash
./mvnw clean install
```
Run
```bash
./mvnw spring-boot:run
```
### 4. Frontend Setup
Navigate to the Frontend Directory
```bash
cd frontend
```
Install Dependencies
```bash
npm install
```
Start the Frontend
```bash
npm start
```

## Demo

**Login Page**

![Login.png](Demo%2FLogin.png)

**FileEncryption**

![FileEncryption.png](Demo%2FFileEncryption.png)

**Testing it**

![TestFileEncryption.png](Demo%2FTestFileEncryption.png)

### Running the Application
Once both the backend and frontend are running:

Access the Frontend Application at:
```bash
http://localhost:4200
```
Access the Backend API at:
```bash
http://localhost:8080
```