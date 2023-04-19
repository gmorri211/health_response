-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2023 at 01:06 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `health_response`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddAmbulanceRequest` (IN `inUserId` INT, IN `inLocation` TEXT, IN `inInjuryDesc` TEXT, IN `inActiveStatus` INT, IN `inAmbulance` INT, IN `inHospital` INT)   BEGIN
 INSERT INTO AMBULANCE_REQUESTS (USERID, LOCATION, INJURYDESC, ACTIVESTATUS, AMBULANCE, HOSPITAL)
        VALUES (inUserId, inLocation, inInjuryDesc, inActiveStatus, inAmbulance, inHospital);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `AddERPatient` (IN `inName` TEXT, IN `inInjury` TEXT)   BEGIN
 INSERT INTO ER_REGISTER (NAME, INJURY)
        VALUES (inName, inInjury);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `AddStaff` (IN `inUsername` TEXT, IN `inPassword` TEXT, IN `inStaffType` INT)   BEGIN
 INSERT INTO STAFF (USERNAME, PASSWORD, STAFFTYPE)
        VALUES (inUsername, inPassword, inStaffType);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `AddUser` (IN `inFirstName` TEXT, IN `inSurname` TEXT, IN `inUsername` TEXT, IN `inPassword` TEXT)   BEGIN
 INSERT INTO USERS (FIRSTNAME, SURNAME, USERNAME, PASSWORD)
        VALUES (inFirstName, inSurname, inUsername, inPassword);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAmbulanceRequests` ()   BEGIN
  Select REQUESTID, USERID, LOCATION, INJURYDESC, ACTIVESTATUS, AMBULANCE, HOSPITAL
From AMBULANCE_REQUESTS Order By REQUESTID;    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetERPatients` ()   BEGIN
  Select REGISTERID, NAME, INJURY
From ER_REGISTER Order By REGISTERID;    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetUsers` ()   BEGIN
  Select USERID, FIRSTNAME, SURNAME, USERNAME, PASSWORD From USERS Order By USERID;    
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `ambulance_request`
--

CREATE TABLE `ambulance_request` (
  `id` int(11) NOT NULL,
  `injury_desc` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `active_status` int(11) DEFAULT NULL,
  `ambulance` varchar(255) DEFAULT NULL,
  `hospital` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ambulance_request`
--

INSERT INTO `ambulance_request` (`id`, `injury_desc`, `location`, `name`, `active_status`, `ambulance`, `hospital`) VALUES
(1, 'Broken Arm', 'Argyll Street', 'Mary Stevens', 0, NULL, NULL),
(2, 'Head Wound', 'Buchanan Street', 'John Richards', 0, NULL, NULL),
(52, 'Arm Wound', 'Renfrew Street', 'Joan Michaels', 1, 'Ambulance 2', 'Queen Elizabeth'),
(152, 'Seizure', 'St Vincent Street', 'Amy Rogers', 1, 'Ambulance 2', 'Royal Infirmary');

-- --------------------------------------------------------

--
-- Table structure for table `ambulance_request_seq`
--

CREATE TABLE `ambulance_request_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ambulance_request_seq`
--

INSERT INTO `ambulance_request_seq` (`next_val`) VALUES
(501);

-- --------------------------------------------------------

--
-- Table structure for table `erpatient`
--

CREATE TABLE `erpatient` (
  `name` varchar(255) NOT NULL,
  `injury_desc` varchar(255) DEFAULT NULL,
  `urgency_level` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `erpatient`
--

INSERT INTO `erpatient` (`name`, `injury_desc`, `urgency_level`, `id`) VALUES
('Bill Stevenson', 'Heart Attack', 'High', 1),
('Jenny Thompson', 'Arm Wound', 'Low', 2),
('Susan Davidson', 'Head Wound', 'Medium', 202);

-- --------------------------------------------------------

--
-- Table structure for table `erpatient_seq`
--

CREATE TABLE `erpatient_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `erpatient_seq`
--

INSERT INTO `erpatient_seq` (`next_val`) VALUES
(451);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `staff_type` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `password`, `staff_type`, `username`) VALUES
(1, 'test', 0, 'testrec'),
(2, 'test', 1, 'teststaff');

-- --------------------------------------------------------

--
-- Table structure for table `staff_seq`
--

CREATE TABLE `staff_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `staff_seq`
--

INSERT INTO `staff_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `first_name`, `password`, `surname`, `username`) VALUES
(1, 'Test', 'testpass', 'Man', 'testman'),
(2, 'Sarah', 'l24mdr4', 'Johnson', 'sj123'),
(52, 'Steve', 'abc123', 'Stevenson', 'steve1'),
(102, 'Mike', 'abcdefg', 'Richards', 'mrnhs3'),
(152, 'Molly', '123pass', 'Peters', 'mp221'),
(202, 'Danny', 'gk4dm5b3j2j', 'White', 'dwhite22'),
(203, 'Gary', '12345abcde', 'Morris', 'gmo123'),
(252, 'Test', 'TestScenario', 'Case', 'TestCase');

-- --------------------------------------------------------

--
-- Table structure for table `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(351);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ambulance_request`
--
ALTER TABLE `ambulance_request`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `erpatient`
--
ALTER TABLE `erpatient`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
