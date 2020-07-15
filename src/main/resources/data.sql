DROP TABLE IF EXISTS customers;
 
CREATE TABLE customers (
  id LONG AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  salt VARCHAR(10) NOT NULL,
  password VARCHAR(250) NOT NULL
);
 
 -- actual password is 'ABC'
INSERT INTO customers (name, email, salt, password ) VALUES
  ('Steve Roger', 'steve@email.com', 'VDsj3CqSny', 'p866tvqbMG59R4FuNEcNE7xFVzhx5hDeZK4gx+wnn6w='),
  ('Bruce Banner', 'bruce@email.com', 'wccWsGmoFO', '/JccCqAlcVQbWA997r2mgLXBNSi5WGj+LP60QslKJxI='),
  ('Stephen Strange', 'strange@email.com', 'yhF1DEKBxp', 'apTC1JlaHu0urVkn7JUBH2hwNIZLjNDqBseidj0Hfqw=');
  

DROP TABLE IF EXISTS books;
 
CREATE TABLE books (
  id LONG AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description CLOB NOT NULL,
  price_usd DECIMAL(5, 2) NOT NULL,
  cover_image_url VARCHAR(250) NOT NULL
);
 
INSERT INTO books (name, description, price_usd, cover_image_url) VALUES
('Introduction to Algorithms','Some books on algorithms are rigorous but incomplete; others cover masses of material but lack rigor. Introduction to Algorithms uniquely combines rigor and comprehensiveness. The book covers a broad range of algorithms in depth, yet makes their design and analysis accessible to all levels of readers. Each chapter is relatively self-contained and can be used as a unit of study. The algorithms are described in English and in a pseudocode designed to be readable by anyone who has done a little programming. The explanations have been kept elementary without sacrificing depth of coverage or mathematical rigor.',73.37,'https://images-na.ssl-images-amazon.com/images/I/51fgDX37U7L._SX442_BO1,204,203,200_.jpg'),
('Agile Estimating and Planning','Traditional, deterministic approaches to planning and estimating simply don''t cut it on the slippery slopes of today''s dynamic, change-driven projects. Mike Cohn''s breakthrough book gives us not only the philosophy, but also the guidelines and a proven set of tools that we need to succeed in planning, estimating, and scheduling projects with a high uncertainty factor.',31.04,'https://images-na.ssl-images-amazon.com/images/I/41R3JTE6Q7L._SX376_BO1,204,203,200_.jpg'),
('Clean Code','Even bad code can function. But if code isn''t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesn''t have to be that way.',26.99,'https://images-na.ssl-images-amazon.com/images/I/41SH-SvWPxL._SX376_BO1,204,203,200_.jpg'),
('Cracking the Coding Interview','I am not a recruiter. I am a software engineer. And as such, I know what it''s like to be asked to whip up brilliant algorithms on the spot and then write flawless code on a whiteboard. I''ve been through this as a candidate and as an interviewer.',24.55,'https://images-na.ssl-images-amazon.com/images/I/51l5XzLln%2BL._SX348_BO1,204,203,200_.jpg'),
('Working Effectively with Legacy Code','Is your code easy to change? Can you get nearly instantaneous feedback when you do change it? Do you understand it? If the answer to any of these questions is no, you have legacy code, and it is draining time and money away from your development efforts.',24.55,'https://images-na.ssl-images-amazon.com/images/I/51Y4wmtXcVL._SX376_BO1,204,203,200_.jpg'),
('Soft Skills','For most software developers, coding is the fun part. The hard bits are dealing with clients, peers, and managers, staying productive, achieving financial security, keeping yourself in shape, and finding true love. This book is here to help.',30.07,'https://images-na.ssl-images-amazon.com/images/I/51WiLueukSL._SX396_BO1,204,203,200_.jpg'),
('The Self-Taught Programmer','I am a self-taught programmer. After a year of self-study, I learned to program well enough to land a job as a software engineer II at eBay. Once I got there, I realized I was severely under-prepared. I was overwhelmed by the amount of things I needed to know but hadn''t learned yet. My journey learning to program, and my experience at my first job as a software engineer were the inspiration for this book.',19.98,'https://m.media-amazon.com/images/I/41HII-0nFEL.jpg'),
('Rapid Development','Corporate and commercial software-development teams all want solutions for one important problem—how to get their high-pressure development schedules under control. In RAPID DEVELOPMENT, author Steve McConnell addresses that concern head-on with overall strategies, specific best practices, and valuable tips that help shrink and control development schedules and keep projects moving.',9.16,'https://images-na.ssl-images-amazon.com/images/I/41zO12hU5gL._SX400_BO1,204,203,200_.jpg'),
('Domain-Driven Design','With this book in hand, object-oriented developers, system analysts, and designers will have the guidance they need to organize and focus their work, create rich and useful domain models, and leverage those models into quality, long-lasting software implementations.',44.17,'https://images-na.ssl-images-amazon.com/images/I/51OWGtzQLLL._SX376_BO1,204,203,200_.jpg'),
('Don''t Make Me Think','In this second edition, Steve Krug adds essential ammunition for those whose bosses, clients, stakeholders, and marketing managers insist on doing the wrong thing.  If you design, write, program, own, or manage Web sites, you must read this book.',10.74,'https://images-na.ssl-images-amazon.com/images/I/51Swm3TW72L._SX381_BO1,204,203,200_.jpg');

DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
  id LONG AUTO_INCREMENT  PRIMARY KEY,
  customer_id LONG NOT NULL,
  book_id LONG NOT NULL,
  quantity INT NOT NULL,
  order_date TIMESTAMP NOT NULL);
  
 CREATE INDEX customer_idx ON orders(customer_id);
 CREATE INDEX order_date_idx ON orders(order_date);