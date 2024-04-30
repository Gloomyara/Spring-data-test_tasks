DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

CREATE TABLE IF NOT EXISTS departments
(
    id           UUID DEFAULT random_uuid(),
    name         VARCHAR(64)  NOT NULL,
    description  VARCHAR(255) NOT NULL,
    CONSTRAINT   pk_departments PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS employees
(
    id            UUID DEFAULT random_uuid(),
    first_name    VARCHAR(64) NOT NULL,
    last_name     VARCHAR(64) NOT NULL,
    job_title     VARCHAR(64) NOT NULL,
    salary        NUMERIC     NOT NULL,
    department_id UUID        NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id),
    CONSTRAINT fk_u_departments FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE CASCADE
);
