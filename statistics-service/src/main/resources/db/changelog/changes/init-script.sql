CREATE TABLE agsr.statistic(
    id SERIAL PRIMARY KEY,
    pressure_sensors_number INT8,
    voltage_sensors_number INT8,
    temperature_sensors_number INT8,
    humidity_sensors_number INT8,
    date TIMESTAMP NOT NULL
);