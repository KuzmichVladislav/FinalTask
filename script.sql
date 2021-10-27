create table if not exists users
(
    user_id     int auto_increment
        primary key,
    login       varchar(20)                                          not null,
    password    varchar(100)                                         not null,
    role        enum ('ADMIN', 'CLIENT', 'TRAINER') default 'CLIENT' not null,
    name        varchar(45)                                          not null,
    surname     varchar(45)                                          not null,
    is_active   tinyint(1)                          default 1        not null,
    mail        varchar(45)                                          not null,
    is_verified tinyint(1)                          default 0        not null,
    image       longblob                                             null,
    constraint user_unique_index
        unique (login, mail) comment '''Unique index for user''',
    constraint users_login_uindex
        unique (login)
);

create table if not exists clients
(
    client_id     int                                not null
        primary key,
    register_date datetime default CURRENT_TIMESTAMP not null,
    phone_number  varchar(18)                        not null,
    discount      int      default 0                 not null,
    money         decimal  default 0                 not null,
    constraint client_id
        foreign key (client_id) references users (user_id)
            on update cascade on delete cascade
);

create table if not exists comments
(
    comment_id   int auto_increment
        primary key,
    comment_date datetime   default CURRENT_TIMESTAMP not null,
    comment_text longtext                             not null,
    user_id      int                                  not null,
    is_active    tinyint(1) default 1                 not null,
    constraint user_id
        foreign key (user_id) references users (user_id)
            on update cascade on delete cascade
);

create index user_id_idx
    on comments (user_id);

create table if not exists trainers
(
    trainer_id    int                                not null
        primary key,
    phone_number  varchar(18)                        not null,
    register_date datetime default CURRENT_TIMESTAMP not null,
    description   longtext                           null,
    experience    longtext                           null,
    constraint trainer_id
        foreign key (trainer_id) references users (user_id)
            on update cascade on delete cascade
);

create table if not exists orders
(
    order_id         int auto_increment
        primary key,
    client_id        int                                                            not null,
    trainer_id       int                                                            not null,
    start_order_date datetime                                                       not null,
    exercises        longtext                                                       null,
    nutrition        longtext                                                       null,
    price            decimal                                                        null,
    client_comment   longtext                                                       null,
    is_active        tinyint                              default 1                 not null,
    end_order_date   datetime                                                       not null,
    register_date    datetime                             default CURRENT_TIMESTAMP not null,
    order_status     enum ('NEW', 'REVIEWED', 'ACCEPTED') default 'NEW'             not null,
    constraint client_idx
        foreign key (client_id) references clients (client_id)
            on update cascade on delete cascade,
    constraint trainer_idx
        foreign key (trainer_id) references trainers (trainer_id)
            on update cascade on delete cascade
);

create index client_id_idx
    on orders (client_id);

create index trainer_id_idx
    on orders (trainer_id);