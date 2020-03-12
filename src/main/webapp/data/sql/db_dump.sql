create table product_category
(
    id          serial  not null
        constraint product_category_pk
            primary key,
    name        varchar not null,
    department  varchar not null,
    description varchar
);

alter table product_category
    owner to postgres;

create unique index product_category_id_uindex
    on product_category (id);

create table supplier
(
    id          serial  not null
        constraint supplier_pk
            primary key,
    name        varchar not null,
    description varchar
);

alter table supplier
    owner to postgres;

create table product
(
    id            serial           not null
        constraint product_pk
            primary key,
    name          varchar          not null,
    description   varchar,
    default_price double precision not null,
    currency      varchar          not null,
    supplier_id   integer          not null
        constraint product_supplier_id_fk
            references supplier,
    category_id   integer          not null
        constraint product_product_category_id_fk
            references product_category
);

alter table product
    owner to postgres;

create unique index product_id_uindex
    on product (id);

create unique index supplier_id_uindex
    on supplier (id);

create table "user"
(
    id       serial  not null
        constraint user_pk
            primary key,
    username varchar not null,
    password varchar not null
);

alter table "user"
    owner to postgres;

create table customer
(
    id           serial  not null
        constraint customer_pk
            primary key,
    first_name   varchar not null,
    last_name    varchar not null,
    email        varchar not null,
    phone_number varchar not null,
    user_id      integer
        constraint customer_user_id_fk
            references "user"
);

alter table customer
    owner to postgres;

create unique index customer_id_uindex
    on customer (id);

create table customer_address
(
    id           serial  not null
        constraint customer_address_pk
            primary key,
    customer_id  integer not null
        constraint customer_address_customer_id_fk
            references customer,
    zip_code     varchar not null,
    city         varchar not null,
    address      varchar not null,
    address_type varchar
);

alter table customer_address
    owner to postgres;

create unique index customer_address_id_uindex
    on customer_address (id);

create table "order"
(
    id            serial           not null
        constraint order_pk
            primary key,
    customer_id   integer          not null
        constraint order_customer_id_fk
            references customer,
    total_price   double precision not null,
    status        varchar          not null,
    checkout_date timestamp        not null,
    payment_date  timestamp,
    shipping_id   integer
        constraint order_customer_address_id_fk_2
            references customer_address,
    billing_id    integer
        constraint order_customer_address_id_fk
            references customer_address
);

alter table "order"
    owner to postgres;

create table line_item
(
    id         serial           not null
        constraint line_item_pk
            primary key,
    order_id   integer          not null
        constraint line_item_order_id_fk
            references "order",
    product_id integer          not null
        constraint line_item_product_id_fk
            references product,
    unit_price double precision not null,
    quantity   integer          not null
);

alter table line_item
    owner to postgres;

create unique index line_item_id_uindex
    on line_item (id);

create unique index order_id_uindex
    on "order" (id);

create unique index user_id_uindex
    on "user" (id);

create unique index user_password_uindex
    on "user" (password);

create unique index user_username_uindex
    on "user" (username);


