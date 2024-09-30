DROP TABLE IF EXISTS comments;

DROP TABLE IF EXISTS books;

DROP TABLE IF EXISTS authors;

DROP TABLE IF EXISTS genres;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS roles;

DROP TABLE IF EXISTS acl_entry;

DROP TABLE IF EXISTS acl_object_identity;

DROP TABLE IF EXISTS acl_class;

DROP TABLE IF EXISTS acl_sid;

CREATE TABLE
      authors (
            id bigserial,
            full_name varchar(255),
            PRIMARY KEY (id)
      );

CREATE TABLE
      genres (id bigserial, name varchar(255), PRIMARY KEY (id));

CREATE TABLE
      books (
            id bigserial,
            title varchar(255),
            author_id bigint REFERENCES authors (id) ON DELETE CASCADE,
            genre_id bigint REFERENCES genres (id) ON DELETE CASCADE,
            PRIMARY KEY (id)
      );

CREATE TABLE
      comments (
            id bigserial,
            book_id bigint REFERENCES books (id) ON DELETE CASCADE,
            full_comment varchar(255),
            PRIMARY KEY (id)
      );

CREATE TABLE
      users (
            id bigserial,
            login varchar(255) NOT NULL,
            password varchar(255) NOT NULL,
            PRIMARY KEY (id)
      );

CREATE TABLE
      roles (
            id bigserial,
            name varchar(255) NOT NULL,
            user_id bigint REFERENCES users (id) ON DELETE CASCADE,
            PRIMARY KEY (id)
      );

CREATE TABLE
      acl_sid (
            id bigserial,
            principal boolean NOT NULL,
            sid varchar(255) NOT NULL,
            CONSTRAINT unique_key_1 UNIQUE (sid, principal),
            PRIMARY KEY (id)
      );

CREATE TABLE
      acl_class (
            id bigserial,
            class varchar(255) NOT NULL,
            class_id_type varchar(255) NOT NULL,
            CONSTRAINT unique_key_2 UNIQUE (class),
            PRIMARY KEY (id)
      );

CREATE TABLE
      acl_object_identity (
            id bigserial,
            object_id_class bigint NOT NULL,
            object_id_identity varchar(255) NOT NULL,
            parent_object bigint,
            owner_sid bigint,
            entries_inheriting boolean NOT NULL,
            CONSTRAINT unique_key_3 UNIQUE (object_id_class, object_id_identity),
            CONSTRAINT foreign_key_1 FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
            CONSTRAINT foreign_key_2 FOREIGN KEY (owner_sid) REFERENCES acl_sid (id),
            PRIMARY KEY (id)
      );

CREATE TABLE
      acl_entry (
            id bigserial,
            acl_object_identity bigint NOT NULL,
            ace_order int NOT NULL,
            sid bigint NOT NULL,
            mask integer NOT NULL,
            granting boolean NOT NULL,
            audit_success boolean NOT NULL,
            audit_failure boolean NOT NULL,
            CONSTRAINT unique_key_4 UNIQUE (acl_object_identity, ace_order),
            CONSTRAINT foreign_key_3 FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
            CONSTRAINT foreign_key_4 FOREIGN KEY (sid) REFERENCES acl_sid (id),
            PRIMARY KEY (id)
      );