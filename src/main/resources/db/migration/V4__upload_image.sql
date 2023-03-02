CREATE TABLE image (
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    size BIGINT       NOT NULL,
    data BYTEA        NOT NULL
);