insert into authors(full_name)
values ('Author_1'),
       ('Author_2'),
       ('Author_3');

insert into genres(name)
values ('Genre_1'),
       ('Genre_2'),
       ('Genre_3');

insert into books(title, author_id, genre_id)
values ('BookTitle_1', 1, 1),
       ('BookTitle_2', 2, 2),
       ('BookTitle_3', 3, 3);

insert into comments(book_id, full_comment)
values (1, 'Book1_Comment1'),
       (1, 'Book1_Comment2'),
       (2, 'Book2_Comment1');

insert into users(login, password)
values ('User1', 'Pass1'),
       ('User2', 'Pass2')