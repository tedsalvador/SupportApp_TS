CREATE TABLE support_request (
    id_support BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_request TIMESTAMP NOT NULL,
    id_user BIGINT NOT NULL,
    description VARCHAR(1000) NOT NULL,
    support_type VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    date_attention DATE,
    FOREIGN KEY (id_user) REFERENCES user(id_user)
);
