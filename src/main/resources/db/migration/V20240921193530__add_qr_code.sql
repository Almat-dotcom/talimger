ALTER TABLE institution
    ADD COLUMN qr_code BYTEA;

COMMENT ON COLUMN institution.qr_code IS 'Binary data representing the QR code associated with the institution';