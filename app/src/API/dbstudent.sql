-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 31, 2024 at 11:31 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbstudent`
--

-- --------------------------------------------------------

--
-- Table structure for table `srudents`
--

CREATE TABLE `srudents` (
  `stud_name` text NOT NULL,
  `stud_no` varchar(100) NOT NULL,
  `stud_gender` varchar(100) NOT NULL,
  `stud_state` varchar(100) NOT NULL,
  `stud_dob` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `srudents`
--

INSERT INTO `srudents` (`stud_name`, `stud_no`, `stud_gender`, `stud_state`, `stud_dob`) VALUES
('abc', 'Male', 'abc', '2001', '0000-00-00'),
('Wan', 'Male', 'B032310077', '31/12/2024', '0000-00-00'),
('Ahnaf', 'b032310077', 'Terengganu', 'Male', '0000-00-00'),
('ahnaf', 'B032310077', 'Selangor', 'Male', '2024-12-31'),
('Wan Ahnaf', 'B032310078', 'Male', 'Selangor', '2024-12-31');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
