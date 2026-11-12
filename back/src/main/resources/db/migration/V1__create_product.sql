
CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE product (
                         id BIGINT PRIMARY KEY DEFAULT nextval('product_id_seq'),
                         code VARCHAR(9) NOT NULL,
                         name VARCHAR(100) NOT NULL,
                         description TEXT,
                         image VARCHAR(255),
                         category VARCHAR(50) NOT NULL,
                         price DOUBLE PRECISION NOT NULL,
                         quantity INTEGER NOT NULL,
                         internal_reference VARCHAR(255),
                         shell_id BIGINT,
                         inventory_status VARCHAR(50),
                         rating INTEGER,
                         created_at TIMESTAMP,
                         updated_at TIMESTAMP
);

ALTER SEQUENCE product_id_seq
    OWNED BY product.id;
