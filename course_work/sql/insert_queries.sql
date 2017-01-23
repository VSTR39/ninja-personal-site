USE self_explanatory_site;

INSERT INTO registered_users(username,email,password) VALUES ('vstr93','vstr93@abv.bg','121316036');

INSERT INTO admins(registered_users_id) VALUES((SELECT id FROM registered_users WHERE username = 'vstr93'));

INSERT INTO sections(name)VALUES('Java');
INSERT INTO sections(name)VALUES('Ninja Framework');
INSERT INTO sections(name)VALUES('Python');
INSERT INTO sections(name)VALUES('C/C++');
INSERT INTO sections(name)VALUES('C#');

