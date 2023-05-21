CREATE TABLE IF NOT EXISTS app."user"
(
    uuid uuid NOT NULL,
    username text NOT NULL,
    CONSTRAINT user_crypto_pkey PRIMARY KEY (uuid),
    CONSTRAINT user_username_key UNIQUE (username)
)