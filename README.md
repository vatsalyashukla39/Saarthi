# Saarthi – Console‑Based Ticket Booking System

An educational Java application demonstrating a simple ticket booking system using the MVC pattern and Java Streams API for efficient parallel data processing. Saarthi allows users to browse available seats, book or cancel tickets, and view their bookings—all via a console interface.

---

##  Features

- **Seat Management**  
  View all available seats, book a seat, or cancel an existing booking.
- **Concurrent Booking Handling**  
  Uses Java’s Streams API to process multiple booking or cancellation requests in parallel.
- **MVC Architecture**  
  Clear separation of Model, View, and Controller layers for maintainable and testable code.
- **In‑Memory Data Storage**  
  All seat and booking data is stored in Java collections for simplicity.
- **Interactive Console UI**  
  Menu‑driven interface guides the user through booking actions.

---

##  Project Structure
app/
└── src/main/java/org/example/
├── entities/
├── localDb/ 
├── service/ 
├── util/ 
└── App.java # Application entry point 


---

## 🛠️ Requirements

- Java 8.0 or higher
- Maven (for build and dependency management)

---

## 🎯 Getting Started

1. **Clone the repository**  
   ```bash
   git clone https://github.com/vatsalyashukla39/Saarthi.git
   cd Saarthi/app


