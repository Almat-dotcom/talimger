CREATE TABLE forgot_password
(
    id               UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    otp              INT NOT NULL,
    expiration_date  TIMESTAMP WITH TIME ZONE NOT NULL,
    is_verified      BOOLEAN DEFAULT FALSE,
    user_id          UUID NOT NULL,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    created_by       VARCHAR(255),
    updated_by       VARCHAR(255),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

COMMENT ON TABLE forgot_password IS 'Таблица для хранения данных сброса пароля';
COMMENT ON COLUMN forgot_password.id IS 'Уникальный идентификатор сброса пароля';
COMMENT ON COLUMN forgot_password.otp IS 'Одноразовый пароль (OTP)';
COMMENT ON COLUMN forgot_password.expiration_date IS 'Дата и время истечения срока действия OTP';
COMMENT ON COLUMN forgot_password.is_verified IS 'Флаг, указывающий, подтвержден ли OTP';
COMMENT ON COLUMN forgot_password.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN forgot_password.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN forgot_password.updated_at IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN forgot_password.created_by IS 'Кем создана запись';
COMMENT ON COLUMN forgot_password.updated_by IS 'Кем обновлена запись';

-- Обновление таблицы users
ALTER TABLE users
    ADD COLUMN forgot_password_id UUID,
ADD CONSTRAINT fk_forgot_password FOREIGN KEY (forgot_password_id) REFERENCES forgot_password(id) ON DELETE SET NULL;

COMMENT ON COLUMN users.forgot_password_id IS 'Идентификатор связанной записи сброса пароля пользователя';