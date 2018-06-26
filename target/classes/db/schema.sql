CREATE TABLE tb_category (
id SERIAL primary key,
name varchar
);

CREATE TABLE tb_book(
  id serial primary key ,
  title varchar,
  author varchar,
  publisher varchar,
  thumbnail varchar,
  cate_id INT,
  CONSTRAINT fk_cat FOREIGN KEY (cate_id) REFERENCES tb_category
);