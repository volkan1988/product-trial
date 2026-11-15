-- PRODUCT
ALTER TABLE product
    ALTER COLUMN description SET NOT NULL,
ALTER
COLUMN created_at SET NOT NULL;

ALTER TABLE product
    ADD CONSTRAINT unique_product_code UNIQUE (code);


-- USER

ALTER TABLE app_user
    ADD CONSTRAINT unique_app_user_username UNIQUE (username);

CREATE UNIQUE INDEX unique_app_user_email_lower
    ON app_user (LOWER(email));

-- CART ITEM

ALTER TABLE cart_item
    ALTER COLUMN id SET DEFAULT nextval('cart_item_id_seq');

ALTER SEQUENCE cart_item_id_seq
    OWNED BY cart_item.id;

-- WISH ITEM

ALTER TABLE wish_item
    ALTER COLUMN id SET DEFAULT nextval('wish_item_id_seq');

ALTER SEQUENCE wish_item_id_seq
    OWNED BY wish_item.id;