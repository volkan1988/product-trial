CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE app_user
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('user_id_seq'),
    username   VARCHAR(100) NOT NULL,
    firstname  VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP
);

ALTER SEQUENCE user_id_seq
    OWNED BY app_user.id;
