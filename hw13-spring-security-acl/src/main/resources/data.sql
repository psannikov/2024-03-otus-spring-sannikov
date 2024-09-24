insert into
      authors (full_name)
values
      ('Author_1'),
      ('Author_2'),
      ('Author_3');

insert into
      genres (name)
values
      ('Genre_1'),
      ('Genre_2'),
      ('Genre_3');

insert into
      books (title, author_id, genre_id)
values
      ('BookTitle_1', 1, 1),
      ('BookTitle_2', 2, 2),
      ('BookTitle_3', 3, 3);

insert into
      comments (book_id, full_comment)
values
      (1, 'Book1_Comment1'),
      (1, 'Book1_Comment2'),
      (2, 'Book2_Comment1');

insert into
      users (login, password)
values
      ('Admin', 'Admin'),
      ('Reader', 'Reader'),
      ('Librarian', 'Librarian');
insert into
      roles (name, user_id)
values('ADMIN',1), ('READER',2), ('LIBRARIAN', 3), ('READER',3), ('MANAGER',3), ('READER', 1);

insert into
      acl_sid (id, principal, sid)
values
      (1, true, 'Admin'),
      (2, true, 'Reader'),
      (3, false, 'ROLE_LIBRARIAN');

INSERT INTO
      acl_class (id, class, class_id_type)
values
      (1, 'ru.otus.spring.psannikov.spring.security.acl.models.Book', 'java.lang.Long');

insert into
      acl_object_identity (
            id,
            object_id_class,
            object_id_identity,
            parent_object,
            owner_sid,
            entries_inheriting
      )
values
      (1, 1, 1, NULL, 3, false),
      (2, 1, 2, NULL, 3, false),
      (3, 1, 3, NULL, 3, false);

insert into
      acl_entry (
            id,
            acl_object_identity,
            ace_order,
            sid,
            mask,
            granting,
            audit_success,
            audit_failure
      )
values
      --Права библиотекаря
      ----на книгу 1
      (1, 1, 1, 3, 1, true, true, true),
      (2, 1, 2, 3, 2, true, true, true),
      (3, 1, 3, 3, 8, true, true, true),
      ----на книгу 2
      (4, 2, 1, 3, 1, true, true, true),
      (5, 2, 2, 3, 2, true, true, true),
      (6, 2, 3, 3, 8, true, true, true),
      ----на книгу 3
      (7, 3, 1, 3, 1, true, true, true),
      (8, 3, 2, 3, 2, true, true, true),
      (9, 3, 3, 3, 8, true, true, true),
      --Права читателя библиотеки
      (10, 1, 4, 2, 1, true, true, true),
      (11, 2, 4, 2, 1, true, true, true),
      (12, 3, 4, 2, 1, true, true, true);