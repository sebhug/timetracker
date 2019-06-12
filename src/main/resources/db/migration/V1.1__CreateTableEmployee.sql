CREATE SEQUENCE employee_seq_1;

CREATE TABLE employee
(
    id                 BIGINT DEFAULT nextval('employee_seq_1'),
    object_id          VARCHAR(255) NOT NULL,
    name               VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP    NOT NULL,
    update_timestamp   TIMESTAMP    NOT NULL,
    version            BIGINT       NOT NULL,
    CONSTRAINT employee_pk PRIMARY KEY (id),
    CONSTRAINT employee_uk_1 UNIQUE (object_id)
)