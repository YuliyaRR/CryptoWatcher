CREATE TABLE IF NOT EXISTS app.crypto
(
    id integer NOT NULL,
    symbol text NOT NULL,
    current_rate double precision,
    CONSTRAINT cryptocurrency_pkey PRIMARY KEY (id),
    CONSTRAINT crypto_symbol_key UNIQUE (symbol)
)