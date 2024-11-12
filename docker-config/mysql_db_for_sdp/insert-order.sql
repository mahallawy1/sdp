-- Author Table
INSERT INTO `author` (`id`, `name`) VALUES
(1, 'Author A'),
(2, 'Author B'),
(3, 'Author C'),
(4, 'Author D'),
(5, 'Author E'),
(6, 'Author F'),
(7, 'Author G'),
(8, 'Author H'),
(9, 'Author I'),
(10, 'Author J');

-- Address Table
INSERT INTO `address` (`id`, `name`, `parent_id`) VALUES
(1, 'Egypt', NULL),
(2, 'Cairo', 1),
(3, 'Abdo Basha', 2),
(4, 'Alex', 1),
(5, 'Moharam Bek', 4),
(6, '123 Library St', 5),
(7, '456 Handsa St', 3),
(8, 'Giza', 1),
(9, 'Zamalek', 2),
(10, 'Sharm El Sheikh', 1);

-- Authors and Books Relationship Table
INSERT INTO `authors_id` (`id`, `author_id`, `book_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10);

-- Book Table
INSERT INTO `book` (`id`, `description`, `title`, `cover`, `deleted`, `publish_year`, `quantity`) VALUES
(1, 'A classic novel', 'Book One', 'cover1.jpg', 0, 1990, 10),
(2, 'A mystery novel', 'Book Two', 'cover2.jpg', 0, 2000, 5),
(3, 'A sci-fi adventure', 'Book Three', 'cover3.jpg', 0, 2010, 12),
(4, 'A fantasy tale', 'Book Four', 'cover4.jpg', 0, 2015, 8),
(5, 'A romance story', 'Book Five', 'cover5.jpg', 0, 2020, 15),
(6, 'A thriller', 'Book Six', 'cover6.jpg', 0, 2021, 6),
(7, 'An autobiography', 'Book Seven', 'cover7.jpg', 0, 2022, 20),
(8, 'A history book', 'Book Eight', 'cover8.jpg', 0, 2018, 25),
(9, 'A self-help book', 'Book Nine', 'cover9.jpg', 0, 2023, 30),
(10, 'A memoir', 'Book Ten', 'cover10.jpg', 0, 2024, 10);

-- Category Table
INSERT INTO `category` (`id`, `type`) VALUES
(1, 'Fiction'),
(2, 'Mystery'),
(3, 'Science Fiction'),
(4, 'Fantasy'),
(5, 'Romance'),
(6, 'Thriller'),
(7, 'Autobiography'),
(8, 'History'),
(9, 'Self-Help'),
(10, 'Biography');

-- Category and Book Relationship Table
INSERT INTO `categoryid` (`id`, `book_id`, `category_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10);

-- Donation Record Table
INSERT INTO `donationrecord` (`id`, `user_id`, `donate_date`, `CumilativeAmount`, `status`) VALUES
(1, 3, '2024-10-05', 100, 1),
(2, 2, '2024-11-01', 121, 1),
(3, 1, '2024-11-10', 50, 1),
(4, 3, '2024-11-05', 200, 1),
(5, 2, '2024-11-07', 300, 1),
(6, 1, '2024-10-20', 400, 0),
(7, 2, '2024-11-03', 150, 1),
(8, 3, '2024-11-12', 180, 0),
(9, 1, '2024-10-15', 220, 1),
(10, 2, '2024-10-30', 250, 0);

-- Donation Record Type Table
INSERT INTO `donationrecordtype` (`id`, `donation_record_id`, `donation_type_name`, `amount`) VALUES
(1, 1, 'Gaza', 30),
(2, 1, 'Sudan', 20),
(3, 1, 'Support Us', 48),
(4, 1, 'Charity', 2),
(5, 2, 'Support Us Donation', 50),
(6, 2, 'Charity Donation', 37),
(7, 2, 'Sudan Donation', 34),
(8, 3, 'Emergency Fund', 60),
(9, 3, 'Medical Aid', 40),
(10, 4, 'Relief Fund', 100);

-- User Table
INSERT INTO `user` (`id`, `password`, `email`, `firstname`, `address_id`, `mobile_phone`, `role_id`, `status`) VALUES
(1, 'adminpass', 'admin@example.com', 'Alice', 6, '1234567890', 1, 1),
(2, 'volpass', 'volunteer@example.com', 'Bob', 7, '0987654321', 2, 1),
(3, 'memberpass', 'member@example.com', 'Charlie', 7, '1122334455', 3, 1),
(4, 'guestpass', 'guest@example.com', 'David', 8, '2233445566', 4, 1),
(5, 'adminpass2', 'admin2@example.com', 'Eva', 9, '6677889900', 1, 1),
(6, 'volpass2', 'volunteer2@example.com', 'Frank', 10, '1122339988', 2, 1),
(7, 'memberpass2', 'member2@example.com', 'Grace', 6, '4455667788', 3, 1),
(8, 'guestpass2', 'guest2@example.com', 'Hank', 7, '5566778899', 4, 1),
(9, 'adminpass3', 'admin3@example.com', 'Ivy', 8, '9988776655', 1, 1),
(10, 'volpass3', 'volunteer3@example.com', 'Jack', 9, '1234560000', 2, 1);
