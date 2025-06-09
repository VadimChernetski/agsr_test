CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE TABLE agsr.role(
    id SERIAL PRIMARY KEY,
    role VARCHAR(15)
);

INSERT INTO agsr.role(role)
VALUES
    ('ADMINISTRATOR'),
    ('VIEWER');

CREATE TABLE agsr.user(
    id SERIAL PRIMARY KEY,
    nickname VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    role_id INT NOT NULL REFERENCES agsr.role(id)
);

CREATE INDEX nickname_hash_idx ON agsr.user USING HASH (nickname);

INSERT INTO agsr.user(nickname, password, role_id)
VALUES
    ('administrator', '{bcrypt}$2a$10$BrgQwgrRHH2cESAGvMWZaO4pK8i8aPkML2nzjJKic3Zq3.WjGlbp6', 1),
    ('viewer', '{bcrypt}$2a$10$BrgQwgrRHH2cESAGvMWZaO4pK8i8aPkML2nzjJKic3Zq3.WjGlbp6', 2);

CREATE TABLE agsr.refresh_token(
    id SERIAL8 PRIMARY KEY,
    user_id INT8 REFERENCES agsr.user(id) NOT NULL,
    token VARCHAR NOT NULL
);

CREATE TABLE agsr.measurement_unit(
    id SERIAL PRIMARY KEY,
    unit VARCHAR(15)
);

INSERT INTO agsr.measurement_unit(unit)
VALUES
    ('BAR'),
    ('VOLTAGE'),
    ('CELSIUS'),
    ('PERCENTAGE');

CREATE TABLE agsr.sensor_type(
    id SERIAL PRIMARY KEY,
    type VARCHAR(15)
);

INSERT INTO agsr.sensor_type(type)
VALUES
    ('PRESSURE'),
    ('VOLTAGE'),
    ('TEMPERATURE'),
    ('HUMIDITY');

CREATE TABLE agsr.sensor(
    id SERIAL8 PRIMARY KEY,
    model VARCHAR(15) NOT NULL,
    min SMALLINT,
    max SMALLINT NOT NULL,
    type_id INT REFERENCES agsr.sensor_type(id) NOT NULL,
    measurement_unit_id INT REFERENCES agsr.measurement_unit(id) NOT NULL
);

CREATE INDEX idx_model_trgm ON agsr.sensor USING GIN (model gin_trgm_ops);

INSERT INTO agsr.sensor(model, min, max, type_id, measurement_unit_id)
VALUES
    ('ac-23', 22, 45, 1, 1),
    ('ac-24', 210, 220, 2, 2),
    ('ac-25', 10, 35, 3, 3),
    ('ac-26', 20, 90, 4, 4);

CREATE TABLE agsr.installed_sensor(
    id SERIAL8 PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    sensor_id INT8 REFERENCES agsr.sensor(id) NOT NULL,
    location VARCHAR(40),
    description VARCHAR(200)
);

CREATE INDEX idx_name_trgm ON agsr.installed_sensor USING GIN (name gin_trgm_ops);

INSERT INTO agsr.installed_sensor(name, sensor_id, location, description)
VALUES
    ('Barometer', 1, 'kitchen', 'description0'),
    ('Voltmeter', 2, 'garage', 'description1'),
    ('Thermometer', 3, 'bedroom', 'description2'),
    ('Hygrometer', 1, 'kitchen', 'description3');