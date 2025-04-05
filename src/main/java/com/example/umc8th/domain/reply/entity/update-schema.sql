create TABLE reply
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    delete_at  BIT(1)                NOT NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    deleted_at datetime              NULL,
    content    VARCHAR(255)          NULL,
    article_id BIGINT                NULL,
    CONSTRAINT pk_reply PRIMARY KEY (id)
);

alter table reply
    add CONSTRAINT FK_REPLY_ON_ARTICLE FOREIGN KEY (article_id) REFERENCES article (id);