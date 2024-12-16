CREATE TABLE orders
(
    id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE addons
(
    id    integer     NOT NULL,
    name  varchar(60) NOT NULL,
    price float(53)   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_addons_name UNIQUE (name)
);

CREATE TABLE cuisines
(
    id   integer     NOT NULL,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_cuisines_name UNIQUE (name)
);

CREATE TABLE menu_item_categories
(
    id   integer     NOT NULL,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_menu_item_categories_name UNIQUE (name)
);

CREATE TABLE menu_items
(
    id          integer     NOT NULL,
    name        varchar(60) NOT NULL,
    price       float(53)   NOT NULL,
    category_id integer     NOT NULL,
    cuisine_id  integer     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_menu_items_name UNIQUE (name),
    CONSTRAINT fk_menu_items_category_id FOREIGN KEY (category_id) REFERENCES menu_item_categories (id),
    CONSTRAINT fk_menu_items_cuisine_id FOREIGN KEY (cuisine_id) REFERENCES cuisines (id)
);

CREATE TABLE menu_items_addons
(
    menu_item_id integer NOT NULL,
    addon_id     integer NOT NULL,
    CONSTRAINT fk_menu_items_addons_menu_item_id FOREIGN KEY (menu_item_id) REFERENCES menu_items (id),
    CONSTRAINT fk_menu_items_addons_addon_id FOREIGN KEY (addon_id) REFERENCES addons (id)
);

CREATE TABLE order_items
(
    id           integer NOT NULL,
    menu_item_id integer NOT NULL,
    order_id     integer,
    PRIMARY KEY (id),
    CONSTRAINT fk_order_items_menu_item_id FOREIGN KEY (menu_item_id) REFERENCES menu_items (id),
    CONSTRAINT fk_order_items_order_id FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE TABLE order_items_addons
(
    order_item_id integer NOT NULL,
    addon_id      integer NOT NULL,
    CONSTRAINT fk_order_items_addons_order_item_id FOREIGN KEY (order_item_id) REFERENCES order_items (id),
    CONSTRAINT fk_order_items_addons_addon_id FOREIGN KEY (addon_id) REFERENCES addons (id)
);

CREATE INDEX idx_menu_items_category_id ON menu_items (category_id);
CREATE INDEX idx_menu_items_cuisine_id ON menu_items (cuisine_id);
CREATE INDEX idx_menu_items_name ON menu_items (name);
CREATE INDEX idx_order_items_menu_item_id ON order_items (menu_item_id);
CREATE INDEX idx_order_items_order_id ON order_items (order_id);
CREATE INDEX idx_menu_item_categories_name ON menu_item_categories (name);
CREATE INDEX idx_cuisines_name ON cuisines (name);
CREATE INDEX idx_addons_name ON addons (name);
CREATE INDEX idx_menu_items_addons_menu_item_id ON menu_items_addons (menu_item_id);
CREATE INDEX idx_menu_items_addons_addon_id ON menu_items_addons (addon_id);
CREATE INDEX idx_order_items_addons_order_item_id ON order_items_addons (order_item_id);
CREATE INDEX idx_order_items_addons_addon_id ON order_items_addons (addon_id);


CREATE SEQUENCE addons_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE cuisines_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE menu_item_categories_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE menu_items_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE order_items_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE orders_seq START WITH 1 INCREMENT BY 50;