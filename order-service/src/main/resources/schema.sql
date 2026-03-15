create table orders (
    id bigint not null,
    order_id varchar(100),
    product_id varchar(100),
    quantity integer,
    amount float(25),
    status varchar(10) check (status in ('CREATED','CONFIRMED','REJECT','CANCELLED')),
    created_at timestamp(10),
    created_by varchar(20),
    updated_at timestamp(10),
    updated_by varchar(20),
    primary key (id))