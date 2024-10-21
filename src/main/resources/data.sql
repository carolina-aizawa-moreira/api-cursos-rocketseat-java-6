CREATE TABLE Cursos (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    category VARCHAR(100) NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at DATE NULL,
    updated_at DATE NULL,
    PRIMARY KEY(id)
);

INSERT INTO Cursos (name, category, active, created_at, updated_at)
VALUES ('SpringBoot', 'Java', TRUE, '2021-01-01', null),
       ('Angular', 'JavaScript', TRUE, '2021-01-01', null),
       ('React', 'JavaScript', TRUE, '2021-01-01', null),
       ('Vue', 'JavaScript', TRUE, '2021-01-01', null),
       ('Spring', 'Java', TRUE, '2021-01-01', null);