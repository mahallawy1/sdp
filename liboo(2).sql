-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Nov 02, 2024 at 05:47 PM
-- Server version: 5.7.24
-- PHP Version: 8.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `liboo`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `name`, `parent_id`) VALUES
(1, 'egypt', NULL),
(2, 'cairo', 1),
(3, 'abdo basha', 2),
(4, 'alex', 1),
(5, 'moharam bek', 4),
(6, '123 Library St', 5),
(7, '456 handsa St', 3);

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id`, `name`) VALUES
(1, 'Author A'),
(2, 'Author B');

-- --------------------------------------------------------

--
-- Table structure for table `authors_id`
--

CREATE TABLE `authors_id` (
  `id` int(11) NOT NULL,
  `author_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `authors_id`
--

INSERT INTO `authors_id` (`id`, `author_id`, `book_id`) VALUES
(1, 1, 1),
(2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `publish_year` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `description`, `title`, `cover`, `deleted`, `publish_year`, `quantity`) VALUES
(1, 'A classic novel', 'Book One', 'cover1.jpg', 0, 1990, 10),
(2, 'A mystery novel', 'Book Two', 'cover2.jpg', 0, 2000, 5);

-- --------------------------------------------------------

--
-- Table structure for table `borrow`
--

CREATE TABLE `borrow` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `days` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `borrow_details`
--

CREATE TABLE `borrow_details` (
  `id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `borrow_id` int(11) DEFAULT NULL,
  `returned` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `type`) VALUES
(1, 'Fiction'),
(2, 'Mystery');

-- --------------------------------------------------------

--
-- Table structure for table `categoryid`
--

CREATE TABLE `categoryid` (
  `id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categoryid`
--

INSERT INTO `categoryid` (`id`, `book_id`, `category_id`) VALUES
(1, 1, 1),
(2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `donationrecord`
--

CREATE TABLE `donationrecord` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `donate_date` date DEFAULT NULL,
  `CumilativeAmount` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `donationrecord`
--

INSERT INTO `donationrecord` (`id`, `user_id`, `donate_date`, `CumilativeAmount`, `status`) VALUES
(1, 3, '2024-10-05', 100, 1),
(2, 2, '2024-11-01', 121, 1);

-- --------------------------------------------------------

--
-- Table structure for table `donationrecordtype`
--

CREATE TABLE `donationrecordtype` (
  `id` int(11) NOT NULL,
  `donation_record_id` int(11) DEFAULT NULL,
  `donation_type_name` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `donationrecordtype`
--

INSERT INTO `donationrecordtype` (`id`, `donation_record_id`, `donation_type_name`, `amount`) VALUES
(1, 1, 'gaza', 30),
(2, 1, 'sudan', 20),
(3, 1, 'supportus', 48),
(4, 1, 'charity', 2),
(5, 2, 'Support Us Donation', 50),
(6, 2, 'Charity Donation', 37),
(7, 2, 'Sudan Donation', 34);

-- --------------------------------------------------------

--
-- Table structure for table `donationrecord_payment`
--

CREATE TABLE `donationrecord_payment` (
  `id` int(11) NOT NULL,
  `donation_id` int(11) DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `event_type_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `event_date` date DEFAULT NULL,
  `time_from` time DEFAULT NULL,
  `time_to` time DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `event_type`
--

CREATE TABLE `event_type` (
  `id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `payment_method_id` int(11) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `paymentmethod`
--

CREATE TABLE `paymentmethod` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `required_skills_id`
--

CREATE TABLE `required_skills_id` (
  `id` int(11) NOT NULL,
  `event_id` int(11) DEFAULT NULL,
  `skill_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `type`) VALUES
(1, 'Admin'),
(3, 'Member'),
(2, 'Volunteer');

-- --------------------------------------------------------

--
-- Table structure for table `skill`
--

CREATE TABLE `skill` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `skill`
--

INSERT INTO `skill` (`id`, `name`) VALUES
(1, 'Event Management'),
(2, 'Customer Service');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `password`, `email`, `firstname`, `address_id`, `mobile_phone`, `role_id`, `status`) VALUES
(1, 'adminpass', 'admin@example.com', 'Alice', 6, '1234567890', 1, 1),
(2, 'volpass', 'volunteer@example.com', 'Bob', 7, '0987654321', 2, 1),
(3, 'memberpass', 'member@example.com', 'Charlie', 7, '1122334455', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `volunteering`
--

CREATE TABLE `volunteering` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `volunteering_details`
--

CREATE TABLE `volunteering_details` (
  `id` int(11) NOT NULL,
  `event_id` int(11) DEFAULT NULL,
  `volunteering_id` int(11) DEFAULT NULL,
  `hours` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT 'Status as enum'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `parent_id` (`parent_id`);

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `authors_id`
--
ALTER TABLE `authors_id`
  ADD PRIMARY KEY (`id`),
  ADD KEY `author_id` (`author_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `borrow`
--
ALTER TABLE `borrow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `borrow_details`
--
ALTER TABLE `borrow_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `borrow_id` (`borrow_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `type` (`type`);

--
-- Indexes for table `categoryid`
--
ALTER TABLE `categoryid`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `donationrecord`
--
ALTER TABLE `donationrecord`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `donationrecordtype`
--
ALTER TABLE `donationrecordtype`
  ADD PRIMARY KEY (`id`),
  ADD KEY `DonationRecordType_ibfk_1` (`donation_record_id`);

--
-- Indexes for table `donationrecord_payment`
--
ALTER TABLE `donationrecord_payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `payment_id` (`payment_id`),
  ADD KEY `donationrecord_payment_ibfk_1` (`donation_id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `event_type_id` (`event_type_id`);

--
-- Indexes for table `event_type`
--
ALTER TABLE `event_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `type` (`type`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `payment_method_id` (`payment_method_id`);

--
-- Indexes for table `paymentmethod`
--
ALTER TABLE `paymentmethod`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `required_skills_id`
--
ALTER TABLE `required_skills_id`
  ADD PRIMARY KEY (`id`),
  ADD KEY `event_id` (`event_id`),
  ADD KEY `skill_id` (`skill_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `type` (`type`);

--
-- Indexes for table `skill`
--
ALTER TABLE `skill`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `mobile_phone` (`mobile_phone`),
  ADD KEY `address_id` (`address_id`),
  ADD KEY `role_id` (`role_id`);

--
-- Indexes for table `volunteering`
--
ALTER TABLE `volunteering`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `volunteering_details`
--
ALTER TABLE `volunteering_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `event_id` (`event_id`),
  ADD KEY `volunteering_id` (`volunteering_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `donationrecord`
--
ALTER TABLE `donationrecord`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `donationrecordtype`
--
ALTER TABLE `donationrecordtype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `address` (`id`);

--
-- Constraints for table `authors_id`
--
ALTER TABLE `authors_id`
  ADD CONSTRAINT `authors_id_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  ADD CONSTRAINT `authors_id_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`);

--
-- Constraints for table `borrow`
--
ALTER TABLE `borrow`
  ADD CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `borrow_details`
--
ALTER TABLE `borrow_details`
  ADD CONSTRAINT `borrow_details_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `borrow_details_ibfk_2` FOREIGN KEY (`borrow_id`) REFERENCES `borrow` (`id`);

--
-- Constraints for table `categoryid`
--
ALTER TABLE `categoryid`
  ADD CONSTRAINT `categoryid_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `categoryid_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `donationrecord`
--
ALTER TABLE `donationrecord`
  ADD CONSTRAINT `donationrecord_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `donationrecordtype`
--
ALTER TABLE `donationrecordtype`
  ADD CONSTRAINT `DonationRecordType_ibfk_1` FOREIGN KEY (`donation_record_id`) REFERENCES `donationrecord` (`id`);

--
-- Constraints for table `donationrecord_payment`
--
ALTER TABLE `donationrecord_payment`
  ADD CONSTRAINT `donationrecord_payment_ibfk_1` FOREIGN KEY (`donation_id`) REFERENCES `donationrecord` (`id`),
  ADD CONSTRAINT `donationrecord_payment_ibfk_2` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`);

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`event_type_id`) REFERENCES `event_type` (`id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`payment_method_id`) REFERENCES `paymentmethod` (`id`);

--
-- Constraints for table `required_skills_id`
--
ALTER TABLE `required_skills_id`
  ADD CONSTRAINT `required_skills_id_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `required_skills_id_ibfk_2` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Constraints for table `volunteering`
--
ALTER TABLE `volunteering`
  ADD CONSTRAINT `volunteering_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `volunteering_details`
--
ALTER TABLE `volunteering_details`
  ADD CONSTRAINT `volunteering_details_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `volunteering_details_ibfk_2` FOREIGN KEY (`volunteering_id`) REFERENCES `volunteering` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
