CREATE TABLE location (
    id VARCHAR(255) PRIMARY KEY,
    location_name VARCHAR(255),
    level INT,
    region VARCHAR(255),
    x INT,
    y INT
);

CREATE TABLE building (
    id SERIAL PRIMARY KEY,
    building_name VARCHAR(255),
    x INT,
    y INT,
    location_id VARCHAR(255) REFERENCES location (id)  ON DELETE CASCADE ON UPDATE CASCADE,
    location_key VARCHAR(255)
);

CREATE TABLE monster_group (
    id SERIAL PRIMARY KEY,
    monster_group_name VARCHAR(255),
    location_id VARCHAR(255) REFERENCES location (id)  ON DELETE CASCADE ON UPDATE CASCADE,
    location_key VARCHAR(255)
);