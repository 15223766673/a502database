/*
 Navicat Premium Data Transfer

 Source Server         : a502
 Source Server Type    : MySQL
 Source Server Version : 80019 (8.0.19)
 Source Host           : localhost:3306
 Source Schema         : waterdatabase

 Target Server Type    : MySQL
 Target Server Version : 80019 (8.0.19)
 File Encoding         : 65001

 Date: 04/02/2023 21:28:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for daily_volume_of_runoff
-- ----------------------------
DROP TABLE IF EXISTS `daily_volume_of_runoff`;
CREATE TABLE `daily_volume_of_runoff`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `hydrometric_station_id` int NULL DEFAULT NULL,
  `month_date` date NULL DEFAULT NULL,
  `volume_of_runoff` float NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for grdc_day_station
-- ----------------------------
DROP TABLE IF EXISTS `grdc_day_station`;
CREATE TABLE `grdc_day_station`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `hydrometric_station_id` int NULL DEFAULT NULL,
  `river_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hydrometric_station_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `station_area` float NULL DEFAULT NULL,
  `country_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `latitude` float NULL DEFAULT NULL,
  `longitude` float NULL DEFAULT NULL,
  `altitude` float NULL DEFAULT NULL,
  `owner_of_original_data` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_year` int NULL DEFAULT NULL,
  `end_year` int NULL DEFAULT NULL,
  `time_of_duration` int NULL DEFAULT NULL,
  `data_type` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_update` date NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `data_lines` int NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5292 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for grdc_month_station
-- ----------------------------
DROP TABLE IF EXISTS `grdc_month_station`;
CREATE TABLE `grdc_month_station`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `hydrometric_station_id` int NULL DEFAULT NULL,
  `river_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hydrometric_station_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `station_area` float NULL DEFAULT NULL,
  `country_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `latitude` float NULL DEFAULT NULL,
  `longitude` float NULL DEFAULT NULL,
  `altitude` float NULL DEFAULT NULL,
  `owner_of_original_data` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_year` int NULL DEFAULT NULL,
  `end_year` int NULL DEFAULT NULL,
  `time_of_duration` int NULL DEFAULT NULL,
  `data_type` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_update` date NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `data_lines` int NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2264 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for monthly_volume_of_runoff
-- ----------------------------
DROP TABLE IF EXISTS `monthly_volume_of_runoff`;
CREATE TABLE `monthly_volume_of_runoff`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `hydrometric_station_id` int NULL DEFAULT NULL,
  `month_date` date NULL DEFAULT NULL,
  `original` float NULL DEFAULT NULL,
  `calculated` float NULL DEFAULT NULL,
  `flag` int NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `identity` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
