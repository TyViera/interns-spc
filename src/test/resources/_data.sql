INSERT INTO spc.segments (id, segment_code, airline_code, departure_airport_code, departure_date, departure_terminal,
                          arrival_airport_code, arrival_date, arrival_terminal)
VALUES (random_uuid(), 'AA123', 'AA', 'JFK', '2020-01-01 00:00:00', 'T1', 'LAX', '2020-01-01 00:00:00', 'T1'),
       ('1', '1', 'AA', 'JFK', '2020-01-01 00:00:00', 'T1', 'LAX', '2020-01-01 00:00:00', 'T1'),;