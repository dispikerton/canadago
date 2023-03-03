CREATE TABLE article (
    id         SERIAL PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    created_at TIMESTAMP    NOT NULL
);

CREATE TABLE image (
    id         UUID PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    type       VARCHAR(255) NOT NULL,
    size       BIGINT       NOT NULL,
    data       BYTEA        NOT NULL,
    article_id BIGINT,
    FOREIGN KEY (article_id) REFERENCES article(id)
);