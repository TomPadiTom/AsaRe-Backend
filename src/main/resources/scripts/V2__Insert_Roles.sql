DELETE FROM roles  where roles.id IS NOT NULL;

INSERT INTO
    roles(id, created_by, created_date, deleted, description, name)
VALUES
    (1, "System", NOW(), 0, "Admin Role", "ROLE_ADMIN"),
    (2, "System", NOW(), 0, "User Role", "ROLE_USER");
