CREATE SEQUENCE time_record_seq_1;

CREATE TABLE time_record
(
    id                 BIGINT DEFAULT nextval('time_record_seq_1'),
    object_id          VARCHAR(255) NOT NULL,
    minutes            INTEGER      NOT NULL,
    record_date        TIMESTAMP    NOT NULL,
    description        VARCHAR(255),
    employee_id        BIGINT       NOT NULL,
    creation_timestamp TIMESTAMP    NOT NULL,
    update_timestamp   TIMESTAMP    NOT NULL,
    version            BIGINT       NOT NULL,
    CONSTRAINT time_record_pk PRIMARY KEY (id),
    CONSTRAINT time_record_uk_1 UNIQUE (object_id),
    CONSTRAINT time_record_fk_1 FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE CASCADE
)