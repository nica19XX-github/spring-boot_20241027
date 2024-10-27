-- Create parking_lots table
CREATE TABLE IF NOT EXISTS parking_lots (
    lotId INT PRIMARY KEY,
    location VARCHAR(255),
    capacity INT,
    occupiedSpaces INT,
    availableSpots INT
);

-- Create vehicle table
CREATE TABLE IF NOT EXISTS vehicle (
    licensePlate VARCHAR(50) PRIMARY KEY,
    type VARCHAR(50),
    ownerName VARCHAR(255)
);

-- Create transaction_tbl table
CREATE TABLE IF NOT EXISTS transaction_tbl (
    transactionId INT PRIMARY KEY,
    lot_id INT,
    license_plate VARCHAR(50),
    checkIn TIMESTAMP,
    checkOut TIMESTAMP,
    isParked BOOLEAN,
    FOREIGN KEY (lot_id) REFERENCES parking_lots(lotId),
    FOREIGN KEY (license_plate) REFERENCES vehicle(licensePlate)
);

-- Insert data into parking_lots table
INSERT INTO parking_lots (lotId, location, capacity, occupiedSpaces, availableSpots)
VALUES
(1, 'Makati', 10, 5, 5),
(2, 'Quezon City', 8, 6, 2),
(3, 'Taguig', 10, 7, 3),
(4,'Manila', 6, 4, 2),
(5, 'Pasig', 9, 5, 4),
(6, 'Cebu City', 7, 4, 3),
(7, 'Davao City', 10, 6, 4),
(8, 'Baguio', 5, 2, 3),
(9, 'Iloilo City', 8, 6, 2),
(10, 'Cagayan de Oro', 10, 8, 2);

-- Insert data into vehicle table
INSERT INTO vehicle (licensePlate, type, ownerName)
VALUES
('ABC123', 'CAR', 'John Doe'),
('XYZ789', 'MOTORCYCLE', 'Jane Smith'),
('LMN456', 'TRUCK', 'Mike Johnson'),
('DEF234', 'CAR', 'Emma Davis'),
('GHI567', 'MOTORCYCLE', 'Chris Brown'),
('JKL890', 'TRUCK', 'Sara Wilson'),
('PQR123', 'CAR', 'David Lee'),
('STU456', 'TRUCK', 'Sophia Green'),
('VWX789', 'MOTORCYCLE', 'Linda White'),
('YZA123', 'CAR', 'James Black');

-- Insert data into transaction_tbl
INSERT INTO transaction_tbl (transactionId, lot_id, license_plate, checkIn, checkOut, isParked)
VALUES
(1, 1, 'ABC123', '2023-10-20 08:30:00', NULL, TRUE),
(2, 1, 'XYZ789', '2023-10-20 09:00:00', '2023-10-20 18:00:00', FALSE),
(3, 1, 'LMN456', '2023-10-21 07:00:00', NULL, TRUE),
(4, 1, 'DEF234', '2023-10-21 08:15:00', NULL, TRUE),
(5, 1, 'GHI567', '2023-10-21 10:30:00', '2023-10-21 15:00:00', FALSE),
(6, 2, 'JKL890', '2023-10-22 08:45:00', NULL, TRUE),
(7, 2, 'PQR123', '2023-10-22 09:20:00', '2023-10-22 17:30:00', FALSE),
(8, 2, 'STU456', '2023-10-22 11:00:00', NULL, TRUE),
(9, 2, 'VWX789', '2023-10-23 06:30:00', NULL, TRUE),
(10, 2, 'YZA123', '2023-10-23 07:15:00', NULL, TRUE);