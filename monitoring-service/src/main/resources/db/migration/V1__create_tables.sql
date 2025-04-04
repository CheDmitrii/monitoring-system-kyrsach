CREATE TABLE sensor (
    id                  UUID            NOT NULL UNIQUE,
    name                VARCHAR(255)    NOT NULL,
    type                VARCHAR(255)    NOT NULL,
    description         TEXT,
    PRIMARY KEY(id)
);


CREATE TABLE journal (
    sensor_id           UUID            NOT NULL references sensor(id) ON UPDATE CASCADE ON DELETE CASCADE,
    value               REAL            NOT NULL,
    time                TIMESTAMPTZ     NOT NULL
);


CREATE TABLE reference_value (
    id                  UUID            NOT NULL UNIQUE,
    sensor_id           UUID            NOT NULL UNIQUE references sensor(id) ON UPDATE CASCADE ON DELETE CASCADE,
    name                TEXT            NOT NULL,
    value               REAL            NOT NULL,
    type                VARCHAR(255),
    PRIMARY KEY(id)
);


CREATE TABLE history_reference (
    id                  UUID            NOT NULL references reference_value(id) ON UPDATE CASCADE ON DELETE CASCADE,
    old_value           REAL,
    new_value           REAL,
    time           TIMESTAMPTZ
);
