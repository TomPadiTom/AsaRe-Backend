SELECT GROUP_CONCAT('DROP TABLE IF EXISTS ', table_name, ';')
FROM information_schema.tables
WHERE table_schema = 'u101457745_telemedicine_d';

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS account_details;
DROP TABLE IF EXISTS billing_address;
DROP TABLE IF EXISTS shipping_address;
DROP TABLE IF EXISTS category;
SET FOREIGN_KEY_CHECKS = 1;
