-- Миграция для таблицы country
CREATE TABLE country (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         location_id BIGINT NOT NULL UNIQUE,
                         name VARCHAR(255) NOT NULL UNIQUE,
                         created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                         updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                         created_by VARCHAR(255),
                         updated_by VARCHAR(255)
);

COMMENT ON TABLE country IS 'Таблица для хранения стран';
COMMENT ON COLUMN country.location_id IS 'Идентификатор местоположения страны';
COMMENT ON COLUMN country.name IS 'Название страны';


-- Миграция для таблицы region
CREATE TABLE region (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        location_id BIGINT NOT NULL UNIQUE,
                        name VARCHAR(255) NOT NULL UNIQUE,
                        country_id UUID NOT NULL,
                        created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                        updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                        created_by VARCHAR(255),
                        updated_by VARCHAR(255),
                        CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES country(id) ON DELETE CASCADE
);

COMMENT ON TABLE region IS 'Таблица для хранения регионов';
COMMENT ON COLUMN region.location_id IS 'Идентификатор местоположения региона';
COMMENT ON COLUMN region.name IS 'Название региона';
COMMENT ON COLUMN region.country_id IS 'Ссылка на страну, к которой относится регион';


-- Миграция для таблицы city
ALTER TABLE city
    ADD COLUMN location_id BIGINT NOT NULL UNIQUE;

ALTER TABLE city
    ADD COLUMN region_id UUID NOT NULL,
    ADD CONSTRAINT fk_region FOREIGN KEY (region_id) REFERENCES region(id) ON DELETE CASCADE;

ALTER TABLE city
    ADD COLUMN is_center BOOLEAN DEFAULT FALSE;

COMMENT ON COLUMN city.location_id IS 'Идентификатор местоположения города';
COMMENT ON COLUMN city.region_id IS 'Ссылка на регион, к которому относится город';
COMMENT ON COLUMN city.is_center IS 'Является ли город региональным центром';

CREATE TABLE district (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          location_id BIGINT NOT NULL UNIQUE,
                          name VARCHAR(255) NOT NULL,
                          city_id UUID,
                          country_id UUID,
                          created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                          updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                          created_by VARCHAR(255),
                          updated_by VARCHAR(255),
                          CONSTRAINT fk_city FOREIGN KEY (city_id) REFERENCES city(id) ON DELETE CASCADE,
                          CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES country(id) ON DELETE CASCADE
);

COMMENT ON TABLE district IS 'Таблица для хранения районов города';
COMMENT ON COLUMN district.location_id IS 'Идентификатор местоположения района';
COMMENT ON COLUMN district.city_id IS 'Ссылка на город, к которому относится район';

CREATE TABLE district_area (
                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                               name VARCHAR(255) NOT NULL,
                               location_id BIGINT NOT NULL UNIQUE,
                               region_id UUID NOT NULL,
                               created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                               updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                               created_by VARCHAR(255),
                               updated_by VARCHAR(255),
                               CONSTRAINT fk_region FOREIGN KEY (region_id) REFERENCES region(id) ON DELETE CASCADE
);

CREATE TABLE settlement (
                            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                            name VARCHAR(255) NOT NULL,
                            location_id BIGINT NOT NULL UNIQUE,
                            region_id UUID,
                            created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                            updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                            created_by VARCHAR(255),
                            updated_by VARCHAR(255),
                            CONSTRAINT fk_region FOREIGN KEY (region_id) REFERENCES region(id) ON DELETE CASCADE
);


-- Миграция для таблицы point
CREATE TABLE point (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       latitude DOUBLE PRECISION NOT NULL,
                       longitude DOUBLE PRECISION NOT NULL,
                       created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                       updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                       created_by VARCHAR(255),
                       updated_by VARCHAR(255)
);

COMMENT ON TABLE point IS 'Таблица для хранения географических координат';
COMMENT ON COLUMN point.latitude IS 'Широта';
COMMENT ON COLUMN point.longitude IS 'Долгота';


-- Миграция для таблицы address
CREATE TABLE address (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         building_id VARCHAR(255) UNIQUE,
                         address_name VARCHAR(255),
                         address_comment VARCHAR(255),
                         country_id UUID NOT NULL,
                         region_id UUID,
                         city_id UUID,
                         district_id UUID,
                         district_area_id UUID,
                         settlement_id UUID,
                         street VARCHAR(255),
                         building_number VARCHAR(255),
                         postal_code VARCHAR(10),
                         created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                         updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                         created_by VARCHAR(255),
                         updated_by VARCHAR(255),
                         CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES country(id),
                         CONSTRAINT fk_region FOREIGN KEY (region_id) REFERENCES region(id),
                         CONSTRAINT fk_city FOREIGN KEY (city_id) REFERENCES city(id),
                         CONSTRAINT fk_district FOREIGN KEY (district_id) REFERENCES district(id),
                         CONSTRAINT fk_district_area FOREIGN KEY (district_area_id) REFERENCES district_area(id),
                         CONSTRAINT fk_settlement FOREIGN KEY (settlement_id) REFERENCES settlement(id)
);

COMMENT ON TABLE address IS 'Таблица для хранения адресов';
COMMENT ON COLUMN address.building_id IS 'Идентификатор здания';
COMMENT ON COLUMN address.address_name IS 'Полное название адреса';
COMMENT ON COLUMN address.address_comment IS 'Комментарий к адресу';


-- Миграция для таблицы rubric
CREATE TABLE rubric (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        location_id VARCHAR(255) NOT NULL UNIQUE,
                        alias VARCHAR(255) NOT NULL UNIQUE,
                        name VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                        updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                        created_by VARCHAR(255),
                        updated_by VARCHAR(255)
);

COMMENT ON TABLE rubric IS 'Таблица для хранения рубрик';
COMMENT ON COLUMN rubric.alias IS 'Короткое название рубрики';
COMMENT ON COLUMN rubric.name IS 'Полное название рубрики';


-- Миграция для таблицы educational_institution
CREATE TABLE educational_institution (
                                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                         name VARCHAR(1000) NOT NULL,
                                         legal_name VARCHAR(1000),
                                         short_name VARCHAR(1000),
                                         address_id UUID,
                                         city_id UUID,
                                         region_id UUID,
                                         point_id UUID,
                                         created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                                         updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
                                         created_by VARCHAR(255),
                                         updated_by VARCHAR(255),
                                         CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES address(id),
                                         CONSTRAINT fk_city FOREIGN KEY (city_id) REFERENCES city(id),
                                         CONSTRAINT fk_region FOREIGN KEY (region_id) REFERENCES region(id),
                                         CONSTRAINT fk_point FOREIGN KEY (point_id) REFERENCES point(id) ON DELETE SET NULL
);

COMMENT ON TABLE educational_institution IS 'Таблица для хранения образовательных учреждений';
COMMENT ON COLUMN educational_institution.name IS 'Название образовательного учреждения';
COMMENT ON COLUMN educational_institution.legal_name IS 'Официальное название образовательного учреждения';
COMMENT ON COLUMN educational_institution.short_name IS 'Сокращённое название образовательного учреждения';

    -- Migration for the institution_rubric join table
CREATE TABLE institution_rubric (
                                    institution_id UUID NOT NULL,
                                    rubric_id UUID NOT NULL,
                                    PRIMARY KEY (institution_id, rubric_id),
                                    CONSTRAINT fk_institution FOREIGN KEY (institution_id) REFERENCES educational_institution(id) ON DELETE CASCADE,
                                    CONSTRAINT fk_rubric FOREIGN KEY (rubric_id) REFERENCES rubric(id) ON DELETE CASCADE
);

COMMENT ON TABLE institution_rubric IS 'Join table for the many-to-many relationship between EducationalInstitution and Rubric';


-- Миграция для таблицы school
CREATE TABLE school (
                        id UUID PRIMARY KEY,
                        CONSTRAINT fk_educational_institution FOREIGN KEY (id) REFERENCES educational_institution (id) ON DELETE CASCADE
);

-- Миграция для таблицы kindergarten
CREATE TABLE kindergarten (
                              id UUID PRIMARY KEY,
                              CONSTRAINT fk_educational_institution FOREIGN KEY (id) REFERENCES educational_institution(id) ON DELETE CASCADE
);

COMMENT ON TABLE kindergarten IS 'Таблица для хранения детских садов';
