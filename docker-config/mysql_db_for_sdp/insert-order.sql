INSERT INTO `author` (`id`, `name`) VALUES
(1, 'Author A'),
(2, 'Author B');

INSERT INTO `address` (`id`, `name`, `parent_id`) VALUES
(1, 'egypt', NULL),
(2, 'cairo', 1),
(3, 'abdo basha', 2),
(4, 'alex', 1),
(5, 'moharam bek', 4),
(6, '123 Library St', 5),
(7, '456 handsa St', 3);


INSERT INTO `authors_id` (`id`, `author_id`, `book_id`) VALUES
(1, 1, 1),
(2, 2, 2);



INSERT INTO `book` (`id`, `description`, `title`, `cover`, `deleted`, `publish_year`, `quantity`) VALUES
(1, 'A classic novel', 'Book One', 'cover1.jpg', 0, 1990, 10),
(2, 'A mystery novel', 'Book Two', 'cover2.jpg', 0, 2000, 5);


INSERT INTO `category` (`id`, `type`) VALUES
(1, 'Fiction'),
(2, 'Mystery');


INSERT INTO `categoryid` (`id`, `book_id`, `category_id`) VALUES
(1, 1, 1),
(2, 2, 2);


INSERT INTO `donationrecord` (`id`, `user_id`, `donate_date`, `CumilativeAmount`, `status`) VALUES
(1, 3, '2024-10-05', 100, 1),
(2, 2, '2024-11-01', 121, 1);


INSERT INTO `donationrecordtype` (`id`, `donation_record_id`, `donation_type_name`, `amount`) VALUES
(1, 1, 'gaza', 30),
(2, 1, 'sudan', 20),
(3, 1, 'supportus', 48),
(4, 1, 'charity', 2),
(5, 2, 'Support Us Donation', 50),
(6, 2, 'Charity Donation', 37),
(7, 2, 'Sudan Donation', 34);


INSERT INTO `user` (`id`, `password`, `email`, `firstname`, `address_id`, `mobile_phone`, `role_id`, `status`) VALUES
(1, 'adminpass', 'admin@example.com', 'Alice', 6, '1234567890', 1, 1),
(2, 'volpass', 'volunteer@example.com', 'Bob', 7, '0987654321', 2, 1),
(3, 'memberpass', 'member@example.com', 'Charlie', 7, '1122334455', 3, 1);


