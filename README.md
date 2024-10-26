Parking Management System
A Spring Boot application to manage parking
lots, vehicles, and transactions for checking in
and out of parking spaces. The application
includes validation for parking lot occupancy,
unique vehicle constraints, and dummy data
preloaded into an H2 in-memory database.
Follow these instructions to set up and run the
application locally.

### Prerequisites
- Java 8 or higher
- Maven
- IDE of your choice (e.g., IntelliJ, Eclipse)
### Installation
1. **Clone the repository**
bash
git clone https://github.com/your-username/
parking-management-system.git
cd parking-management-system

2. Build the project
mvn clean install

4. Run the application
mvn spring-boot:run

The application will start on http://localhost:8080

Access the H2 Console
To view and interact with the preloaded data,
access the H2 console:
	 •	 URL: http://localhost:8080/h2-console
	 •	 JDBC URL: jdbc:h2:mem:testdb
	 •	 Username: sa
	 •	 Password: password

Endpoints
1. Register Parking Lot
	 •	 POST /api/parking/lot
	 •	 Registers a new parking lot with a unique lotId and location.
2. Register Vehicle
	 •	 POST /api/parking/vehicle
	 •	 Registers a new vehicle with a unique licensePlate.
3. Check In Vehicle
	 •	 POST /api/parking/checkin
	 •	 Checks a vehicle into a specified parking lot.
4. Check Out Vehicle
	 •	 POST /api/parking/checkout
Checks a vehicle out of the parking lot and updates availability.
6. Get Current Occupancy and Availability
	 •	 GET /api/parking/occupancy/{lotId}
	 •	 Returns the current occupancy and available spots for a parking lot.
7. Get All Vehicles in Parking Lot
	 •	 GET /api/parking/vehicles/{lotId}
	 •	 Returns a list of all vehicles currently parked in a lot.

Database Setup
The application uses an in-memory H2
database. On startup, it preloads data for
testing purposes based on the **data.sql** file in **src/main/resources*

Technologies Used
	 •	 Spring Boot - Backend framework
	 •	 H2 Database - In-memory testing
database
	 •	 Spring Data JPA - ORM for database interaction
	 •	 Lombok - Reduces boilerplate code
for model classes
	 •	 Java8 - Programming language
