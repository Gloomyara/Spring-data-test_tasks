DROP TABLE IF EXISTS orders_products;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS customers
(
    id           UUID DEFAULT random_uuid(),
    username     VARCHAR(64)  NOT NULL,
    balance      NUMERIC DEFAULT 0,
    CONSTRAINT   pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products
(
    id           UUID DEFAULT random_uuid(),
    name         VARCHAR(64)  NOT NULL,
    description  VARCHAR(255) NOT NULL,
    price        NUMERIC      NOT NULL,
    quantity     BIGINT DEFAULT 0,
    CONSTRAINT   pk_products PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id           UUID DEFAULT random_uuid(),
    customer_id  UUID         NOT NULL,
    description  VARCHAR(255) NOT NULL,
    paid         BOOLEAN DEFAULT FALSE,
    update_date  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT   pk_orders PRIMARY KEY (id),
    CONSTRAINT   fk_users  FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS orders_products
(
    id   UUID DEFAULT random_uuid(),
    order_id             UUID,
    product_id           UUID,
    quantity             INTEGER DEFAULT 0,
    CONSTRAINT   fk_orders   FOREIGN KEY (order_id)  REFERENCES orders   (id) ON DELETE CASCADE,
    CONSTRAINT   fk_products FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    CONSTRAINT   pk_order_products PRIMARY KEY (id)
);
