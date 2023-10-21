CREATE TABLE location (
    id VARCHAR(255) PRIMARY KEY,
    location_name VARCHAR(255),
    level INT,
    region VARCHAR(255),
    x INT,
    y INT
);

CREATE TABLE location_building (
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
    location_id VARCHAR(255) REFERENCES location (id) ON DELETE CASCADE ON UPDATE CASCADE,
    location_key VARCHAR(255)
);

CREATE TABLE building (
    id VARCHAR(255) PRIMARY KEY,
    location_id VARCHAR(255),
    building_name VARCHAR(255),
    type VARCHAR(255)
);

CREATE TABLE shop_item (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    shop_id VARCHAR(255) REFERENCES building (id) ON DELETE CASCADE ON UPDATE CASCADE,
    building_key VARCHAR(255)
);

CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    location_id VARCHAR(255),
    building_name VARCHAR(255),
    building_id VARCHAR(255) REFERENCES building (id),
    building_key VARCHAR(255)
);

CREATE TABLE user_item (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    user_id VARCHAR(255) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    user_key VARCHAR(255)
);