create table orders (
    id bigint not null,
    order_id varchar(255),
    product_id varchar(255),
    quantity integer,
    amount float(53),
    status varchar(10) check (status in ('CREATED','CONFIRMED','REJECT','CANCELLED')),
    created_at timestamp(6),
    created_by varchar(20),
    updated_at timestamp(6),
    updated_by varchar(20),
    primary key (id))