CREATE TABLE cards (
    id BIGSERIAL PRIMARY KEY,
    encrypted_card_number TEXT NOT NULL,
    owner_id BIGINT NOT NULL,
    expiry_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    balance BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES users(id),
    CONSTRAINT chk_status CHECK (status IN ('ACTIVE', 'BLOCKED', 'EXPIRED'))
);

CREATE INDEX idx_cards_owner_id ON cards(owner_id);