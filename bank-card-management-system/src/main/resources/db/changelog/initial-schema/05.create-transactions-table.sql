CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    from_card_id BIGINT NOT NULL,
    to_card_id BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    created_at TIMESTAMP,
    CONSTRAINT fk_from_card FOREIGN KEY (from_card_id) REFERENCES cards(id),
    CONSTRAINT fk_to_card FOREIGN KEY (to_card_id) REFERENCES cards(id)
);

CREATE INDEX idx_transactions_from_card_id ON transactions(from_card_id);
CREATE INDEX idx_transactions_to_card_id ON transactions(to_card_id);