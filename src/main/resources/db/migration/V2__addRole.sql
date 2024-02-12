insert into roles (name)
values ('ROLE_ADMIN');
insert into roles (name)
values ('ROLE_USER');
insert into users (email, password, username) values ('admin@admin.com', '$2a$10$wrT9.HXyWywSs9Be/AjArOipbQn8NaHIFUlIEyaLuUSWTZiNcW..O', 'admin');
insert into user_roles (user_id, role_id) VALUES (1, 1)