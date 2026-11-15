CREATE TABLE cart_item
(
    id         BIGINT PRIMARY KEY,
    user_id    BIGINT  NOT NULL,
    product_id BIGINT  NOT NULL,
    quantity   INTEGER NOT NULL,

    CONSTRAINT fk_cart_item_user
        FOREIGN KEY (user_id) REFERENCES app_user (id),

    CONSTRAINT fk_cart_item_product
        FOREIGN KEY (product_id) REFERENCES product (id),

    CONSTRAINT unique_cart_item_user_product
        UNIQUE (user_id, product_id)
);

CREATE SEQUENCE cart_item_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE wish_item
(
    id         BIGINT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    CONSTRAINT fk_wish_item_user
        FOREIGN KEY (user_id) REFERENCES app_user (id),

    CONSTRAINT fk_wish_item_product
        FOREIGN KEY (product_id) REFERENCES product (id),

    CONSTRAINT unique_wish_item_user_product
        UNIQUE (user_id, product_id)
);

CREATE SEQUENCE wish_item_id_seq START WITH 1 INCREMENT BY 1;