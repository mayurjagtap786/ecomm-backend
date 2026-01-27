CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    pwd VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

insert into users (pwd,role,username) values('{bcrypt}$2a$12$82KMkbNgeb9hQuULdIa5eOOHGpEjVRbF7k2N9WAPNYbEVaEoelSZa','ADMIN','mayur@example.com');