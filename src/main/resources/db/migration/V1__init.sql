CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE roles
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    created_by   VARCHAR(500),
    updated_by   VARCHAR(500),
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

COMMENT ON TABLE roles IS 'Таблица ролей';

COMMENT ON COLUMN roles.id IS 'Уникальный идентификатор роли';
COMMENT ON COLUMN roles.name IS 'Название роли';
COMMENT ON COLUMN roles.created_by IS 'Кем создана запись';
COMMENT ON COLUMN roles.updated_by IS 'Кем обновлена запись';
COMMENT ON COLUMN roles.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN roles.updated_at IS 'Дата и время последнего обновления записи';

INSERT INTO roles (id, name, created_at, updated_at, created_by, updated_by)
VALUES
    (gen_random_uuid(), 'ADMIN', now(), now(), 'system', 'system'),
    (gen_random_uuid(), 'USER', now(), now(), 'system', 'system'),
    (gen_random_uuid(), 'TEACHER', now(), now(), 'system', 'system'),
    (gen_random_uuid(), 'STUDENT', now(), now(), 'system', 'system'),
    (gen_random_uuid(), 'ATTENDER', now(), now(), 'system', 'system'),
    (gen_random_uuid(), 'MODERATOR', now(), now(), 'system', 'system');

CREATE TABLE users
(
    id           UUID PRIMARY KEY,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    middle_name  VARCHAR(255),
    email        VARCHAR(255) NOT NULL UNIQUE,
    password     VARCHAR(255),
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    created_by   VARCHAR(500),
    updated_by   VARCHAR(500)
);

COMMENT ON TABLE users IS 'Таблица пользователей';
COMMENT ON COLUMN users.id IS 'Уникальный идентификатор пользователя';
COMMENT ON COLUMN users.first_name IS 'Имя пользователя';
COMMENT ON COLUMN users.last_name IS 'Фамилия пользователя';
COMMENT ON COLUMN users.middle_name IS 'Отчество пользователя';
COMMENT ON COLUMN users.email IS 'Электронная почта пользователя';
COMMENT ON COLUMN users.password IS 'Хэш пароля пользователя';
COMMENT ON COLUMN users.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN users.updated_at IS 'Дата и время последнего обновления записи';

-- Связь Many-to-Many с таблицей roles
CREATE TABLE users_roles
(
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    role_id UUID REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

COMMENT ON TABLE users_roles IS 'Связующая таблица пользователей и ролей';
COMMENT ON COLUMN users_roles.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN users_roles.role_id IS 'Идентификатор роли';

CREATE TABLE city
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    created_by   VARCHAR(500),
    updated_by   VARCHAR(500)
);

COMMENT ON TABLE city IS 'Таблица городов';
COMMENT ON COLUMN city.id IS 'Уникальный идентификатор города';
COMMENT ON COLUMN city.name IS 'Название города';
COMMENT ON COLUMN city.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN city.updated_at IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN city.created_by IS 'Кем создана запись';
COMMENT ON COLUMN city.updated_by IS 'Кем обновлена запись';

CREATE TABLE department
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    created_by   VARCHAR(500),
    updated_by   VARCHAR(500)
);

COMMENT ON TABLE department IS 'Таблица департаментов';
COMMENT ON COLUMN department.id IS 'Уникальный идентификатор департамента';
COMMENT ON COLUMN department.name IS 'Название департамента';
COMMENT ON COLUMN department.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN department.updated_at IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN department.created_by IS 'Кем создана запись';
COMMENT ON COLUMN department.updated_by IS 'Кем обновлена запись';


CREATE TABLE institute
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    address      VARCHAR(255) NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    created_by   VARCHAR(500),
    updated_by   VARCHAR(500)
);

COMMENT ON TABLE institute IS 'Таблица институтов';
COMMENT ON COLUMN institute.id IS 'Уникальный идентификатор института';
COMMENT ON COLUMN institute.name IS 'Название института';
COMMENT ON COLUMN institute.address IS 'Адрес института';
COMMENT ON COLUMN institute.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN institute.updated_at IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN institute.created_by IS 'Кем создана запись';
COMMENT ON COLUMN institute.updated_by IS 'Кем обновлена запись';


CREATE TABLE specialization
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    created_by   VARCHAR(500),
    updated_by   VARCHAR(500)
);

COMMENT ON TABLE specialization IS 'Таблица специализаций';
COMMENT ON COLUMN specialization.id IS 'Уникальный идентификатор специализации';
COMMENT ON COLUMN specialization.name IS 'Название специализации';
COMMENT ON COLUMN specialization.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN specialization.updated_at IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN specialization.created_by IS 'Кем создана запись';
COMMENT ON COLUMN specialization.updated_by IS 'Кем обновлена запись';

CREATE TABLE teacher
(
    id            UUID PRIMARY KEY,
    department_id UUID,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    created_by    VARCHAR(500),
    updated_by    VARCHAR(500),
    CONSTRAINT fk_department
        FOREIGN KEY (department_id)
            REFERENCES department(id)
            ON DELETE SET NULL
);

COMMENT ON TABLE teacher IS 'Таблица учителей';
COMMENT ON COLUMN teacher.id IS 'Уникальный идентификатор учителя';
COMMENT ON COLUMN teacher.department_id IS 'Идентификатор департамента';
COMMENT ON COLUMN teacher.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN teacher.updated_at IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN teacher.created_by IS 'Кем создана запись';
COMMENT ON COLUMN teacher.updated_by IS 'Кем обновлена запись';


CREATE TABLE student
(
    id                UUID PRIMARY KEY,
    course            VARCHAR(255) NOT NULL,
    specialization_id UUID,
    teacher_id        UUID,
    created_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    created_by        VARCHAR(500),
    updated_by        VARCHAR(500),
    CONSTRAINT fk_specialization
        FOREIGN KEY (specialization_id)
            REFERENCES specialization(id)
            ON DELETE SET NULL,
    CONSTRAINT fk_teacher
        FOREIGN KEY (teacher_id)
            REFERENCES teacher(id)
            ON DELETE SET NULL
);

COMMENT ON TABLE student IS 'Таблица студентов';
COMMENT ON COLUMN student.id IS 'Уникальный идентификатор студента';
COMMENT ON COLUMN student.course IS 'Курс студента';
COMMENT ON COLUMN student.specialization_id IS 'Идентификатор специализации студента';
COMMENT ON COLUMN student.teacher_id IS 'Идентификатор учителя студента';
COMMENT ON COLUMN student.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN student.updated_at IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN student.created_by IS 'Кем создана запись';
COMMENT ON COLUMN student.updated_by IS 'Кем обновлена запись';
