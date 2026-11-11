
CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE product (
                         id BIGINT PRIMARY KEY DEFAULT nextval('product_id_seq'),
                         code VARCHAR(255),
                         name VARCHAR(255),
                         description TEXT,
                         image VARCHAR(255),
                         category VARCHAR(255),
                         price DOUBLE PRECISION NOT NULL,
                         quantity INTEGER NOT NULL,
                         internal_reference VARCHAR(255),
                         shell_id BIGINT,
                         inventory_status VARCHAR(50),
                         rating INTEGER NOT NULL,
                         created_at TIMESTAMP,
                         updated_at TIMESTAMP
);

ALTER SEQUENCE product_id_seq
    OWNED BY product.id;
