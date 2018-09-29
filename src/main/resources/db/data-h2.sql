-- data


INSERT INTO user (id, tenant_id, username, mobile, encrypted_password) VALUES
(1, 1, 'username1', '13825279842', 'passwod1'),
(2, 1, 'username1', '13825279842', 'passwod1'),
(3, 1, 'username1', '13825279842', 'passwod1'),
(4, 1, 'username1', '13825279842', 'passwod1'),
(5, 1, 'username1', '13825279842', 'passwod1'),
(6, 1, 'username1', '13825279842', 'passwod1'),
(7, 1, 'username1', '13825279842', 'passwod1');


INSERT INTO device (id, tenant_id, user_id, nickname, product_key, device_key, device_secret) VALUES

(1, 1, 0, 'nickname1', 'product_key1', 'device_key1', 'device_secret1'),
(2, 1, 0, 'nickname2', 'product_key2', 'device_key2', 'device_secret2'),
(3, 1, 0, 'nickname3', 'product_key3', 'device_key3', 'device_secret3'),
(4, 1, 0, 'nickname4', 'product_key4', 'device_key4', 'device_secret4'),
(5, 1, 0, 'nickname5', 'product_key5', 'device_key5', 'device_secret5'),
(6, 1, 0, 'nickname6', 'product_key6', 'device_key6', 'device_secret6');

INSERT INTO follow (id, tenant_id, user_id, device_id, nickname) VALUES
(1, 1, 1, 1, 'follow1'),
(2, 1, 1, 2, 'follow2'),
(3, 1, 1, 3, 'follow3');
