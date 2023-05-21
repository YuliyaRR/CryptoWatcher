CREATE TABLE IF NOT EXISTS app.user_crypto
(
    id bigserial,
    user_uuid uuid NOT NULL,
    crypto_id integer NOT NULL,
    basic_rate double precision NOT NULL,
    CONSTRAINT user_crypto_pkey1 PRIMARY KEY (id),
    CONSTRAINT user_crypto_user_uuid_crypto_id_key UNIQUE (user_uuid, crypto_id),
    CONSTRAINT user_crypto_crypto_id_fkey FOREIGN KEY (crypto_id)
        REFERENCES app.crypto (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_crypto_user_uuid_fkey FOREIGN KEY (user_uuid)
        REFERENCES app."user" (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)