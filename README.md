# 💰 Smart Expense Tracker

A full-stack Java backend project for tracking personal expenses with secure user authentication using Spring Boot, Spring Security, JWT, and JPA.

---

## 🚀 Features

- ✅ *User Registration & Login* (JWT-based authentication)
- ✅ *Add, Update, Delete Expenses*
- ✅ *Filter Expenses by User*
- ✅ *Get Expenses by Username*
- ✅ *Spring Security + JWT Integration*
- ✅ *Modular Project Structure*
- ✅ *RESTful APIs*
- ✅ *DTOs, Services, Controllers, Repositories*

---

## 🔐 Tech Stack

| Layer        | Tech Used                            |
|--------------|--------------------------------------|
| Language     | Java 17                              |
| Framework    | Spring Boot                          |
| Security     | Spring Security, JWT                 |
| ORM          | Spring Data JPA, Hibernate           |
| Database     | MySQL          |
| Tools        | Maven, IntelliJ, Talend, GitHub     |
| DevOps Ready | Docker, AWS, Kubernetes (soon)     |

---

## 🔒 Authentication Flow (JWT)

1. User registers → password is encoded
2. Logs in → receives JWT
3. JWT added to Authorization Header
4. Backend filters & validates JWT
5. Secured endpoints accessible

---

## 🛠 API Endpoints

| Method | Endpoint                     | Description               |
|--------|------------------------------|---------------------------|
| POST   | /auth/register             | Register new user         |
| POST   | /auth/login                | Login & get JWT           |
| GET    | /expenses/user/{username}  | Get expenses for a user   |
| POST   | /expenses//filter          | Filter expenses for a user   |
| GET    | /expenses/{id}             | Get expense by ID         |
| POST   | /expenses                  | Add new expense           |
| PUT    | /expenses/{id}             | Update an expense         |
| DELETE | /expenses/{id}             | Delete an expense         |

---

## 📌 Current Status

> ✅ Completed:
- User authentication with JWT
- Secure role-based access
- Expense management with all CRUD
- Dockerize the project
- Add Kubernetes deployment

> 🔜 Next:
- Integrate AWS (EC2, S3)
- Add analytics with AI 📊

---

## 🙋‍♂ Author

*Dibya Bikash Pradhan*  
Java Backend Developer | DevOps Learner  
🔗 GitHub: [Dibya819](https://github.com/Dibya819)

---

## 🌟 Give it a star if you liked it!

