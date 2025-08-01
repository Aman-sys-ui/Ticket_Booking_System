# Ticket Booking System (Java CLI Based)

A command-line-based train ticket booking system written in **Java**, with support for user registration, login, and train search/management features. Data is stored in JSON files simulating a local database.

---

## ✅ Features

**User Management:**
- Sign Up: Register with name, username, password.
- Login: Authenticate securely (with hashed passwords via utility).
- Persistence: All user data saved in `users.json`.

**Train Management:**
- Search Trains: Based on source and destination.
- Add Trains: Admins can add train routes, seat layout, and schedules.
- Fetch Train by ID: Retrieve specific train details.
- Validation: Ensures correct route flow (source → destination).

---

## 🛠️ Technologies Used

- Java 17+
- Jackson (for JSON)
- CLI-based app
- File I/O for data
- UUIDs for train ID generation

---

## 📂 Project Structure

ticket_Booking_system/
├── app/src/main/java/org/example/

│ ├── entities/ # User.java, Trains.java

│ ├── services/ # TrainService.java, UserService.java

│ ├── util/ # UserServiceUtil.java

│ └── localDb/ # users.json, trains.json

├── README.md

└── build.gradle 


---

## 📁 Sample JSON

**trains.json:**

```json
[
  {
    "trainId": "uuid-1234",
    "stations": ["pune", "mumbai", "nashik"],
    "stationTimes": {
      "pune": "09:00",
      "mumbai": "11:30",
      "nashik": "14:00"
    },
    "seats": [["A1", "A2"], ["B1", "B2"]],
    "trainNo": "12345"
  }
]

users.json:


```json
[
  {
    "name": "Aman",
    "userName": "aman123",
    "password": "hashed-password"
  }
]

How to Run
## ▶️ How to Run

1. **Clone the Repo:**

```bash
git clone https://github.com/Aman-sys-ui/ticket_Booking_system.git
cd ticket_Booking_system

2 .Run Using Gradle:

./gradlew build
./gradlew run

Or open the project in IntelliJ/VS Code and run the Main class.

⚠️ Notes

Trains must follow valid station order and time constraints.
Jackson ObjectMapper handles all JSON read/write.
Passwords stored securely using hashing.s
No real DB — uses local JSON files.

## 🚀 Future Enhancements

- Seat booking with availability logic
- Role-based access (admin/user)
- DB integration (MySQL/Postgres)
- GUI (JavaFX) or Web UI (Spring Boot)

## 👨‍💻 Author

**Aman**  
🔗 GitHub: [Aman-sys-ui](https://github.com/Aman-sys-ui)