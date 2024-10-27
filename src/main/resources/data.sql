--Insert data into parking_lots table
INSERT INTO parking_lots (lot_id, location,
capacity, occupied_spaces, available_spots)
VALUES
(1, 'Makati', 10, 5, 5),
(1, 'Quezon City', 8, 6, 2),
(1, 'Taguig', 10, 7, 3),
(1, 'Manila', 6, 4, 2),
(1, 'Pasig', 9, 5, 4),
(2, 'Cebu City', 7, 4, 3),
(2, 'Davao City', 10, 6, 4),
(2, 'Baguio', 5, 2, 3),
(2, 'Iloilo City', 8, 6, 2),
(2, 'Cagayan de Oro', 10, 8, 2);

-- Insert data into vehicle table
INSERT INTO vehicle (license_plate,
vehicle_type, owner_name) VALUES
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
INSERT INTO transaction_tbl (transaction_id,
lot_id, license_plate, check_in, check_out,
is_parked) VALUES
(1, 1, 'ABC123', '2023-10-20 08:30:00', NULL, TRUE),
(2, 1, 'XYZ789', '2023-10-20 09:00:00', '2023-10-20 18:00:00', FALSE),
(3, 1, 'LMN456', '2023-10-21 07:00:00', NULL, TRUE),
(4, 1, 'DEF234', '2023-10-21 08:15:00', NULL,TRUE),
(5, 1, 'GHI567', '2023-10-21 10:30:00','2023-10-21 15:00:00', FALSE),
(6, 2, 'JKL890', '2023-10-22 08:45:00', NULL,TRUE),
(7, 2, 'PQR123', '2023-10-22 09:20:00','2023-10-22 17:30:00', FALSE),
(8, 2, 'STU456', '2023-10-22 11:00:00', NULL,TRUE),
(9, 2, 'VWX789', '2023-10-23 06:30:00',NULL, TRUE),
(10, 2, 'YZA123', '2023-10-23 07:15:00',NULL, TRUE);