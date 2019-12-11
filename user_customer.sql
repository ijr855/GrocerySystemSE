-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2019 at 03:15 AM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `user/customer`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `userId` int(12) NOT NULL,
  `itemId` int(10) NOT NULL,
  `itemQuantity` int(3) NOT NULL,
  `price` double(3,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Name` varchar(20) NOT NULL,
  `Address` varchar(30) NOT NULL,
  `Payment Info` varchar(20) NOT NULL,
  `CRN` int(3) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `ID` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Name`, `Address`, `Payment Info`, `CRN`, `UserName`, `Password`, `ID`) VALUES
('John Smith', '123 Park Place', '12980148010', 123, 'Howlie', 'Howler', 7823);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `Name` text NOT NULL,
  `Item_ID` int(10) NOT NULL,
  `Category` text NOT NULL,
  `Quantity` int(5) NOT NULL,
  `Price` double(3,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`Name`, `Item_ID`, `Category`, `Quantity`, `Price`) VALUES
('Apple', 1111, 'Fruit', 10, 0.50),
('Banana', 1112, 'Fruit', 1000, 1.00),
('Strawberry', 1113, 'Fruit', 2000, 0.10);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `CustID` int(12) NOT NULL,
  `OrderID` int(10) NOT NULL,
  `Item_ID` int(10) NOT NULL,
  `Quantity` int(5) NOT NULL,
  `Price` double(3,2) NOT NULL,
  `deliveryTime` varchar(10) NOT NULL,
  `deliveryDate` date NOT NULL,
  `Status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`CustID`, `OrderID`, `Item_ID`, `Quantity`, `Price`, `deliveryTime`, `deliveryDate`, `Status`) VALUES
(-1, -1, -1, -1, -1.00, '-1', '2000-01-01', '');

-- --------------------------------------------------------

--
-- Table structure for table `ordertracking`
--

CREATE TABLE `ordertracking` (
  `total` double(5,2) NOT NULL,
  `customerID` int(20) NOT NULL,
  `orderID` int(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `deliverSpeed` varchar(30) NOT NULL,
  `deliveryTime` varchar(20) NOT NULL,
  `deliveryDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='used for order tracking info';

-- --------------------------------------------------------

--
-- Table structure for table `promos`
--

CREATE TABLE `promos` (
  `Code` varchar(20) NOT NULL,
  `discount` int(10) NOT NULL,
  `item_ID` int(10) NOT NULL,
  `perc_flat` double(3,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promos`
--

INSERT INTO `promos` (`Code`, `discount`, `item_ID`, `perc_flat`) VALUES
('BANANA10', 10, 1112, 1.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`ID`) USING BTREE;

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`Item_ID`);

--
-- Indexes for table `promos`
--
ALTER TABLE `promos`
  ADD PRIMARY KEY (`Code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `ID` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=785963264;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `Item_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1114;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
