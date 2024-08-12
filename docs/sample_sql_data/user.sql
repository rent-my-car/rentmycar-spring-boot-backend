-- Example 1: Admin user (only one admin)
INSERT INTO user (first_name, last_name, mobile, email, password, is_deleted, role_enum)
VALUES ('Alice', 'Admin', '9876543210', 'alice.admin@example.com', 'adminPass123', false, 'ADMIN');

-- Example 2: Guest user
INSERT INTO user (first_name, last_name, mobile, email, password, is_deleted, role_enum)
VALUES ('John', 'Doe', '7890123456', 'john.doe@example.com', 'johnPass123', false, 'GUEST');

-- Example 3: Guest user with special characters in email
INSERT INTO user (first_name, last_name, mobile, email, password, is_deleted, role_enum)
VALUES ('Jane', 'Smith', '8901234567', 'jane.smith@example.com', 'janePass123', false, 'GUEST');

-- Example 4: Another guest user
INSERT INTO user (first_name, last_name, mobile, email, password, is_deleted, role_enum)
VALUES ('Bob', 'Brown', '8776543210', 'bob.brown@example.com', 'bobPass123', false, 'GUEST');

-- Example 5: Guest user with a different phone number
INSERT INTO user (first_name, last_name, mobile, email, password, is_deleted, role_enum)
VALUES ('Charlie', 'Davis', '9787654321', 'charlie.davis@example.com', 'charliePass123', false, 'GUEST');

-- Example 6: Host user
INSERT INTO user (first_name, last_name, mobile, email, password, is_deleted, role_enum)
VALUES ('Eve', 'Wilson', '7892345678', 'eve.wilson@example.com', 'evePass123', false, 'HOST');

-- Example 7: Another host user
INSERT INTO user (first_name, last_name, mobile, email, password, is_deleted, role_enum)
VALUES ('Frank', 'Taylor', '8903456789', 'frank.taylor@example.com', 'frankPass1234567890!', false, 'HOST');

-- Example 8: Host user with a different phone number
INSERT INTO user (first_name, last_name, mobile, email, password, is_deleted, role_enum)
VALUES ('Grace', 'Miller', '9778901234', 'grace.miller@example.com', 'gracePass123', false, 'HOST');

-- Example 9: Another guest user
INSERT INTO user (first_name, last_name, mobile, email, password, is_deleted, role_enum)
VALUES ('Hannah', 'Moore', '9887654321', 'hannah.moore@example.com', 'hannahPass123', false, 'GUEST');

-- Example 10: Additional guest user
INSERT INTO user (first_name, last_name, mobile, email, password, is_deleted, role_enum)
VALUES ('Irene', 'King', '9712345678', 'irene.king@example.com', 'irenePass123', false, 'GUEST');
