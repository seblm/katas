CREATE KEYSPACE IF NOT EXISTS cats_effect WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 1 };

USE cats_effect;

CREATE TABLE IF NOT EXISTS cats(id TIMEUUID PRIMARY KEY, name TEXT);

INSERT INTO cats(id, name) VALUES (9761fc50-af5d-11e7-8484-23ac7386a7b4, 'grosminet');
INSERT INTO cats(id, name) VALUES (a83d1e20-b376-11e7-8d2c-c17e24c486da, 'catwoman');
INSERT INTO cats(id, name) VALUES (017cd6d0-b807-11e7-82b1-6db11998e458, 'felix');
