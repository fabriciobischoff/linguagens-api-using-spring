CREATE TABLE tb_user (
	id uuid,
	username VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL
);
insert into tb_user values('0319d66b-8e08-417f-b32d-5c6f114a4554', 'maria', '$2a$10$Y4XE2lNmxtZEkQDVKmEN9e8bcUpQQiob3SdlUc1/dIqYvDmJaIXJu');
insert into tb_user values('ffeb4b10-085a-4b56-9c31-6e79bcf0d0aa', 'joao', '$2a$10$Y4XE2lNmxtZEkQDVKmEN9e8bcUpQQiob3SdlUc1/dIqYvDmJaIXJu');

CREATE TABLE tb_role (
	id UUID,
	rolename Varchar(255) NOT NULL UNIQUE
);
insert into tb_role values(gen_random_uuid(), 'ROLE_ADMIN');
insert into tb_role values(gen_random_uuid(), 'ROLE_USER');

CREATE TABLE tb_users_roles (
	user_id UUID NOT NULL,
	role_id UUID NOT NULL
);
insert into tb_users_roles values('0319d66b-8e08-417f-b32d-5c6f114a4554', 'ee62dce0-ae35-4407-9c42-1386ae3cf1e3');
insert into tb_users_roles values('ffeb4b10-085a-4b56-9c31-6e79bcf0d0aa', '41f1e8f1-f0a6-45f9-b080-acaf6031cc35');
