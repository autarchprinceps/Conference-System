DROP TABLE IF EXISTS conference_system.conference_rating;
DROP TABLE IF EXISTS conference_system.conference_user_role;
DROP TABLE IF EXISTS conference_system.publication;
DROP TABLE IF EXISTS conference_system.publication_review;
DROP TABLE IF EXISTS conference_system.end_user;
DROP TABLE IF EXISTS conference_system.conference;
DROP TYPE IF EXISTS	conference_system.role;

CREATE TYPE conference_system.role AS ENUM (
	'VIEWER',
	'REVIEWER',
	'ORGANIZER',
        'AUTHOR'
);

CREATE TABLE conference_system.end_user (
	id			SERIAL		NOT NULL PRIMARY KEY,
	name			VARCHAR(30)	NOT NULL,
        password                VARCHAR(30)     NOT NULL
);

CREATE TABLE conference_system.conference (
	id			SERIAL		NOT NULL PRIMARY KEY,
	name			VARCHAR(30)	NOT NULL,
	participant_limit	INT		NOT NULL,
	date 			DATE		NOT NULL
);

CREATE TABLE conference_system.publication (
	author_id		INT		NOT NULL REFERENCES conference_system.end_user(id),
	conference_id		INT		NOT NULL REFERENCES conference_system.conference(id),
	title			VARCHAR(30)	NOT NULL,
	text			TEXT,
	PRIMARY KEY(author_id,conference_id),
	UNIQUE(title)
);

CREATE TABLE conference_system.publication_review (
	author_id		INT		NOT NULL REFERENCES conference_system.end_user(id),
	conference_id		INT		NOT NULL REFERENCES conference_system.conference(id),
	text			TEXT,
	PRIMARY KEY(author_id,conference_id)
);

CREATE TABLE conference_system.conference_rating (
	user_id			INT		NOT NULL REFERENCES conference_system.end_user(id),
	conference_id		INT		NOT NULL REFERENCES conference_system.conference(id),
	rating			INT		NOT NULL,
	PRIMARY KEY(user_id,conference_id)
);

CREATE TABLE conference_system.conference_user_role (
	user_id			INT			NOT NULL REFERENCES conference_system.end_user(id),
	conference_id		INT			NOT NULL REFERENCES conference_system.conference(id),
	user_role		conference_system.role	NOT NULL,
	PRIMARY KEY(user_id,conference_id)
);