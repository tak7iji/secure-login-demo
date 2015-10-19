TRUNCATE TABLE account_authentication_success_log;
TRUNCATE TABLE account_authentication_failure_log;
TRUNCATE TABLE password_history;
TRUNCATE TABLE password_reissue_failure_log;
TRUNCATE TABLE account, role, password_reissue_info;

INSERT INTO account(username, password, first_name, last_name, email) VALUES('demo', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'Taro', 'Yamada', 'taro.yamada@test.com');
INSERT INTO account(username, password, first_name, last_name, email) VALUES('admin', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'Jiro', 'Sato', 'jiro.sato@mail.jp');

INSERT INTO role (username, role) VALUES ('demo', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'ADMN');
COMMIT;