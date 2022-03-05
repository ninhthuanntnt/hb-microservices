--
-- Table structure for table `hb_user_transactions`
--

DROP TABLE IF EXISTS `hb_user_transactions`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hb_user_transactions`
(
    `id`                 bigint         NOT NULL AUTO_INCREMENT,
    `user_id`            bigint         NOT NULL,
    `transaction_no`     varchar(100)   NOT NULL,
    `amount`             decimal(18, 5) NOT NULL,
    `status`             varchar(50)    NOT NULL,
    `payment_method`     varchar(50)    NOT NULL,
    `payment_type`       varchar(50)    NOT NULL,
    `balance`            decimal(18, 5) NOT NULL,
    `created_by`         varchar(50)    NOT NULL,
    `created_date`       timestamp(3)   NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `last_modified_by`   varchar(50)             DEFAULT NULL,
    `last_modified_date` timestamp(3)   NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_user_transaction_user_id` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 172
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hb_user_transactions`
--

LOCK TABLES `hb_user_transactions` WRITE;
/*!40000 ALTER TABLE `hb_user_transactions`
    DISABLE KEYS */;
INSERT INTO `hb_user_transactions`
VALUES (2, 1, 'TR-9abe00ad0342427388cc62e4f8a46036', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 0.00000, 'user',
        '2021-04-18 15:50:07.401', 'user', '2021-04-18 15:50:07.401'),
       (3, 1, 'TR-ff2b7b0507724e5eb7fc5b410abc9516', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 0.00000, 'user',
        '2021-04-18 15:55:49.040', 'user', '2021-04-18 15:55:49.040'),
       (4, 1, 'TR-e7faa46017df4322815e6bc797b37488', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 0.00000, 'user',
        '2021-04-19 01:53:13.688', 'user', '2021-04-19 01:53:13.688'),
       (5, 1, 'TR-52fa11959d3741a98eece48a0715ab61', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 0.00000, 'user',
        '2021-04-19 02:01:52.233', 'user', '2021-04-19 02:01:52.233'),
       (6, 1, 'TR-3b2ea4caf21a410eb6a9eebdc7c9d3cb', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 0.00000, 'user',
        '2021-04-19 02:08:58.655', 'user', '2021-04-19 02:08:58.655'),
       (7, 1, 'TR-1f94ac298b834ab78c27973395c7bdc5', 100.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 0.00000, 'user',
        '2021-04-19 02:19:20.723', 'user', '2021-04-19 02:19:20.723'),
       (8, 1, 'TR-35fa393d3bd942839b47da9653a3c739', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 0.00000, 'user',
        '2021-04-19 02:26:45.232', 'user', '2021-04-19 02:26:45.232'),
       (9, 1, 'TR-d0dee0db2696447db163d5f1b6a026e9', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 0.00000, 'user',
        '2021-04-19 02:31:04.011', 'user', '2021-04-19 02:31:04.011'),
       (10, 1, 'TR-49803000510d48aebaa84019b3aa86f6', 100.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 0.00000, 'user',
        '2021-04-19 03:07:09.330', 'user', '2021-04-19 03:12:49.036'),
       (11, 1, 'TR-cd43748ab3414cc8a7044a4fc7543def', 100.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 0.00000, 'user',
        '2021-04-19 03:49:36.278', 'user', '2021-04-19 03:51:01.529'),
       (12, 1, 'TR-98cb0a855e3c4c49b457607348adf165', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 96.80000, 'user',
        '2021-04-19 14:42:35.969', 'user', '2021-04-19 14:42:35.969'),
       (13, 1, 'TR-446b4351f505418ca93918c368e60f28', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 96.80000, 'user',
        '2021-04-19 14:48:12.494', 'user', '2021-04-19 14:48:12.494'),
       (14, 1, 'TR-596af47faf394108a24e3138101c2525', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 96.80000, 'user',
        '2021-04-19 14:50:56.277', 'user', '2021-04-19 14:50:56.277'),
       (15, 1, 'TR-517d0bd942a54ae28d0089b9e5a988d4', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 96.80000, 'user',
        '2021-04-19 14:51:02.294', 'user', '2021-04-19 14:51:02.294'),
       (16, 1, 'TR-b8f452b12bed403197d1ea8f18c3ac71', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 96.80000, 'user',
        '2021-04-19 14:51:40.246', 'user', '2021-04-19 14:51:40.246'),
       (17, 1, 'TR-532de854ba094816b88e563502ee5663', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 96.80000, 'user',
        '2021-04-19 14:52:23.674', 'user', '2021-04-19 14:52:23.674'),
       (18, 1, 'TR-0056d7654e79455ea84915ec89cfaf76', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 96.80000, 'user',
        '2021-04-19 14:52:56.470', 'user', '2021-04-19 14:52:56.470'),
       (19, 1, 'TR-f385ecb87bb242d5b8cc94f78d41ae6c', 100.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 96.80000, 'user',
        '2021-04-19 14:53:39.564', 'user', '2021-04-19 14:53:39.564'),
       (26, 1, 'TR-599b7be2225740aab331edc655805e89', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 96.80000, 'user',
        '2021-04-22 07:20:07.385', 'user', '2021-04-22 07:20:07.385'),
       (27, 2, 'TR-37bb5f8125ab408cba50d96a7a6a3680', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 0.00000, 'user',
        '2021-04-22 07:20:07.427', 'user', '2021-04-22 07:20:07.427'),
       (28, 1, 'TR-d3f63841d1c34a2882a56a988be4be0e', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 95.80000, 'user',
        '2021-04-22 07:21:21.178', 'user', '2021-04-22 07:21:21.178'),
       (29, 2, 'TR-99fd5464e9254584a1bd6616967f1527', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 1.00000, 'user',
        '2021-04-22 07:21:21.220', 'user', '2021-04-22 07:21:21.220'),
       (30, 1, 'TR-1611db8ebaff415da52bb9f5c22dad71', 1.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 94.80000, 'user',
        '2021-04-23 04:31:16.988', 'user', '2021-04-23 04:31:16.988'),
       (31, 1, 'TR-100facea94bd4345b599117d73b627a1', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 94.80000, 'user',
        '2021-04-23 04:33:07.851', 'user', '2021-04-23 04:33:07.851'),
       (32, 1, 'TR-6e04b5cf9f904c53873143797070fe11', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 93.80000, 'user',
        '2021-04-23 06:44:43.011', 'user', '2021-04-23 06:44:43.011'),
       (33, 1, 'TR-cbb1ec2aea7f459bb00e7620d9d1d121', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 92.80000, 'user',
        '2021-04-23 06:48:57.077', 'user', '2021-04-23 06:48:57.077'),
       (34, 1, 'TR-fe0d702438b44f6d9b90a7ca811e6ba3', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 91.80000, 'user',
        '2021-04-23 06:51:21.709', 'user', '2021-04-23 06:51:21.709'),
       (35, 1, 'TR-265a2ebe4bc34268a83fd2798612f78a', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 90.80000, 'user',
        '2021-04-23 06:54:04.942', 'user', '2021-04-23 06:54:04.942'),
       (36, 1, 'TR-fb42687dceed4de5942c97220f8f010f', 1.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 90.80000, 'user',
        '2021-04-25 09:26:24.832', 'user', '2021-04-25 09:26:24.832'),
       (37, 1, 'TR-bbe987476e3144a6babca21a77eb8831', 1.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 90.80000, 'user',
        '2021-04-25 09:27:14.315', 'user', '2021-04-25 09:27:14.315'),
       (38, 1, 'TR-4af4ec2f53154887ae2bbbca7fc3d560', 1.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 90.80000, 'user',
        '2021-04-25 09:28:16.095', 'user', '2021-04-25 09:28:16.095'),
       (39, 1, 'TR-7f5bd7cef6904dcbaf03a347a94c94e9', 1.00000, 'CREATED', 'PAYPAL', 'DEPOSIT', 90.80000, 'user',
        '2021-04-25 09:30:00.735', 'user', '2021-04-25 09:30:00.735'),
       (40, 1, 'TR-0a588c51c0f54a898049d89a4207271c', 100.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 90.80000, 'user',
        '2021-04-26 10:03:20.162', 'user', '2021-04-26 10:03:29.163'),
       (41, 1, 'TR-e25b93eed2da496aab21a3728111c06c', 10.00000, 'FINISHED', 'WALLET', 'DONATE', 90.80000, 'user',
        '2021-05-07 07:23:38.582', 'user', '2021-05-07 07:23:38.582'),
       (42, 321, 'TR-c5b3762db869453d845d2075690461b2', 10.00000, 'FINISHED', 'WALLET', 'DONATE', 0.00000, 'user',
        '2021-05-07 07:23:38.604', 'user', '2021-05-07 07:23:38.604'),
       (43, 1, 'TR-d5cffd42a485427f882ea6d67c91cca9', 30.00000, 'FINISHED', 'WALLET', 'DONATE', 80.80000, 'user',
        '2021-05-07 07:24:53.431', 'user', '2021-05-07 07:24:53.431'),
       (44, 321, 'TR-4a16a5c65c7042ad829bb5cdbe0b6e1c', 30.00000, 'FINISHED', 'WALLET', 'DONATE', 10.00000, 'user',
        '2021-05-07 07:24:53.434', 'user', '2021-05-07 07:24:53.434'),
       (45, 1, 'TR-8c9749668f5744ffbd70933076727e07', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 50.80000, 'user',
        '2021-05-07 07:25:29.671', 'user', '2021-05-07 07:25:29.671'),
       (46, 321, 'TR-1a2354d23d5c4442950cf9411d58791f', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 40.00000, 'user',
        '2021-05-07 07:25:29.673', 'user', '2021-05-07 07:25:29.673'),
       (47, 1, 'TR-a183aa3fd8ff4e3b89066cae596328dd', 2.00000, 'FINISHED', 'WALLET', 'DONATE', 49.80000, 'user',
        '2021-05-07 07:39:15.768', 'user', '2021-05-07 07:39:15.768'),
       (48, 321, 'TR-44599356bf654c7f99f846d2cf4bd068', 2.00000, 'FINISHED', 'WALLET', 'DONATE', 41.00000, 'user',
        '2021-05-07 07:39:15.774', 'user', '2021-05-07 07:39:15.774'),
       (49, 1, 'TR-d4a373167907400a94e37f6c95edff13', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 47.80000, 'user',
        '2021-05-07 07:40:04.994', 'user', '2021-05-07 07:40:04.994'),
       (50, 321, 'TR-e6fa553abb244ec3832061b3073eaeba', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 43.00000, 'user',
        '2021-05-07 07:40:05.008', 'user', '2021-05-07 07:40:05.008'),
       (51, 1, 'TR-4cabb199794d4f95951f39be5798530d', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 44.80000, 'user',
        '2021-05-07 07:40:20.250', 'user', '2021-05-07 07:40:20.250'),
       (52, 321, 'TR-b35c22d006f141eaa8080a05f0098a11', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 46.00000, 'user',
        '2021-05-07 07:40:20.253', 'user', '2021-05-07 07:40:20.253'),
       (53, 1, 'TR-a86ef2ceb61f4274a57e6d77cc9b0d88', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 43.80000, 'user',
        '2021-05-07 07:40:30.861', 'user', '2021-05-07 07:40:30.861'),
       (54, 321, 'TR-7a72a2745c744befbd806e956c08f7d4', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 47.00000, 'user',
        '2021-05-07 07:40:30.868', 'user', '2021-05-07 07:40:30.868'),
       (55, 1, 'TR-ac6066814b414b75a8ff6824c1a71c90', 4.00000, 'FINISHED', 'WALLET', 'DONATE', 40.80000, 'user',
        '2021-05-07 07:40:42.252', 'user', '2021-05-07 07:40:42.252'),
       (56, 321, 'TR-24bd7ceff941466ba73e2fd67ead4fb3', 4.00000, 'FINISHED', 'WALLET', 'DONATE', 50.00000, 'user',
        '2021-05-07 07:40:42.255', 'user', '2021-05-07 07:40:42.255'),
       (57, 1, 'TR-6a673d1e9adb4339944e22a0442aa899', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 36.80000, 'user',
        '2021-05-07 07:41:26.618', 'user', '2021-05-07 07:41:26.618'),
       (58, 321, 'TR-694197547fe741f9a4ee9c8bc56d6532', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 54.00000, 'user',
        '2021-05-07 07:41:26.620', 'user', '2021-05-07 07:41:26.620'),
       (59, 1, 'TR-1c98672ed92446d29df905e0acfbbe91', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 33.80000, 'user',
        '2021-05-07 07:42:36.870', 'user', '2021-05-07 07:42:36.870'),
       (60, 321, 'TR-fc541302d99a4b998f48b456bd8142aa', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 57.00000, 'user',
        '2021-05-07 07:42:36.873', 'user', '2021-05-07 07:42:36.873'),
       (61, 1, 'TR-da6ff0fcdfab4785acef5bc1e0adfd34', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 30.80000, 'user',
        '2021-05-07 07:47:18.350', 'user', '2021-05-07 07:47:18.350'),
       (62, 321, 'TR-7a2d36f7a80e4d648e75873dfeed371b', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 60.00000, 'user',
        '2021-05-07 07:47:18.352', 'user', '2021-05-07 07:47:18.352'),
       (63, 1, 'TR-4f235c513fd74e378124da6939256197', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 27.80000, 'user',
        '2021-05-07 07:48:26.337', 'user', '2021-05-07 07:48:26.337'),
       (64, 321, 'TR-e18f72b75f414553b86cd13b62dac778', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 63.00000, 'user',
        '2021-05-07 07:48:26.342', 'user', '2021-05-07 07:48:26.342'),
       (65, 1, 'TR-56a2c5f6c9ff4717b2f544ef71a09708', 2.00000, 'FINISHED', 'WALLET', 'DONATE', 24.80000, 'user',
        '2021-05-07 07:49:32.586', 'user', '2021-05-07 07:49:32.586'),
       (66, 321, 'TR-9866533bd16c486eb244e3fb24073da2', 2.00000, 'FINISHED', 'WALLET', 'DONATE', 66.00000, 'user',
        '2021-05-07 07:49:32.588', 'user', '2021-05-07 07:49:32.588'),
       (67, 1, 'TR-cf414380a3ef49a698d8fd0e043150b7', 11.00000, 'FINISHED', 'WALLET', 'DONATE', 22.80000, 'user',
        '2021-05-07 07:49:40.623', 'user', '2021-05-07 07:49:40.623'),
       (68, 321, 'TR-8df9525af1494995bcfaaa7ffc645f44', 11.00000, 'FINISHED', 'WALLET', 'DONATE', 68.00000, 'user',
        '2021-05-07 07:49:40.625', 'user', '2021-05-07 07:49:40.625'),
       (69, 1, 'TR-1ea5c1c0bc5841749f8323937ff0bd95', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 11.80000, 'user',
        '2021-05-07 07:50:27.786', 'user', '2021-05-07 07:50:27.786'),
       (70, 321, 'TR-284375024ff9488daa9f94ac175a7f7d', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 79.00000, 'user',
        '2021-05-07 07:50:27.789', 'user', '2021-05-07 07:50:27.789'),
       (71, 1, 'TR-9f37a6bb879d4c228b84c96034c95706', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 8.80000, 'user',
        '2021-05-07 07:52:52.118', 'user', '2021-05-07 07:52:52.118'),
       (72, 321, 'TR-21cd3dca80d3456ea7907ca8db864c67', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 82.00000, 'user',
        '2021-05-07 07:52:52.121', 'user', '2021-05-07 07:52:52.121'),
       (73, 1, 'TR-ad50ee6a31bd482faaec6f2ee91771dc', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 7.80000, 'user',
        '2021-05-07 07:53:00.590', 'user', '2021-05-07 07:53:00.590'),
       (74, 321, 'TR-89ccd70bc4be4b81933072a5badf30b3', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 83.00000, 'user',
        '2021-05-07 07:53:00.593', 'user', '2021-05-07 07:53:00.593'),
       (75, 1, 'TR-83bbc748ddf24485a00fa16d7994f958', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 6.80000, 'user',
        '2021-05-07 07:53:07.669', 'user', '2021-05-07 07:53:07.669'),
       (76, 321, 'TR-af08fbe0fe854c8aa29717d61b3d8fa4', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 84.00000, 'user',
        '2021-05-07 07:53:07.671', 'user', '2021-05-07 07:53:07.671'),
       (77, 1, 'TR-38c2f8eea37544e9830f806b5278358b', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 5.80000, 'user',
        '2021-05-07 13:23:44.265', 'user', '2021-05-07 13:23:44.265'),
       (78, 728, 'TR-41c6197ffe7944e0b78d385a0a787bfa', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 0.00000, 'user',
        '2021-05-07 13:23:44.275', 'user', '2021-05-07 13:23:44.275'),
       (79, 1, 'TR-e2ae54c8121b4e7fb64e7f67f0e24884', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 3.80000, 'user',
        '2021-05-08 13:44:16.271', 'user', '2021-05-08 13:44:16.271'),
       (80, 1, 'TR-96305cc200e841f3a3f7500fb4100a0f', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 2.80000, 'user',
        '2021-05-08 13:44:53.275', 'user', '2021-05-08 13:44:53.275'),
       (81, 1, 'TR-53d38753ce39426399d13530a349a291', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 1.80000, 'user',
        '2021-05-08 13:45:03.015', 'user', '2021-05-08 13:45:03.015'),
       (82, 1, 'TR-4abb24d2857c446abb12c0838e651b8d', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 0.80000, 'user',
        '2021-05-08 13:45:10.431', 'user', '2021-05-08 13:45:10.431'),
       (83, 1, 'TR-135da34f731d463b86979c7434597e83', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 99999.00000, 'user',
        '2021-05-08 13:56:11.223', 'user', '2021-05-08 13:56:11.223'),
       (84, 1, 'TR-62878a2ed6954d0d813b4af14e49a27b', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 99998.00000, 'user',
        '2021-05-08 13:56:16.617', 'user', '2021-05-08 13:56:16.617'),
       (85, 1, 'TR-fd3339a886514a56992d497ad2d23f85', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 99997.00000, 'user',
        '2021-05-08 13:56:20.693', 'user', '2021-05-08 13:56:20.693'),
       (86, 1, 'TR-c4f130223cba4a2e81fe1e25fd8710db', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 99996.00000, 'user',
        '2021-05-08 13:56:54.054', 'user', '2021-05-08 13:56:54.054'),
       (87, 1, 'TR-0dff02f918cb4cd3a80790f3966b56df', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 99995.00000, 'user',
        '2021-05-08 13:56:58.232', 'user', '2021-05-08 13:56:58.232'),
       (88, 1, 'TR-ce0a329a071248538ee4fc7638900651', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 99994.00000, 'user',
        '2021-05-08 14:02:28.974', 'user', '2021-05-08 14:02:28.974'),
       (89, 1, 'TR-008d3fa233c64252ac16e4c5f19aa133', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 99993.00000, 'user',
        '2021-05-08 14:02:48.504', 'user', '2021-05-08 14:02:48.504'),
       (90, 1, 'TR-d3e6b1120ef845d1ab6e2c54cc4bb56f', 1.00000, 'IN_PROGRESS', 'PAYPAL', 'DEPOSIT', 99992.00000, 'user',
        '2021-05-08 14:03:00.612', 'user', '2021-05-08 14:03:00.612'),
       (91, 1, 'TR-4e192baeb797409689ab21314fe63dc7', 200.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 99792.00000,
        'user', '2021-05-08 14:30:47.768', 'user', '2021-05-08 14:30:47.768'),
       (92, 1, 'TR-beb1208a55ae4ee893d09214dce1e19c', 3.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 99792.00000, 'user',
        '2021-05-08 14:44:55.058', 'user', '2021-05-08 14:50:03.211'),
       (93, 1, 'TR-d497182191c4490a846e7b3f7e994288', 8.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 99794.61000, 'user',
        '2021-05-08 14:52:39.113', 'user', '2021-05-08 14:52:59.542'),
       (94, 1, 'TR-c81795aea2fd410cb03fe8462e86c3cb', 8.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 99802.08000, 'user',
        '2021-05-08 14:54:29.557', 'user', '2021-05-08 14:54:47.505'),
       (95, 1, 'TR-8b736609ee964b729003f333ce4125b7', 1.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 99809.55000, 'user',
        '2021-05-08 15:20:47.521', 'user', '2021-05-08 15:20:47.737'),
       (96, 1, 'TR-e5443ff3c0b343d9b00355058f3f4bbf', 1.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 99809.55000, 'user',
        '2021-05-08 16:23:02.173', 'user', '2021-05-08 16:23:02.410'),
       (97, 1, 'TR-a0ca020e9b9e4d05a05746ac5d4278b2', 1.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 99809.55000, 'user',
        '2021-05-08 16:23:05.678', 'user', '2021-05-08 16:23:14.221'),
       (98, 1, 'TR-9e8c53e2d4e84b7d87ed5655f6518f57', 4.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 99809.55000, 'user',
        '2021-05-09 13:34:13.192', 'user', '2021-05-09 13:34:36.223'),
       (99, 1, 'TR-15ab6c4fa19343fba5710a65c10f9578', 4.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 99813.13000, 'user',
        '2021-05-09 13:34:45.418', 'user', '2021-05-09 13:35:03.161'),
       (100, 1, 'TR-9c624e19eb1940ce82490e2b20f32eb5', 1.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 99816.71000, 'user',
        '2021-05-09 14:28:49.908', 'user', '2021-05-09 14:28:52.228'),
       (101, 1, 'TR-f175dbdc94354645ac22810b674bb27f', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 99816.71000, 'user',
        '2021-05-12 07:59:19.808', 'user', '2021-05-12 07:59:19.808'),
       (102, 452, 'TR-81e5081d15e94af090bd245965f69f90', 1.00000, 'FINISHED', 'WALLET', 'DONATE', 0.00000, 'user',
        '2021-05-12 07:59:19.815', 'user', '2021-05-12 07:59:19.815'),
       (103, 1, 'TR-3a716243875743e2a3c787bc5eb8f5fb', 1.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 99815.71000, 'user',
        '2021-05-15 15:14:30.779', 'user', '2021-05-15 15:15:00.183'),
       (104, 1, 'TR-16b947e397f34aa996b6044ad6c1d8e9', 4.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 99816.38000, 'user',
        '2021-05-16 14:13:20.575', 'user', '2021-05-16 14:13:42.239'),
       (105, 1, 'TR-89881124f0f248aca5e2ec86f2963329', 4.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 99816.38000, 'user',
        '2021-05-16 14:13:44.956', 'user', '2021-05-16 14:13:51.016'),
       (106, 1, 'TR-613e10a664c34187a7de374307a56c1f', 10.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 99816.38000, 'user',
        '2021-05-17 16:34:18.092', 'user', '2021-05-17 16:35:08.143'),
       (107, 1, 'TR-1a471fceb7c844718ed0fd9b80386b9c', 10.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 99816.38000, 'user',
        '2021-05-17 16:35:28.539', 'user', '2021-05-17 16:35:33.065'),
       (108, 1, 'TR-552b06575f064983b239922410c89122', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 99816.38000, 'user',
        '2021-05-17 16:35:54.747', 'user', '2021-05-17 16:35:54.747'),
       (109, 718, 'TR-ff01eea6cba64ba48a9b56ec531a8360', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 0.00000, 'user',
        '2021-05-17 16:35:54.751', 'user', '2021-05-17 16:35:54.751'),
       (110, 1, 'TR-6e4797e7d5f44a35a89f1a58a1b009fe', 1.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 99811.38000, 'user',
        '2021-05-17 16:46:03.094', 'user', '2021-05-17 16:46:21.581'),
       (111, 1, 'TR-bfa3459b89784f05b777bb9719fafda7', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 99811.38000, 'user',
        '2021-05-17 16:56:05.471', 'user', '2021-05-17 16:56:05.471'),
       (112, 718, 'TR-2f481462ac314ae1ac6485fe04723ff2', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 5.00000, 'user',
        '2021-05-17 16:56:05.474', 'user', '2021-05-17 16:56:05.474'),
       (113, 1, 'TR-9717273b3d1c4b7985e87202c10aff6e', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 99806.38000, 'user',
        '2021-05-17 16:58:10.596', 'user', '2021-05-17 16:58:10.596'),
       (114, 718, 'TR-634ebe5d476e44bdadd38d007ad62fab', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 10.00000, 'user',
        '2021-05-17 16:58:10.599', 'user', '2021-05-17 16:58:10.599'),
       (115, 1, 'TR-ccd879c7f4d44a24aec0ff683f2d9834', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 99801.38000, 'user',
        '2021-05-17 16:59:46.901', 'user', '2021-05-17 16:59:46.901'),
       (116, 718, 'TR-0fb47ebaf7ae4e52baa83be264a4ab6e', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 15.00000, 'user',
        '2021-05-17 16:59:46.905', 'user', '2021-05-17 16:59:46.905'),
       (117, 1, 'TR-ae563bf2c2a74373bb92dd7486fd225e', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 99796.38000, 'user',
        '2021-05-17 17:03:00.723', 'user', '2021-05-17 17:03:00.723'),
       (118, 718, 'TR-2d2011155dc54e77a09f26515545754b', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 20.00000, 'user',
        '2021-05-17 17:03:00.728', 'user', '2021-05-17 17:03:00.728'),
       (119, 1, 'TR-b1eb0da875bf4c4589c2cd2115d99649', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 99791.38000, 'user',
        '2021-05-17 17:06:56.402', 'user', '2021-05-17 17:06:56.402'),
       (120, 718, 'TR-10bd8322589346a9b17c4eaa5750eb9e', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 25.00000, 'user',
        '2021-05-17 17:06:56.405', 'user', '2021-05-17 17:06:56.405'),
       (121, 1, 'TR-80a8b9fecd124875a33d3ee659bd9317', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 99786.38000, 'user',
        '2021-05-17 17:15:44.597', 'user', '2021-05-17 17:15:44.597'),
       (122, 718, 'TR-d4378c098d414f11800fe37d90fd326e', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 30.00000, 'user',
        '2021-05-17 17:15:44.600', 'user', '2021-05-17 17:15:44.600'),
       (123, 1, 'TR-8ce64327578e4272b5254961ac083298', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 99781.38000, 'user',
        '2021-05-17 17:15:52.174', 'user', '2021-05-17 17:15:52.174'),
       (124, 718, 'TR-d943de8ff0204007989a75dabf0363e6', 5.00000, 'FINISHED', 'WALLET', 'DONATE', 35.00000, 'user',
        '2021-05-17 17:15:52.176', 'user', '2021-05-17 17:15:52.176'),
       (125, 1, 'TR-e76ff0a41826451c8e1c769f511aa51f', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 99776.38000, 'user',
        '2021-05-17 17:16:30.935', 'user', '2021-05-17 17:16:30.935'),
       (126, 718, 'TR-bbb83a11c7fc4e579d7d06b644788e97', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 40.00000, 'user',
        '2021-05-17 17:16:30.938', 'user', '2021-05-17 17:16:30.938'),
       (127, 1, 'TR-367922a0b2034ce9aa9851ac7af1aa7f', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 99773.38000, 'user',
        '2021-05-17 17:19:49.909', 'user', '2021-05-17 17:19:49.909'),
       (128, 718, 'TR-e4a9d751e58345369ef6e74fead75f78', 3.00000, 'FINISHED', 'WALLET', 'DONATE', 43.00000, 'user',
        '2021-05-17 17:19:49.912', 'user', '2021-05-17 17:19:49.912'),
       (129, 1, 'TR-f8558a7153494cf9b8687c2d68127dd6', -9.00000, 'FINISHED', 'WALLET', 'DONATE', 99770.38000, 'user',
        '2021-05-17 17:24:25.870', 'user', '2021-05-17 17:24:25.870'),
       (130, 718, 'TR-00d2594f17fd4389897a6da409c72284', -9.00000, 'FINISHED', 'WALLET', 'DONATE', 46.00000, 'user',
        '2021-05-17 17:24:25.873', 'user', '2021-05-17 17:24:25.873'),
       (131, 1, 'TR-6aa8de763ba74f31a2ac774d2db79a95', -100000.00000, 'FINISHED', 'WALLET', 'DONATE', 99779.38000,
        'user', '2021-05-17 17:25:05.802', 'user', '2021-05-17 17:25:05.802'),
       (132, 718, 'TR-bec237ee88844bb98b4b35a7bfa8cf55', -100000.00000, 'FINISHED', 'WALLET', 'DONATE', 37.00000,
        'user', '2021-05-17 17:25:05.805', 'user', '2021-05-17 17:25:05.805'),
       (133, 1, 'TR-091ac36ead434fcf907946e6bbb4f7d2', 5.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 199779.38000, 'user',
        '2021-05-19 11:04:29.143', 'user', '2021-05-19 11:04:53.428'),
       (134, 1, 'TR-e72ea1fbc6d7479ab78e2e0207f73020', 6.00000, 'FINISHED', 'WALLET', 'DONATE', 199783.93000, 'user',
        '2021-05-26 04:38:52.621', 'user', '2021-05-26 04:38:52.621'),
       (135, 639, 'TR-b2c7af7e0bf94dbdb9736256509c3047', 6.00000, 'FINISHED', 'WALLET', 'DONATE', 0.00000, 'user',
        '2021-05-26 04:38:52.627', 'user', '2021-05-26 04:38:52.627'),
       (136, 1, 'TR-f9a16725d51646f69e71169b0b487fb4', 6.00000, 'FINISHED', 'WALLET', 'DONATE', 199777.93000, 'user',
        '2021-05-26 04:39:15.903', 'user', '2021-05-26 04:39:15.903'),
       (137, 639, 'TR-53b2e0690c7f41668e797f1926f345ca', 6.00000, 'FINISHED', 'WALLET', 'DONATE', 6.00000, 'user',
        '2021-05-26 04:39:15.905', 'user', '2021-05-26 04:39:15.905'),
       (138, 1, 'TR-6e6d284e752744139f1b893b1a30df93', 100.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199671.93000,
        'user', '2021-06-01 14:40:27.535', 'user', '2021-06-01 14:40:27.535'),
       (139, 1, 'TR-02d14e3290fe4fd4a447eed098c29abd', 110.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199561.93000,
        'user', '2021-06-01 14:41:42.835', 'user', '2021-06-01 14:41:42.835'),
       (140, 1, 'TR-4693d5322daa4b70a716a9ef172e72e6', 2.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 199561.93000, 'user',
        '2021-06-01 15:35:53.929', 'user', '2021-06-01 15:36:05.504'),
       (141, 1, 'TR-5fb4bb3b732e4cd7bf5643539af1f957', 4.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 199561.93000, 'user',
        '2021-06-01 15:38:56.356', 'user', '2021-06-01 15:39:18.370'),
       (142, 1, 'TR-e91ea0b7d4a64007a9c4a68654b796b5', 2.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199563.51000,
        'user', '2021-06-01 15:41:29.197', 'user', '2021-06-01 15:41:29.197'),
       (143, 1, 'TR-537f12ad3eb84b7f9d0f17f09a73df32', 6.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199557.51000,
        'user', '2021-06-01 15:43:11.132', 'user', '2021-06-01 15:43:11.132'),
       (144, 1, 'TR-8c5b47a2831f4ef3af07798495231a84', 3.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199554.51000,
        'user', '2021-06-01 15:47:24.622', 'user', '2021-06-01 15:47:24.622'),
       (145, 1, 'TR-7ed7d7a4e4ef4964a7b3bff0d24c1299', 3.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 199554.51000, 'user',
        '2021-06-01 15:47:48.312', 'user', '2021-06-01 15:49:19.426'),
       (146, 1, 'TR-6e1367bf61ec4087bb46222dfa18a0dc', 3.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 199557.12000, 'user',
        '2021-06-01 15:49:50.249', 'user', '2021-06-01 15:50:11.908'),
       (147, 1, 'TR-80efb8f7c70245a2b3bda1691de39cdf', 4.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 199559.73000, 'user',
        '2021-06-01 15:55:55.744', 'user', '2021-06-01 15:56:23.287'),
       (148, 1, 'TR-a08d3546f4eb40c4b01ee41c743ddaab', 4.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 199563.31000, 'user',
        '2021-06-05 14:37:14.517', 'user', '2021-06-05 14:37:18.039'),
       (149, 1, 'TR-53a9c3372b0a4d548500607d1af7d65e', 4.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 199563.31000, 'user',
        '2021-06-05 14:37:21.365', 'user', '2021-06-05 14:38:04.107'),
       (150, 1, 'TR-dde486a6d91e4443b3e4c120c0b9dedb', 4.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 199563.31000, 'user',
        '2021-06-05 14:38:38.377', 'user', '2021-06-05 14:38:40.890'),
       (151, 1, 'TR-c1317c14ea284b05985bb162a57cd6bf', 4.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 199563.31000, 'user',
        '2021-06-05 14:38:46.233', 'user', '2021-06-05 14:39:19.101'),
       (152, 1, 'TR-f8e03d81985c46b6b7ea6ecca1b206a3', 1.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 199566.89000, 'user',
        '2021-06-05 14:40:57.239', 'user', '2021-06-05 14:41:31.128'),
       (153, 1, 'TR-37e6140476144b90b0fbe6bc1649749d', 4.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199563.56000,
        'user', '2021-06-06 08:00:49.219', 'user', '2021-06-06 08:00:49.219'),
       (154, 1, 'TR-17c1deff62404b8fa8b23d9172b523a7', 1.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 199563.56000, 'user',
        '2021-06-06 08:00:54.379', 'user', '2021-06-06 08:02:24.336'),
       (155, 1, 'TR-6656a1cdfbf4456e8eab609a5a532f60', 8.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 199563.56000, 'user',
        '2021-06-06 08:02:49.382', 'user', '2021-06-06 08:03:09.380'),
       (156, 1, 'TR-035d4a600d75476c90ea69b7a69fcc54', 6.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 199571.03000, 'user',
        '2021-06-06 08:03:18.438', 'user', '2021-06-06 08:03:46.137'),
       (157, 1, 'TR-a9c65c4a91a847eaafb93933e67a8e2d', 8.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199568.56000,
        'user', '2021-06-06 08:03:56.267', 'user', '2021-06-06 08:03:56.267'),
       (158, 1, 'TR-2981c798fb094d6e915a6ee3cc4b2c77', 9.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199559.56000,
        'user', '2021-06-06 08:12:20.287', 'user', '2021-06-06 08:12:20.287'),
       (159, 1, 'TR-d17935eed5cd43a6a385b401a8febca8', 9.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199550.56000,
        'user', '2021-06-06 08:12:40.328', 'user', '2021-06-06 08:12:40.328'),
       (160, 1, 'TR-5e54ddf5b6a548c78523a8910a5b188d', 3.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199547.56000,
        'user', '2021-06-06 08:14:38.635', 'user', '2021-06-06 08:14:38.635'),
       (161, 1, 'TR-853aca9d37934359925682635a7d4962', 5.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199542.56000,
        'user', '2021-06-06 08:15:12.552', 'user', '2021-06-06 08:15:12.552'),
       (162, 1, 'TR-3e11da39adbb4cad875dd95f209d0bad', 9.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199533.56000,
        'user', '2021-06-06 08:15:17.617', 'user', '2021-06-06 08:15:17.617'),
       (163, 1, 'TR-30472fccaf3f47c795d9054b5a9d6126', 9.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 199533.56000, 'user',
        '2021-06-06 08:15:28.281', 'user', '2021-06-06 08:15:50.327'),
       (164, 1, 'TR-1370b7a116724ad89b7a662d3ef8f31e', 8.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199525.56000,
        'user', '2021-06-06 08:39:50.463', 'user', '2021-06-06 08:39:50.463'),
       (165, 1, 'TR-adb57b7a77174e82ac1bd6b23f17c8e9', 9.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 199516.56000,
        'user', '2021-06-06 08:40:06.627', 'user', '2021-06-06 08:40:06.627'),
       (166, 1, 'TR-641210b0f72948ab9d0dc31f9cdf6fb3', 10.00000, 'CANCELED', 'PAYPAL', 'DEPOSIT', 199516.56000, 'user',
        '2021-06-07 08:42:16.028', 'user', '2021-06-07 08:44:08.851'),
       (167, 1, 'TR-e3a644a3108a48f2b2f89240f33be9fc', 20000.00000, 'FINISHED', 'WALLET', 'DONATE', 199516.56000,
        'user', '2021-06-07 08:46:20.978', 'user', '2021-06-07 08:46:20.978'),
       (168, 996, 'TR-e75967cc712a41da96e21eeabd8fe4a9', 20000.00000, 'FINISHED', 'WALLET', 'DONATE', 0.00000, 'user',
        '2021-06-07 08:46:20.986', 'user', '2021-06-07 08:46:20.986'),
       (169, 1, 'TR-89052f212ae74bc689641e6720f441a5', 5.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 179516.56000, 'user',
        '2021-06-08 03:28:07.309', 'user', '2021-06-08 03:30:47.790'),
       (170, 1, 'TR-ae41e1feabd94ef6b3f2feff68bd8538', 4.00000, 'FINISHED', 'PAYPAL', 'DEPOSIT', 179521.11000, 'user',
        '2021-06-13 16:18:23.863', 'user', '2021-06-13 16:18:47.941'),
       (171, 1, 'TR-1e8ae3a15e7d4c2c913ae59a5cb712e3', 3.00000, 'IN_PROGRESS', 'PAYPAL', 'WITHDRAW', 179521.69000,
        'user', '2021-06-13 16:18:55.645', 'user', '2021-06-13 16:18:55.645');
/*!40000 ALTER TABLE `hb_user_transactions`
    ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `hb_third_party_transactions`
--

DROP TABLE IF EXISTS `hb_third_party_transactions`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hb_third_party_transactions`
(
    `id`                 bigint         NOT NULL AUTO_INCREMENT,
    `payment_id`         varchar(255)   NOT NULL,
    `amount`             decimal(18, 5) NOT NULL,
    `currency_type`      varchar(5)     NOT NULL,
    `status`             varchar(50)    NOT NULL,
    `fee`                float                   DEFAULT NULL,
    `additional_params`  json                    DEFAULT NULL,
    `created_by`         varchar(50)    NOT NULL,
    `created_date`       timestamp(3)   NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `last_modified_by`   varchar(50)             DEFAULT NULL,
    `last_modified_date` timestamp(3)   NULL     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 138
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hb_third_party_transactions`
--

LOCK TABLES `hb_third_party_transactions` WRITE;
/*!40000 ALTER TABLE `hb_third_party_transactions`
    DISABLE KEYS */;
INSERT INTO `hb_third_party_transactions`
VALUES (3, '0XK755134J0281434', 100.00000, 'USD', 'CREATED', NULL, 'null', 'user', '2021-04-18 15:50:04.840', 'user',
        '2021-04-18 15:50:04.840'),
       (4, '02S18369AA3819724', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=02S18369AA3819724\"
       }', 'user', '2021-04-18 15:55:46.304', 'user', '2021-04-18 15:55:46.304'),
       (5, '4KL50132706654149', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=4KL50132706654149\"
       }', 'user', '2021-04-19 01:53:13.591', 'user', '2021-04-19 01:53:13.591'),
       (6, '4HN93111P27198143', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=4HN93111P27198143\"
       }', 'user', '2021-04-19 02:01:52.075', 'user', '2021-04-19 02:01:52.075'),
       (7, '0EG29229U5016921E', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=0EG29229U5016921E\"
       }', 'user', '2021-04-19 02:08:58.477', 'user', '2021-04-19 02:08:58.477'),
       (9, '2PX74812J4586030F', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=2PX74812J4586030F\"
       }', 'user', '2021-04-19 02:19:20.527', 'user', '2021-04-19 02:19:20.527'),
       (10, '2PX74812J4586030F', 100.00000, 'USD', 'COMPLETED', 3.2, '{
         \"payer\": {}
       }', 'user', '2021-04-19 02:24:16.797', 'user', '2021-04-19 02:24:25.179'),
       (11, '33Y699782S401010T', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=33Y699782S401010T\"
       }', 'user', '2021-04-19 02:26:45.222', 'user', '2021-04-19 02:26:45.222'),
       (12, '33Y699782S401010T', 100.00000, 'USD', 'COMPLETED', 3.2, '{
         \"payer\": {}
       }', 'user', '2021-04-19 02:28:17.366', 'user', '2021-04-19 02:28:19.191'),
       (13, '8B0778160W157620C', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=8B0778160W157620C\"
       }', 'user', '2021-04-19 02:31:03.876', 'user', '2021-04-19 02:31:03.876'),
       (14, '0JG63864XA654464J', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=0JG63864XA654464J\"
       }', 'user', '2021-04-19 03:07:09.304', 'user', '2021-04-19 03:07:09.304'),
       (15, '0JG63864XA654464J', 100.00000, 'USD', 'COMPLETED', 3.2, '{
         \"payer\": {}
       }', 'user', '2021-04-19 03:12:47.229', 'user', '2021-04-19 03:12:49.035'),
       (16, '4VK72617D2933031C', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=4VK72617D2933031C\"
       }', 'user', '2021-04-19 03:49:36.089', 'user', '2021-04-19 03:49:36.089'),
       (17, '4VK72617D2933031C', 100.00000, 'USD', 'COMPLETED', 3.2, '{
         \"payer\": {}
       }', 'user', '2021-04-19 03:51:01.472', 'user', '2021-04-19 03:51:01.528'),
       (18, '6LP912596B6684109', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=6LP912596B6684109\"
       }', 'user', '2021-04-19 14:42:35.839', 'user', '2021-04-19 14:42:35.839'),
       (19, '0U20746530355851G', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=0U20746530355851G\"
       }', 'user', '2021-04-19 14:48:12.466', 'user', '2021-04-19 14:48:12.466'),
       (20, '4831171568705233E', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=4831171568705233E\"
       }', 'user', '2021-04-19 14:50:56.241', 'user', '2021-04-19 14:50:56.241'),
       (21, '264327857L4102929', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=264327857L4102929\"
       }', 'user', '2021-04-19 14:51:02.220', 'user', '2021-04-19 14:51:02.220'),
       (22, '53C77357HF331074A', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=53C77357HF331074A\"
       }', 'user', '2021-04-19 14:51:40.237', 'user', '2021-04-19 14:51:40.237'),
       (23, '9EM90668XU671023H', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=9EM90668XU671023H\"
       }', 'user', '2021-04-19 14:52:23.664', 'user', '2021-04-19 14:52:23.664'),
       (24, '8SX05256S84169221', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=8SX05256S84169221\"
       }', 'user', '2021-04-19 14:52:56.462', 'user', '2021-04-19 14:52:56.462'),
       (25, '5PD39864RC2568405', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=5PD39864RC2568405\"
       }', 'user', '2021-04-19 14:53:39.532', 'user', '2021-04-19 14:53:39.532'),
       (26, 'RLY5GB4VLZAHC', 1.00000, 'USD', 'SUCCESS', 0.25, '{
         \"senderItemId\": \"fd8dcde8abf34a9e9a94f01a3f78a4f0\",
         \"senderBatchId\": \"BATCH_ID_HB-6a174a810d1c41c690a81a22233377ae\"
       }', 'user', '2021-04-23 03:41:36.671', 'user', '2021-04-23 03:41:36.671'),
       (27, 'MA6D5Z4MV67X4', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"e765866f56cf4fd7a228e3ba10111eb6\",
         \"senderBatchId\": \"BATCH_ID_HB-cacaa8d8074d4c6580c5edd101299e90\"
       }', 'user', '2021-04-23 03:49:58.034', 'user', '2021-04-23 03:49:58.034'),
       (28, 'PAL2XQG4X3XJL', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"006162d1adfb41c5833d4c56fd889d49\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-8e9331d112084ebb96f5b0a1c66ceb48\"
       }', 'user', '2021-04-23 04:21:03.695', 'user', '2021-04-23 04:21:03.695'),
       (29, 'MGX4UQCWJQUEA', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"d62e7c281e9b463d8201e4f8544a4838\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-e62c8e237ac749319fcc27db9b9ca055\"
       }', 'user', '2021-04-23 04:24:31.480', 'user', '2021-04-23 04:24:31.480'),
       (30, '2W93MZRVLLDD4', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"a14cc2c4affb4e05bea2c5d0cd30aac1\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-7ff06fce015c447da70eff592d4c9cde\"
       }', 'user', '2021-04-23 04:26:43.993', 'user', '2021-04-23 04:26:43.993'),
       (32, 'RR76ZPQZSRN9Y', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"18ccd9860e87476a82b1c258083e8468\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-86b1d5e80916425c82939f5d6a900d6b\"
       }', 'user', '2021-04-23 04:31:16.832', 'user', '2021-04-23 04:31:16.832'),
       (33, 'XWEJWG72XB23N', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"625823f503d74bbd9731dbbf4e734501\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-a41897af9fd041f38d05e8c690092058\"
       }', 'user', '2021-04-23 04:33:07.710', 'user', '2021-04-23 04:33:07.710'),
       (34, '5UQQY7GPARBAS', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"2d836b2497674e40a60db1ee529b657f\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-0d303d696a034c418e565c854eec941d\"
       }', 'user', '2021-04-23 06:44:17.137', 'user', '2021-04-23 06:44:17.137'),
       (35, 'WLL3UCQK2XF5J', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"b8e446ea4aa74c19aa2d8730ffea624b\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-5c7f4e947ed04c009a2f896b987b139d\"
       }', 'user', '2021-04-23 06:48:54.594', 'user', '2021-04-23 06:48:54.594'),
       (36, '5AFBQVBKAHDTN', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"3d76a5eb76ba4cd2a55f0e636d2ff991\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-5c96f59746b043b19279d630ea8685d5\"
       }', 'user', '2021-04-23 06:51:21.634', 'user', '2021-04-23 06:51:21.634'),
       (37, 'LJA4NXTZABVPY', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"285b3e0463204355a568558b14511071\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-43a199ed7ddc4e0481138045128e3322\"
       }', 'user', '2021-04-23 06:54:04.928', 'user', '2021-04-23 06:54:04.928'),
       (38, '0WG65523J11283849', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=0WG65523J11283849\"
       }', 'user', '2021-04-25 09:26:24.681', 'user', '2021-04-25 09:26:24.681'),
       (39, '1BC461159P428954W', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=1BC461159P428954W\"
       }', 'user', '2021-04-25 09:27:14.294', 'user', '2021-04-25 09:27:14.294'),
       (40, '9BP449987N001020T', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=9BP449987N001020T\"
       }', 'user', '2021-04-25 09:28:16.087', 'user', '2021-04-25 09:28:16.087'),
       (41, '9NF617235D9739425', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=9NF617235D9739425\"
       }', 'user', '2021-04-25 09:30:00.722', 'user', '2021-04-25 09:30:00.722'),
       (42, '7M3354141L2241402', 100.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=7M3354141L2241402\"
       }', 'user', '2021-04-26 10:03:19.934', 'user', '2021-04-26 10:03:19.934'),
       (43, '7M3354141L2241402', 100.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-04-26 10:03:29.143', 'user',
        '2021-04-26 10:03:29.143'),
       (44, 'K275NFSLH7WMQ', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"718abe47d362417ea1280eea34dc8152\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-cd48a25b6d0741e78bd5d5b4d3103763\"
       }', 'user', '2021-05-08 13:44:16.203', 'user', '2021-05-08 13:44:16.203'),
       (45, 'VCQAL5A6RFCC8', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"a57e0c4f0cb849aab5fed26cf5c4cd4c\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-0f9d92bd461e4b3aa46a7a25fa0c7644\"
       }', 'user', '2021-05-08 13:44:53.261', 'user', '2021-05-08 13:44:53.261'),
       (46, 'S54PXL3BGJ3ES', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"b5fa0ae80c0b44ebac62f9887ba085c0\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-414df1aeec1d4f159e2c08dc12788d8d\"
       }', 'user', '2021-05-08 13:45:02.999', 'user', '2021-05-08 13:45:02.999'),
       (47, 'B9VFHYZ75KQHN', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"c1b2b90daf374ebf952689ff95aaeab1\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-1093c6c1e5cf491f995c3ac7b38b9574\"
       }', 'user', '2021-05-08 13:45:10.414', 'user', '2021-05-08 13:45:10.414'),
       (48, 'ZV6XKLRF9L2RL', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"b0e4f317a8e747ed880abdf5354c6055\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-73578dad17104460a5e1760e78293661\"
       }', 'user', '2021-05-08 13:56:11.040', 'user', '2021-05-08 13:56:11.040'),
       (49, 'W2KBVVAN7B8KE', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"1c37c146a8394bb797923a511077f44f\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-5c2864bafb9a4a18a3aa3cb746622334\"
       }', 'user', '2021-05-08 13:56:16.600', 'user', '2021-05-08 13:56:16.600'),
       (50, '6P6P6RZGXZGV4', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"1bf528cceb5a494a93e914eeeb8f6324\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-31be2f34c8804f4fae478324032c02ac\"
       }', 'user', '2021-05-08 13:56:20.679', 'user', '2021-05-08 13:56:20.679'),
       (51, 'SKB687W9B5K94', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"bba012003d264ba695948f2f3efbaf41\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-30de57cb21d54254b686311d52f43c34\"
       }', 'user', '2021-05-08 13:56:54.039', 'user', '2021-05-08 13:56:54.039'),
       (52, 'DSY3FTNN879DG', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"9f50236ae21c4ec68272f18cea8e1a11\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-73b5714102ac4348809de24f5f411b67\"
       }', 'user', '2021-05-08 13:56:58.216', 'user', '2021-05-08 13:56:58.216'),
       (53, '8DLHEQL92PCRL', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"8db76e22c804443d85bc83a9cc0a3ba4\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-f022d08f833649a8a6ed38b4145b5b2f\"
       }', 'user', '2021-05-08 14:02:28.959', 'user', '2021-05-08 14:02:28.959'),
       (54, 'PLTN7LLAJ644W', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"7a9d08b0527d45a0b6c77674197458ae\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-f8be0f8581764976b7a8b90360692157\"
       }', 'user', '2021-05-08 14:02:48.491', 'user', '2021-05-08 14:02:48.491'),
       (55, 'KUSXXJECGHM78', 1.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"fed4d5e4eb22443eb30971c2cccc2962\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-dc40488696724a8181550af1adb02745\"
       }', 'user', '2021-05-08 14:03:00.596', 'user', '2021-05-08 14:03:00.596'),
       (56, 'XK7V4AZ2QFBP6', 200.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"37cda004a57d4b49a5fc91305e265e46\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-6d36fb341c124828a77f55573caa1607\"
       }', 'user', '2021-05-08 14:30:47.603', 'user', '2021-05-08 14:30:47.603'),
       (57, '09834912R7681045U', 3.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=09834912R7681045U\"
       }', 'user', '2021-05-08 14:44:55.049', 'user', '2021-05-08 14:44:55.049'),
       (58, '09834912R7681045U', 3.00000, 'USD', 'COMPLETED', 0.39, '{
         \"payer\": {}
       }', 'user', '2021-05-08 14:50:03.180', 'user', '2021-05-08 14:50:03.210'),
       (59, '21Y406649Y774154D', 8.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=21Y406649Y774154D\"
       }', 'user', '2021-05-08 14:52:39.105', 'user', '2021-05-08 14:52:39.105'),
       (60, '21Y406649Y774154D', 8.00000, 'USD', 'COMPLETED', 0.53, '{
         \"payer\": {}
       }', 'user', '2021-05-08 14:52:59.515', 'user', '2021-05-08 14:52:59.541'),
       (61, '1VB10622FR641414R', 8.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=1VB10622FR641414R\"
       }', 'user', '2021-05-08 14:54:29.545', 'user', '2021-05-08 14:54:29.545'),
       (62, '1VB10622FR641414R', 8.00000, 'USD', 'COMPLETED', 0.53, '{
         \"payer\": {}
       }', 'user', '2021-05-08 14:54:47.475', 'user', '2021-05-08 14:54:47.505'),
       (63, '7G539787LT512111V', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=7G539787LT512111V\"
       }', 'user', '2021-05-08 15:20:47.447', 'user', '2021-05-08 15:20:47.447'),
       (64, '7G539787LT512111V', 1.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-05-08 15:20:47.716', 'user',
        '2021-05-08 15:20:47.716'),
       (65, '5T821630NF035125D', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=5T821630NF035125D\"
       }', 'user', '2021-05-08 16:23:02.074', 'user', '2021-05-08 16:23:02.074'),
       (66, '5T821630NF035125D', 1.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-05-08 16:23:02.380', 'user',
        '2021-05-08 16:23:02.380'),
       (67, '0W049231093428145', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=0W049231093428145\"
       }', 'user', '2021-05-08 16:23:05.669', 'user', '2021-05-08 16:23:05.669'),
       (68, '0W049231093428145', 1.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-05-08 16:23:14.207', 'user',
        '2021-05-08 16:23:14.207'),
       (69, '9CN43079MJ131781U', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=9CN43079MJ131781U\"
       }', 'user', '2021-05-09 13:34:12.952', 'user', '2021-05-09 13:34:12.952'),
       (70, '9CN43079MJ131781U', 4.00000, 'USD', 'COMPLETED', 0.42, '{
         \"payer\": {}
       }', 'user', '2021-05-09 13:34:36.117', 'user', '2021-05-09 13:34:36.223'),
       (71, '98D573402E346592H', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=98D573402E346592H\"
       }', 'user', '2021-05-09 13:34:45.402', 'user', '2021-05-09 13:34:45.402'),
       (72, '98D573402E346592H', 4.00000, 'USD', 'COMPLETED', 0.42, '{
         \"payer\": {}
       }', 'user', '2021-05-09 13:35:03.125', 'user', '2021-05-09 13:35:03.161'),
       (73, '0YC95539S3196542D', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=0YC95539S3196542D\"
       }', 'user', '2021-05-09 14:28:49.900', 'user', '2021-05-09 14:28:49.900'),
       (74, '0YC95539S3196542D', 1.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-05-09 14:28:52.216', 'user',
        '2021-05-09 14:28:52.216'),
       (75, '9Y446576XF3854119', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=9Y446576XF3854119\"
       }', 'user', '2021-05-15 15:14:30.662', 'user', '2021-05-15 15:14:30.662'),
       (76, '9Y446576XF3854119', 1.00000, 'USD', 'COMPLETED', 0.33, '{
         \"payer\": {}
       }', 'user', '2021-05-15 15:15:00.133', 'user', '2021-05-15 15:15:00.182'),
       (77, '2SU2059479612045G', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=2SU2059479612045G\"
       }', 'user', '2021-05-16 14:13:20.517', 'user', '2021-05-16 14:13:20.517'),
       (78, '2SU2059479612045G', 4.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-05-16 14:13:42.227', 'user',
        '2021-05-16 14:13:42.227'),
       (79, '0RC44529G5119745E', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=0RC44529G5119745E\"
       }', 'user', '2021-05-16 14:13:44.949', 'user', '2021-05-16 14:13:44.949'),
       (80, '0RC44529G5119745E', 4.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-05-16 14:13:51.003', 'user',
        '2021-05-16 14:13:51.003'),
       (81, '2FP34663GG183870P', 10.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=2FP34663GG183870P\"
       }', 'user', '2021-05-17 16:34:17.975', 'user', '2021-05-17 16:34:17.975'),
       (82, '2FP34663GG183870P', 10.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-05-17 16:35:08.120', 'user',
        '2021-05-17 16:35:08.120'),
       (83, '1CA25583EA2667055', 10.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=1CA25583EA2667055\"
       }', 'user', '2021-05-17 16:35:28.529', 'user', '2021-05-17 16:35:28.529'),
       (84, '1CA25583EA2667055', 10.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-05-17 16:35:33.047', 'user',
        '2021-05-17 16:35:33.047'),
       (85, '4LJ79711994852247', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=4LJ79711994852247\"
       }', 'user', '2021-05-17 16:46:02.966', 'user', '2021-05-17 16:46:02.966'),
       (86, '4LJ79711994852247', 1.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-05-17 16:46:21.543', 'user',
        '2021-05-17 16:46:21.543'),
       (87, '2MV29501PF0475034', 5.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=2MV29501PF0475034\"
       }', 'user', '2021-05-19 11:04:29.117', 'user', '2021-05-19 11:04:29.117'),
       (88, '2MV29501PF0475034', 5.00000, 'USD', 'COMPLETED', 0.45, '{
         \"payer\": {}
       }', 'user', '2021-05-19 11:04:53.391', 'user', '2021-05-19 11:04:53.428'),
       (89, 'VDPG2Z3ZTDUUG', 100.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"09b0a88e8ffd42b4a5cbe043917f3fdd\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-4f0dd36fc7894c9186ef7bb8c3c2b508\"
       }', 'user', '2021-06-01 14:40:27.462', 'user', '2021-06-01 14:40:27.462'),
       (90, '6YLKU3CQRD666', 110.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"284af268754a46cb8b17eac0f230461c\",
         \"receiverEmail\": \"ninhthuan1999-buyer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-34b7481fc70b406d8aaa9cc3ae013547\"
       }', 'user', '2021-06-01 14:41:42.814', 'user', '2021-06-01 14:41:42.814'),
       (91, '37858138MN375522M', 2.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=37858138MN375522M\"
       }', 'user', '2021-06-01 15:35:53.919', 'user', '2021-06-01 15:35:53.919'),
       (92, '37858138MN375522M', 2.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-06-01 15:36:05.497', 'user',
        '2021-06-01 15:36:05.497'),
       (93, '5YC259586P643801X', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=5YC259586P643801X\"
       }', 'user', '2021-06-01 15:38:56.351', 'user', '2021-06-01 15:38:56.351'),
       (94, '5YC259586P643801X', 4.00000, 'USD', 'COMPLETED', 0.42, '{
         \"payer\": {}
       }', 'user', '2021-06-01 15:39:18.350', 'user', '2021-06-01 15:39:18.369'),
       (95, '87BFN8N4C5ARC', 2.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"8dd83bdf22b748abb0898348da1a0d78\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-3715e19d13824526a137bc08bb7589ab\"
       }', 'user', '2021-06-01 15:41:29.191', 'user', '2021-06-01 15:41:29.191'),
       (96, 'PKQM4BBX9ZL9A', 6.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"9d1e253f3dc84431ae7a87c4b5cb24b5\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmai.com\",
         \"senderBatchId\": \"BATCH_ID_HB-4bbecefa359a49e78d93e6a128b52f2c\"
       }', 'user', '2021-06-01 15:43:11.028', 'user', '2021-06-01 15:43:11.028'),
       (97, 'LXAJX9LCNCF7W', 3.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"273d790cf99240d2bc92b58d89c4963b\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-653625b555a04c9ea3601209f59927a9\"
       }', 'user', '2021-06-01 15:47:24.610', 'user', '2021-06-01 15:47:24.610'),
       (98, '027605058L6410600', 3.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=027605058L6410600\"
       }', 'user', '2021-06-01 15:47:48.307', 'user', '2021-06-01 15:47:48.307'),
       (99, '027605058L6410600', 3.00000, 'USD', 'COMPLETED', 0.39, '{
         \"payer\": {}
       }', 'user', '2021-06-01 15:49:19.391', 'user', '2021-06-01 15:49:19.426'),
       (100, '93V57186H4482693Y', 3.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=93V57186H4482693Y\"
       }', 'user', '2021-06-01 15:49:50.245', 'user', '2021-06-01 15:49:50.245'),
       (101, '93V57186H4482693Y', 3.00000, 'USD', 'COMPLETED', 0.39, '{
         \"payer\": {}
       }', 'user', '2021-06-01 15:50:11.888', 'user', '2021-06-01 15:50:11.907'),
       (102, '536493914N230445L', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=536493914N230445L\"
       }', 'user', '2021-06-01 15:55:55.738', 'user', '2021-06-01 15:55:55.738'),
       (103, '536493914N230445L', 4.00000, 'USD', 'COMPLETED', 0.42, '{
         \"payer\": {}
       }', 'user', '2021-06-01 15:56:23.273', 'user', '2021-06-01 15:56:23.286'),
       (104, '2LX12858US1235458', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=2LX12858US1235458\"
       }', 'user', '2021-06-05 14:37:14.500', 'user', '2021-06-05 14:37:14.500'),
       (105, '2LX12858US1235458', 4.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-06-05 14:37:18.025', 'user',
        '2021-06-05 14:37:18.025'),
       (106, '5VF61925CN396553G', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=5VF61925CN396553G\"
       }', 'user', '2021-06-05 14:37:21.361', 'user', '2021-06-05 14:37:21.361'),
       (107, '5VF61925CN396553G', 4.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-06-05 14:38:04.099', 'user',
        '2021-06-05 14:38:04.099'),
       (108, '25093980W58005549', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=25093980W58005549\"
       }', 'user', '2021-06-05 14:38:38.372', 'user', '2021-06-05 14:38:38.372'),
       (109, '25093980W58005549', 4.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-06-05 14:38:40.880', 'user',
        '2021-06-05 14:38:40.880'),
       (110, '0KY151029G247123F', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=0KY151029G247123F\"
       }', 'user', '2021-06-05 14:38:46.228', 'user', '2021-06-05 14:38:46.228'),
       (111, '0KY151029G247123F', 4.00000, 'USD', 'COMPLETED', 0.42, '{
         \"payer\": {}
       }', 'user', '2021-06-05 14:39:19.087', 'user', '2021-06-05 14:39:19.100'),
       (112, '2A804685SH296745Y', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=2A804685SH296745Y\"
       }', 'user', '2021-06-05 14:40:57.229', 'user', '2021-06-05 14:40:57.229'),
       (113, '2A804685SH296745Y', 1.00000, 'USD', 'COMPLETED', 0.33, '{
         \"payer\": {}
       }', 'user', '2021-06-05 14:41:31.111', 'user', '2021-06-05 14:41:31.128'),
       (114, 'RBB5BT7SVTV3Y', 4.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"c7c8a3bdd11a410487c68b714afaeb68\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-75769c3801e14399bcd8574768f4187d\"
       }', 'user', '2021-06-06 08:00:49.129', 'user', '2021-06-06 08:00:49.129'),
       (115, '8TP535933X294105Y', 1.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=8TP535933X294105Y\"
       }', 'user', '2021-06-06 08:00:54.368', 'user', '2021-06-06 08:00:54.368'),
       (116, '8TP535933X294105Y', 1.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-06-06 08:02:24.326', 'user',
        '2021-06-06 08:02:24.326'),
       (117, '6LU1365940200100S', 8.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=6LU1365940200100S\"
       }', 'user', '2021-06-06 08:02:49.376', 'user', '2021-06-06 08:02:49.376'),
       (118, '6LU1365940200100S', 8.00000, 'USD', 'COMPLETED', 0.53, '{
         \"payer\": {}
       }', 'user', '2021-06-06 08:03:09.357', 'user', '2021-06-06 08:03:09.380'),
       (119, '35656714GC134471R', 6.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=35656714GC134471R\"
       }', 'user', '2021-06-06 08:03:18.433', 'user', '2021-06-06 08:03:18.433'),
       (120, '35656714GC134471R', 6.00000, 'USD', 'COMPLETED', 0.47, '{
         \"payer\": {}
       }', 'user', '2021-06-06 08:03:46.116', 'user', '2021-06-06 08:03:46.136'),
       (121, 'X97SFENN9S4DU', 8.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"29f1c702628d4cf792e9b2a0cd3653d4\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-e7ed79380d244d41a31a8dff1b5dfebd\"
       }', 'user', '2021-06-06 08:03:56.258', 'user', '2021-06-06 08:03:56.258'),
       (122, 'C8F5N99PUSX5L', 9.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"1491943679b143c2986e6e67ac92780b\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-792c6843c98c4b8fa0d96c7cb7b31590\"
       }', 'user', '2021-06-06 08:12:20.236', 'user', '2021-06-06 08:12:20.236'),
       (123, 'UAJNHSNXQWRDL', 9.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"53c0d7876ce8422990d1f837e5d5ba5a\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-dd8ebee4211047568892916dfd966a97\"
       }', 'user', '2021-06-06 08:12:40.317', 'user', '2021-06-06 08:12:40.317'),
       (124, 'VKYNJ9B8CSFHS', 3.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"855d80f36e20423283930de7d4314144\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-91a185c08b4540e3870b07217435649e\"
       }', 'user', '2021-06-06 08:14:38.620', 'user', '2021-06-06 08:14:38.620'),
       (125, 'SXBM73FRZNQJ4', 5.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"983e01e0b7254f81947f3856d12c8288\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-702f6aab999a430d8da980a8a15b5dc0\"
       }', 'user', '2021-06-06 08:15:12.540', 'user', '2021-06-06 08:15:12.540'),
       (126, 'QF4CSMVFEUCFG', 9.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"254f56415b2045c98b6b80df2d44842a\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-036b33f9e8ec4f62915afc0d98edd08a\"
       }', 'user', '2021-06-06 08:15:17.608', 'user', '2021-06-06 08:15:17.608'),
       (127, '1YM69059HM952214U', 9.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=1YM69059HM952214U\"
       }', 'user', '2021-06-06 08:15:28.270', 'user', '2021-06-06 08:15:28.270'),
       (128, '1YM69059HM952214U', 9.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-06-06 08:15:50.319', 'user',
        '2021-06-06 08:15:50.319'),
       (129, 'PTNL7MQLLH4UN', 8.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"420542faf44b493f9fa26b069b0a22ee\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-5c0271dc021d480bbcb71dfb66929653\"
       }', 'user', '2021-06-06 08:39:50.421', 'user', '2021-06-06 08:39:50.421'),
       (130, 'ECY6VN8QUN8F2', 9.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"cd401c736c1a4cf0abaae8a057a37a5e\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-64835de2ae8d4862ba1c2d92cf333bc1\"
       }', 'user', '2021-06-06 08:40:06.617', 'user', '2021-06-06 08:40:06.617'),
       (131, '7VY75213F2476763C', 10.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=7VY75213F2476763C\"
       }', 'user', '2021-06-07 08:42:15.972', 'user', '2021-06-07 08:42:15.972'),
       (132, '7VY75213F2476763C', 10.00000, 'USD', 'CANCELED', NULL, 'null', 'user', '2021-06-07 08:44:08.835', 'user',
        '2021-06-07 08:44:08.835'),
       (133, '0TK26237BF4275340', 5.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=0TK26237BF4275340\"
       }', 'user', '2021-06-08 03:28:07.151', 'user', '2021-06-08 03:28:07.151'),
       (134, '0TK26237BF4275340', 5.00000, 'USD', 'COMPLETED', 0.45, '{
         \"payer\": {}
       }', 'user', '2021-06-08 03:30:47.699', 'user', '2021-06-08 03:30:47.789'),
       (135, '9WW869076T793761J', 4.00000, 'USD', 'CREATED', NULL, '{
         \"redirect_url\": \"https://www.sandbox.paypal.com/checkoutnow?token=9WW869076T793761J\"
       }', 'user', '2021-06-13 16:18:23.779', 'user', '2021-06-13 16:18:23.779'),
       (136, '9WW869076T793761J', 4.00000, 'USD', 'COMPLETED', 0.42, '{
         \"payer\": {}
       }', 'user', '2021-06-13 16:18:47.873', 'user', '2021-06-13 16:18:47.941'),
       (137, 'LTXRHRZHWRB2E', 3.00000, 'USD', 'PENDING', 0.25, '{
         \"senderItemId\": \"acb68ea06534483880d98f20acce2f12\",
         \"receiverEmail\": \"ninhthuan1999-payer1@gmail.com\",
         \"senderBatchId\": \"BATCH_ID_HB-70f9aa6b4f364be5ac08d35ce3efdb84\"
       }', 'user', '2021-06-13 16:18:55.628', 'user', '2021-06-13 16:18:55.628');
/*!40000 ALTER TABLE `hb_third_party_transactions`
    ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `hb_system_transactions`
--

DROP TABLE IF EXISTS `hb_system_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hb_system_transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sender_transaction_id` bigint NOT NULL,
  `receiver_transaction_id` bigint DEFAULT NULL,
  `third_party_transaction_id` bigint DEFAULT NULL,
  `amount` decimal(18,5) NOT NULL,
  `status` varchar(50) NOT NULL,
  `fee_rate` float NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_system_transaction_sender_transaction_id` (`sender_transaction_id`),
  KEY `idx_system_transaction_receiver_transaction_id` (`receiver_transaction_id`),
  KEY `idx_system_transaction_third_party_transaction` (`third_party_transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hb_system_transactions`
--

LOCK TABLES `hb_system_transactions` WRITE;
/*!40000 ALTER TABLE `hb_system_transactions` DISABLE KEYS */;
INSERT INTO `hb_system_transactions` VALUES (1,2,2,3,100.00000,'CREATED',0,'user','2021-04-18 15:50:07.411','user','2021-04-18 15:50:07.411'),(2,3,3,4,100.00000,'CREATED',0,'user','2021-04-18 15:55:49.051','user','2021-04-18 15:55:49.051'),(3,4,4,5,100.00000,'CREATED',0,'user','2021-04-19 01:53:13.712','user','2021-04-19 01:53:13.712'),(4,5,5,6,100.00000,'CREATED',0,'user','2021-04-19 02:01:52.246','user','2021-04-19 02:01:52.246'),(5,6,6,7,100.00000,'CREATED',0,'user','2021-04-19 02:08:58.670','user','2021-04-19 02:08:58.670'),(6,7,7,9,100.00000,'CREATED',0,'user','2021-04-19 02:19:20.736','user','2021-04-19 02:19:20.736'),(7,7,7,10,100.00000,'FINISHED',0,'user','2021-04-19 02:24:23.455','user','2021-04-19 02:24:23.455'),(8,8,8,11,100.00000,'CREATED',0,'user','2021-04-19 02:26:45.236','user','2021-04-19 02:26:45.236'),(9,8,8,12,100.00000,'FINISHED',0,'user','2021-04-19 02:28:19.188','user','2021-04-19 02:28:19.188'),(10,9,9,13,100.00000,'CREATED',0,'user','2021-04-19 02:31:04.023','user','2021-04-19 02:31:04.023'),(11,10,10,14,100.00000,'CREATED',0,'user','2021-04-19 03:07:09.335','user','2021-04-19 03:07:09.335'),(12,10,10,15,100.00000,'FINISHED',0,'user','2021-04-19 03:12:49.033','user','2021-04-19 03:12:49.033'),(13,11,11,16,100.00000,'CREATED',0,'user','2021-04-19 03:49:36.291','user','2021-04-19 03:49:36.291'),(14,11,11,17,100.00000,'FINISHED',0,'user','2021-04-19 03:51:01.511','user','2021-04-19 03:51:01.511'),(15,12,12,18,100.00000,'CREATED',0,'user','2021-04-19 14:42:35.983','user','2021-04-19 14:42:35.983'),(16,13,13,19,100.00000,'CREATED',0,'user','2021-04-19 14:48:12.497','user','2021-04-19 14:48:12.497'),(17,14,14,20,100.00000,'CREATED',0,'user','2021-04-19 14:50:56.285','user','2021-04-19 14:50:56.285'),(18,15,15,21,100.00000,'CREATED',0,'user','2021-04-19 14:51:02.309','user','2021-04-19 14:51:02.309'),(19,16,16,22,100.00000,'CREATED',0,'user','2021-04-19 14:51:40.250','user','2021-04-19 14:51:40.250'),(20,17,17,23,100.00000,'CREATED',0,'user','2021-04-19 14:52:23.676','user','2021-04-19 14:52:23.676'),(21,18,18,24,100.00000,'CREATED',0,'user','2021-04-19 14:52:56.473','user','2021-04-19 14:52:56.473'),(22,19,19,25,100.00000,'CREATED',0,'user','2021-04-19 14:53:39.581','user','2021-04-19 14:53:39.581'),(24,26,27,NULL,1.00000,'FINISHED',0,'user','2021-04-22 07:20:07.439','user','2021-04-22 07:20:07.439'),(25,28,29,NULL,1.00000,'FINISHED',0,'user','2021-04-22 07:21:21.234','user','2021-04-22 07:21:21.234'),(26,30,30,32,1.00000,'IN_PROGRESS',0,'user','2021-04-23 04:31:17.001','user','2021-04-23 04:31:17.001'),(27,31,31,33,1.00000,'IN_PROGRESS',0,'user','2021-04-23 04:33:07.862','user','2021-04-23 04:33:07.862'),(28,32,32,34,1.00000,'IN_PROGRESS',0,'user','2021-04-23 06:44:48.024','user','2021-04-23 06:44:48.024'),(29,33,33,35,1.00000,'IN_PROGRESS',0,'user','2021-04-23 06:48:57.081','user','2021-04-23 06:48:57.081'),(30,34,34,36,1.00000,'IN_PROGRESS',0,'user','2021-04-23 06:51:21.713','user','2021-04-23 06:51:21.713'),(31,35,35,37,1.00000,'IN_PROGRESS',0,'user','2021-04-23 06:54:04.945','user','2021-04-23 06:54:04.945'),(32,36,36,38,1.00000,'CREATED',0,'user','2021-04-25 09:26:24.848','user','2021-04-25 09:26:24.848'),(33,37,37,39,1.00000,'CREATED',0,'user','2021-04-25 09:27:14.317','user','2021-04-25 09:27:14.317'),(34,38,38,40,1.00000,'CREATED',0,'user','2021-04-25 09:28:16.097','user','2021-04-25 09:28:16.097'),(35,39,39,41,1.00000,'CREATED',0,'user','2021-04-25 09:30:00.737','user','2021-04-25 09:30:00.737'),(36,40,40,42,100.00000,'CREATED',0,'user','2021-04-26 10:03:20.187','user','2021-04-26 10:03:20.187'),(37,40,40,43,100.00000,'CANCELED',0,'user','2021-04-26 10:03:29.160','user','2021-04-26 10:03:29.160'),(38,41,42,NULL,10.00000,'FINISHED',0,'user','2021-05-07 07:23:38.640','user','2021-05-07 07:23:38.640'),(39,43,44,NULL,30.00000,'FINISHED',0,'user','2021-05-07 07:24:53.438','user','2021-05-07 07:24:53.438'),(40,45,46,NULL,1.00000,'FINISHED',0,'user','2021-05-07 07:25:29.676','user','2021-05-07 07:25:29.676'),(41,47,48,NULL,2.00000,'FINISHED',0,'user','2021-05-07 07:39:15.777','user','2021-05-07 07:39:15.777'),(42,49,50,NULL,3.00000,'FINISHED',0,'user','2021-05-07 07:40:05.013','user','2021-05-07 07:40:05.013'),(43,51,52,NULL,1.00000,'FINISHED',0,'user','2021-05-07 07:40:20.257','user','2021-05-07 07:40:20.257'),(44,53,54,NULL,3.00000,'FINISHED',0,'user','2021-05-07 07:40:30.871','user','2021-05-07 07:40:30.871'),(45,55,56,NULL,4.00000,'FINISHED',0,'user','2021-05-07 07:40:42.256','user','2021-05-07 07:40:42.256'),(46,57,58,NULL,3.00000,'FINISHED',0,'user','2021-05-07 07:41:26.623','user','2021-05-07 07:41:26.623'),(47,59,60,NULL,3.00000,'FINISHED',0,'user','2021-05-07 07:42:36.876','user','2021-05-07 07:42:36.876'),(48,61,62,NULL,3.00000,'FINISHED',0,'user','2021-05-07 07:47:18.354','user','2021-05-07 07:47:18.354'),(49,63,64,NULL,3.00000,'FINISHED',0,'user','2021-05-07 07:48:26.344','user','2021-05-07 07:48:26.344'),(50,65,66,NULL,2.00000,'FINISHED',0,'user','2021-05-07 07:49:32.590','user','2021-05-07 07:49:32.590'),(51,67,68,NULL,11.00000,'FINISHED',0,'user','2021-05-07 07:49:40.627','user','2021-05-07 07:49:40.627'),(52,69,70,NULL,3.00000,'FINISHED',0,'user','2021-05-07 07:50:27.795','user','2021-05-07 07:50:27.795'),(53,71,72,NULL,1.00000,'FINISHED',0,'user','2021-05-07 07:52:52.128','user','2021-05-07 07:52:52.128'),(54,73,74,NULL,1.00000,'FINISHED',0,'user','2021-05-07 07:53:00.596','user','2021-05-07 07:53:00.596'),(55,75,76,NULL,1.00000,'FINISHED',0,'user','2021-05-07 07:53:07.673','user','2021-05-07 07:53:07.673'),(56,77,78,NULL,1.00000,'FINISHED',0,'user','2021-05-07 13:23:44.300','user','2021-05-07 13:23:44.300'),(57,79,79,44,1.00000,'IN_PROGRESS',0,'user','2021-05-08 13:44:16.279','user','2021-05-08 13:44:16.279'),(58,80,80,45,1.00000,'IN_PROGRESS',0,'user','2021-05-08 13:44:53.279','user','2021-05-08 13:44:53.279'),(59,81,81,46,1.00000,'IN_PROGRESS',0,'user','2021-05-08 13:45:03.019','user','2021-05-08 13:45:03.019'),(60,82,82,47,1.00000,'IN_PROGRESS',0,'user','2021-05-08 13:45:10.437','user','2021-05-08 13:45:10.437'),(61,83,83,48,1.00000,'IN_PROGRESS',0,'user','2021-05-08 13:56:11.267','user','2021-05-08 13:56:11.267'),(62,84,84,49,1.00000,'IN_PROGRESS',0,'user','2021-05-08 13:56:16.621','user','2021-05-08 13:56:16.621'),(63,85,85,50,1.00000,'IN_PROGRESS',0,'user','2021-05-08 13:56:20.696','user','2021-05-08 13:56:20.696'),(64,86,86,51,1.00000,'IN_PROGRESS',0,'user','2021-05-08 13:56:54.057','user','2021-05-08 13:56:54.057'),(65,87,87,52,1.00000,'IN_PROGRESS',0,'user','2021-05-08 13:56:58.244','user','2021-05-08 13:56:58.244'),(66,88,88,53,1.00000,'IN_PROGRESS',0,'user','2021-05-08 14:02:28.977','user','2021-05-08 14:02:28.977'),(67,89,89,54,1.00000,'IN_PROGRESS',0,'user','2021-05-08 14:02:48.507','user','2021-05-08 14:02:48.507'),(68,90,90,55,1.00000,'IN_PROGRESS',0,'user','2021-05-08 14:03:00.616','user','2021-05-08 14:03:00.616'),(69,91,91,56,200.00000,'IN_PROGRESS',0,'user','2021-05-08 14:30:47.819','user','2021-05-08 14:30:47.819'),(70,92,92,57,3.00000,'CREATED',0,'user','2021-05-08 14:44:55.065','user','2021-05-08 14:44:55.065'),(71,92,92,58,3.00000,'FINISHED',0,'user','2021-05-08 14:50:03.199','user','2021-05-08 14:50:03.199'),(72,93,93,59,8.00000,'CREATED',0,'user','2021-05-08 14:52:39.117','user','2021-05-08 14:52:39.117'),(73,93,93,60,8.00000,'FINISHED',0,'user','2021-05-08 14:52:59.526','user','2021-05-08 14:52:59.526'),(74,94,94,61,8.00000,'CREATED',0,'user','2021-05-08 14:54:29.562','user','2021-05-08 14:54:29.562'),(75,94,94,62,8.00000,'FINISHED',0,'user','2021-05-08 14:54:47.497','user','2021-05-08 14:54:47.497'),(76,95,95,63,1.00000,'CREATED',0,'user','2021-05-08 15:20:47.547','user','2021-05-08 15:20:47.547'),(77,95,95,64,1.00000,'CANCELED',0,'user','2021-05-08 15:20:47.732','user','2021-05-08 15:20:47.732'),(78,96,96,65,1.00000,'CREATED',0,'user','2021-05-08 16:23:02.211','user','2021-05-08 16:23:02.211'),(79,96,96,66,1.00000,'CANCELED',0,'user','2021-05-08 16:23:02.405','user','2021-05-08 16:23:02.405'),(80,97,97,67,1.00000,'CREATED',0,'user','2021-05-08 16:23:05.692','user','2021-05-08 16:23:05.692'),(81,97,97,68,1.00000,'CANCELED',0,'user','2021-05-08 16:23:14.216','user','2021-05-08 16:23:14.216'),(82,98,98,69,4.00000,'CREATED',0,'user','2021-05-09 13:34:13.283','user','2021-05-09 13:34:13.283'),(83,98,98,70,4.00000,'FINISHED',0,'user','2021-05-09 13:34:36.186','user','2021-05-09 13:34:36.186'),(84,99,99,71,4.00000,'CREATED',0,'user','2021-05-09 13:34:45.423','user','2021-05-09 13:34:45.423'),(85,99,99,72,4.00000,'FINISHED',0,'user','2021-05-09 13:35:03.143','user','2021-05-09 13:35:03.143'),(86,100,100,73,1.00000,'CREATED',0,'user','2021-05-09 14:28:49.911','user','2021-05-09 14:28:49.911'),(87,100,100,74,1.00000,'CANCELED',0,'user','2021-05-09 14:28:52.224','user','2021-05-09 14:28:52.224'),(88,101,102,NULL,1.00000,'FINISHED',0,'user','2021-05-12 07:59:19.856','user','2021-05-12 07:59:19.856'),(89,103,103,75,1.00000,'CREATED',0,'user','2021-05-15 15:14:30.832','user','2021-05-15 15:14:30.832'),(90,103,103,76,1.00000,'FINISHED',0,'user','2021-05-15 15:15:00.155','user','2021-05-15 15:15:00.155'),(91,104,104,77,4.00000,'CREATED',0,'user','2021-05-16 14:13:20.609','user','2021-05-16 14:13:20.609'),(92,104,104,78,4.00000,'CANCELED',0,'user','2021-05-16 14:13:42.237','user','2021-05-16 14:13:42.237'),(93,105,105,79,4.00000,'CREATED',0,'user','2021-05-16 14:13:44.958','user','2021-05-16 14:13:44.958'),(94,105,105,80,4.00000,'CANCELED',0,'user','2021-05-16 14:13:51.012','user','2021-05-16 14:13:51.012'),(95,106,106,81,10.00000,'CREATED',0,'user','2021-05-17 16:34:18.122','user','2021-05-17 16:34:18.122'),(96,106,106,82,10.00000,'CANCELED',0,'user','2021-05-17 16:35:08.139','user','2021-05-17 16:35:08.139'),(97,107,107,83,10.00000,'CREATED',0,'user','2021-05-17 16:35:28.542','user','2021-05-17 16:35:28.542'),(98,107,107,84,10.00000,'CANCELED',0,'user','2021-05-17 16:35:33.060','user','2021-05-17 16:35:33.060'),(99,108,109,NULL,5.00000,'FINISHED',0,'user','2021-05-17 16:35:54.754','user','2021-05-17 16:35:54.754'),(100,110,110,85,1.00000,'CREATED',0,'user','2021-05-17 16:46:03.119','user','2021-05-17 16:46:03.119'),(101,110,110,86,1.00000,'CANCELED',0,'user','2021-05-17 16:46:21.576','user','2021-05-17 16:46:21.576'),(102,111,112,NULL,5.00000,'FINISHED',0,'user','2021-05-17 16:56:05.479','user','2021-05-17 16:56:05.479'),(103,113,114,NULL,5.00000,'FINISHED',0,'user','2021-05-17 16:58:10.604','user','2021-05-17 16:58:10.604'),(104,115,116,NULL,5.00000,'FINISHED',0,'user','2021-05-17 16:59:46.907','user','2021-05-17 16:59:46.907'),(105,117,118,NULL,5.00000,'FINISHED',0,'user','2021-05-17 17:03:00.731','user','2021-05-17 17:03:00.731'),(106,119,120,NULL,5.00000,'FINISHED',0,'user','2021-05-17 17:06:56.422','user','2021-05-17 17:06:56.422'),(107,121,122,NULL,5.00000,'FINISHED',0,'user','2021-05-17 17:15:44.603','user','2021-05-17 17:15:44.603'),(108,123,124,NULL,5.00000,'FINISHED',0,'user','2021-05-17 17:15:52.179','user','2021-05-17 17:15:52.179'),(109,125,126,NULL,3.00000,'FINISHED',0,'user','2021-05-17 17:16:30.941','user','2021-05-17 17:16:30.941'),(110,127,128,NULL,3.00000,'FINISHED',0,'user','2021-05-17 17:19:49.914','user','2021-05-17 17:19:49.914'),(111,129,130,NULL,-9.00000,'FINISHED',0,'user','2021-05-17 17:24:25.875','user','2021-05-17 17:24:25.875'),(112,131,132,NULL,-100000.00000,'FINISHED',0,'user','2021-05-17 17:25:05.811','user','2021-05-17 17:25:05.811'),(113,133,133,87,5.00000,'CREATED',0,'user','2021-05-19 11:04:29.149','user','2021-05-19 11:04:29.149'),(114,133,133,88,5.00000,'FINISHED',0,'user','2021-05-19 11:04:53.422','user','2021-05-19 11:04:53.422'),(115,134,135,NULL,6.00000,'FINISHED',0,'user','2021-05-26 04:38:52.668','user','2021-05-26 04:38:52.668'),(116,136,137,NULL,6.00000,'FINISHED',0,'user','2021-05-26 04:39:15.909','user','2021-05-26 04:39:15.909'),(117,138,138,89,100.00000,'IN_PROGRESS',0,'user','2021-06-01 14:40:27.574','user','2021-06-01 14:40:27.574'),(118,139,139,90,110.00000,'IN_PROGRESS',0,'user','2021-06-01 14:41:42.839','user','2021-06-01 14:41:42.839'),(119,140,140,91,2.00000,'CREATED',0,'user','2021-06-01 15:35:53.931','user','2021-06-01 15:35:53.931'),(120,140,140,92,2.00000,'CANCELED',0,'user','2021-06-01 15:36:05.502','user','2021-06-01 15:36:05.502'),(121,141,141,93,4.00000,'CREATED',0,'user','2021-06-01 15:38:56.357','user','2021-06-01 15:38:56.357'),(122,141,141,94,4.00000,'FINISHED',0,'user','2021-06-01 15:39:18.366','user','2021-06-01 15:39:18.366'),(123,142,142,95,2.00000,'IN_PROGRESS',0,'user','2021-06-01 15:41:29.199','user','2021-06-01 15:41:29.199'),(124,143,143,96,6.00000,'IN_PROGRESS',0,'user','2021-06-01 15:43:11.154','user','2021-06-01 15:43:11.154'),(125,144,144,97,3.00000,'IN_PROGRESS',0,'user','2021-06-01 15:47:24.627','user','2021-06-01 15:47:24.627'),(126,145,145,98,3.00000,'CREATED',0,'user','2021-06-01 15:47:48.314','user','2021-06-01 15:47:48.314'),(127,145,145,99,3.00000,'FINISHED',0,'user','2021-06-01 15:49:19.417','user','2021-06-01 15:49:19.417'),(128,146,146,100,3.00000,'CREATED',0,'user','2021-06-01 15:49:50.252','user','2021-06-01 15:49:50.252'),(129,146,146,101,3.00000,'FINISHED',0,'user','2021-06-01 15:50:11.901','user','2021-06-01 15:50:11.901'),(130,147,147,102,4.00000,'CREATED',0,'user','2021-06-01 15:55:55.746','user','2021-06-01 15:55:55.746'),(131,147,147,103,4.00000,'FINISHED',0,'user','2021-06-01 15:56:23.282','user','2021-06-01 15:56:23.282'),(132,148,148,104,4.00000,'CREATED',0,'user','2021-06-05 14:37:14.522','user','2021-06-05 14:37:14.522'),(133,148,148,105,4.00000,'CANCELED',0,'user','2021-06-05 14:37:18.033','user','2021-06-05 14:37:18.033'),(134,149,149,106,4.00000,'CREATED',0,'user','2021-06-05 14:37:21.367','user','2021-06-05 14:37:21.367'),(135,149,149,107,4.00000,'CANCELED',0,'user','2021-06-05 14:38:04.104','user','2021-06-05 14:38:04.104'),(136,150,150,108,4.00000,'CREATED',0,'user','2021-06-05 14:38:38.380','user','2021-06-05 14:38:38.380'),(137,150,150,109,4.00000,'CANCELED',0,'user','2021-06-05 14:38:40.887','user','2021-06-05 14:38:40.887'),(138,151,151,110,4.00000,'CREATED',0,'user','2021-06-05 14:38:46.237','user','2021-06-05 14:38:46.237'),(139,151,151,111,4.00000,'FINISHED',0,'user','2021-06-05 14:39:19.097','user','2021-06-05 14:39:19.097'),(140,152,152,112,1.00000,'CREATED',0,'user','2021-06-05 14:40:57.241','user','2021-06-05 14:40:57.241'),(141,152,152,113,1.00000,'FINISHED',0,'user','2021-06-05 14:41:31.121','user','2021-06-05 14:41:31.121'),(142,153,153,114,4.00000,'IN_PROGRESS',0,'user','2021-06-06 08:00:49.250','user','2021-06-06 08:00:49.250'),(143,154,154,115,1.00000,'CREATED',0,'user','2021-06-06 08:00:54.382','user','2021-06-06 08:00:54.382'),(144,154,154,116,1.00000,'CANCELED',0,'user','2021-06-06 08:02:24.333','user','2021-06-06 08:02:24.333'),(145,155,155,117,8.00000,'CREATED',0,'user','2021-06-06 08:02:49.385','user','2021-06-06 08:02:49.385'),(146,155,155,118,8.00000,'FINISHED',0,'user','2021-06-06 08:03:09.375','user','2021-06-06 08:03:09.375'),(147,156,156,119,6.00000,'CREATED',0,'user','2021-06-06 08:03:18.441','user','2021-06-06 08:03:18.441'),(148,156,156,120,6.00000,'FINISHED',0,'user','2021-06-06 08:03:46.130','user','2021-06-06 08:03:46.130'),(149,157,157,121,8.00000,'IN_PROGRESS',0,'user','2021-06-06 08:03:56.269','user','2021-06-06 08:03:56.269'),(150,158,158,122,9.00000,'IN_PROGRESS',0,'user','2021-06-06 08:12:20.320','user','2021-06-06 08:12:20.320'),(151,159,159,123,9.00000,'IN_PROGRESS',0,'user','2021-06-06 08:12:40.330','user','2021-06-06 08:12:40.330'),(152,160,160,124,3.00000,'IN_PROGRESS',0,'user','2021-06-06 08:14:38.637','user','2021-06-06 08:14:38.637'),(153,161,161,125,5.00000,'IN_PROGRESS',0,'user','2021-06-06 08:15:12.555','user','2021-06-06 08:15:12.555'),(154,162,162,126,9.00000,'IN_PROGRESS',0,'user','2021-06-06 08:15:17.619','user','2021-06-06 08:15:17.619'),(155,163,163,127,9.00000,'CREATED',0,'user','2021-06-06 08:15:28.285','user','2021-06-06 08:15:28.285'),(156,163,163,128,9.00000,'CANCELED',0,'user','2021-06-06 08:15:50.325','user','2021-06-06 08:15:50.325'),(157,164,164,129,8.00000,'IN_PROGRESS',0,'user','2021-06-06 08:39:50.484','user','2021-06-06 08:39:50.484'),(158,165,165,130,9.00000,'IN_PROGRESS',0,'user','2021-06-06 08:40:06.629','user','2021-06-06 08:40:06.629'),(159,166,166,131,10.00000,'CREATED',0,'user','2021-06-07 08:42:16.060','user','2021-06-07 08:42:16.060'),(160,166,166,132,10.00000,'CANCELED',0,'user','2021-06-07 08:44:08.848','user','2021-06-07 08:44:08.848'),(161,167,168,NULL,20000.00000,'FINISHED',0,'user','2021-06-07 08:46:20.990','user','2021-06-07 08:46:20.990'),(162,169,169,133,5.00000,'CREATED',0,'user','2021-06-08 03:28:07.372','user','2021-06-08 03:28:07.372'),(163,169,169,134,5.00000,'FINISHED',0,'user','2021-06-08 03:30:47.765','user','2021-06-08 03:30:47.765'),(164,170,170,135,4.00000,'CREATED',0,'user','2021-06-13 16:18:23.910','user','2021-06-13 16:18:23.910'),(165,170,170,136,4.00000,'FINISHED',0,'user','2021-06-13 16:18:47.910','user','2021-06-13 16:18:47.910'),(166,171,171,137,3.00000,'IN_PROGRESS',0,'user','2021-06-13 16:18:55.648','user','2021-06-13 16:18:55.648');
/*!40000 ALTER TABLE `hb_system_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hb_wallets`
--

DROP TABLE IF EXISTS `hb_wallets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hb_wallets` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `balance` decimal(18,5) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_wallet_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hb_wallets`
--

LOCK TABLES `hb_wallets` WRITE;
/*!40000 ALTER TABLE `hb_wallets` DISABLE KEYS */;
INSERT INTO `hb_wallets` VALUES (1,1,179521.69000,'system','2021-04-18 22:45:18.000','user','2021-06-13 16:18:55.638'),(2,2,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(3,3,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(4,4,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(5,5,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(6,6,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(7,7,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(8,8,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(9,9,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(10,10,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(11,11,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(12,12,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(13,13,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(14,14,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(15,15,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(16,16,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(17,17,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(18,18,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(19,19,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(20,20,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(21,21,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(22,22,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(23,23,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(24,24,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(25,25,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(26,26,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(27,27,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(28,28,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(29,29,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(30,30,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(31,31,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(32,32,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(33,33,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(34,34,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(35,35,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(36,36,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(37,37,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(38,38,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(39,39,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(40,40,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(41,41,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(42,42,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(43,43,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(44,44,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(45,45,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(46,46,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(47,47,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(48,48,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(49,49,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(50,50,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(51,51,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(52,52,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(53,53,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(54,54,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(55,55,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(56,56,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(57,57,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(58,58,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(59,59,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(60,60,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(61,61,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(62,62,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(63,63,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(64,64,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(65,65,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(66,66,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(67,67,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(68,68,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(69,69,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(70,70,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(71,71,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(72,72,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(73,73,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(74,74,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(75,75,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(76,76,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(77,77,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(78,78,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(79,79,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(80,80,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(81,81,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(82,82,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(83,83,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(84,84,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(85,85,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(86,86,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(87,87,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(88,88,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(89,89,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(90,90,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(91,91,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(92,92,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(93,93,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(94,94,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(95,95,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(96,96,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(97,97,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(98,98,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(99,99,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(100,100,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(101,101,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(102,102,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(103,103,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(104,104,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(105,105,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(106,106,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(107,107,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(108,108,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(109,109,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(110,110,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(111,111,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(112,112,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(113,113,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(114,114,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(115,115,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(116,116,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(117,117,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(118,118,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(119,119,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(120,120,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(121,121,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(122,122,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(123,123,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(124,124,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(125,125,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(126,126,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(127,127,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(128,128,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(129,129,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(130,130,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(131,131,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(132,132,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(133,133,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(134,134,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(135,135,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(136,136,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(137,137,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(138,138,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(139,139,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(140,140,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(141,141,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(142,142,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(143,143,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(144,144,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(145,145,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(146,146,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(147,147,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(148,148,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(149,149,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(150,150,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(151,151,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(152,152,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(153,153,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(154,154,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(155,155,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(156,156,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(157,157,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(158,158,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(159,159,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(160,160,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(161,161,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(162,162,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(163,163,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(164,164,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(165,165,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(166,166,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(167,167,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(168,168,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(169,169,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(170,170,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(171,171,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(172,172,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(173,173,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(174,174,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(175,175,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(176,176,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(177,177,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(178,178,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(179,179,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(180,180,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(181,181,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(182,182,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(183,183,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(184,184,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(185,185,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(186,186,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(187,187,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(188,188,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(189,189,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(190,190,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(191,191,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(192,192,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(193,193,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(194,194,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(195,195,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(196,196,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(197,197,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(198,198,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(199,199,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(200,200,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(201,201,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(202,202,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(203,203,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(204,204,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(205,205,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(206,206,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(207,207,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(208,208,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(209,209,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(210,210,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(211,211,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(212,212,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(213,213,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(214,214,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(215,215,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(216,216,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(217,217,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(218,218,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(219,219,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(220,220,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(221,221,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(222,222,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(223,223,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(224,224,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(225,225,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(226,226,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(227,227,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(228,228,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(229,229,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(230,230,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(231,231,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(232,232,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(233,233,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(234,234,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(235,235,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(236,236,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(237,237,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(238,238,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(239,239,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(240,240,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(241,241,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(242,242,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(243,243,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(244,244,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(245,245,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(246,246,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(247,247,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(248,248,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(249,249,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(250,250,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(251,251,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(252,252,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(253,253,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(254,254,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(255,255,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(256,256,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(257,257,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(258,258,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(259,259,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(260,260,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(261,261,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(262,262,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(263,263,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(264,264,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(265,265,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(266,266,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(267,267,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(268,268,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(269,269,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(270,270,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(271,271,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(272,272,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(273,273,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(274,274,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(275,275,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(276,276,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(277,277,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(278,278,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(279,279,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(280,280,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(281,281,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(282,282,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(283,283,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(284,284,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(285,285,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(286,286,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(287,287,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(288,288,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(289,289,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(290,290,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(291,291,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(292,292,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(293,293,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(294,294,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(295,295,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(296,296,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(297,297,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(298,298,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(299,299,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(300,300,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(301,301,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(302,302,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(303,303,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(304,304,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(305,305,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(306,306,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(307,307,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(308,308,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(309,309,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(310,310,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(311,311,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(312,312,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(313,313,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(314,314,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(315,315,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(316,316,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(317,317,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(318,318,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(319,319,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(320,320,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(321,321,85.00000,'system','2021-04-25 05:18:29.237','user','2021-05-07 07:53:07.682'),(322,322,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(323,323,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(324,324,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(325,325,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(326,326,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(327,327,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(328,328,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(329,329,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(330,330,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(331,331,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(332,332,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(333,333,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(334,334,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(335,335,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(336,336,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(337,337,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(338,338,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(339,339,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(340,340,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(341,341,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(342,342,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(343,343,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(344,344,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(345,345,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(346,346,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(347,347,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(348,348,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(349,349,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(350,350,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(351,351,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(352,352,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(353,353,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(354,354,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(355,355,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(356,356,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(357,357,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(358,358,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(359,359,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(360,360,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(361,361,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(362,362,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(363,363,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(364,364,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(365,365,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(366,366,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(367,367,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(368,368,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(369,369,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(370,370,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(371,371,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(372,372,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(373,373,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(374,374,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(375,375,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(376,376,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(377,377,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(378,378,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(379,379,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(380,380,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(381,381,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(382,382,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(383,383,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(384,384,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(385,385,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(386,386,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(387,387,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(388,388,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(389,389,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(390,390,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(391,391,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(392,392,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(393,393,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(394,394,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(395,395,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(396,396,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(397,397,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(398,398,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(399,399,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(400,400,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(401,401,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(402,402,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(403,403,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(404,404,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(405,405,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(406,406,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(407,407,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(408,408,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(409,409,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(410,410,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(411,411,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(412,412,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(413,413,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(414,414,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(415,415,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(416,416,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(417,417,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(418,418,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(419,419,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(420,420,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(421,421,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(422,422,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(423,423,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(424,424,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(425,425,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(426,426,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(427,427,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(428,428,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(429,429,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(430,430,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(431,431,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(432,432,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(433,433,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(434,434,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(435,435,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(436,436,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(437,437,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(438,438,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(439,439,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(440,440,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(441,441,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(442,442,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(443,443,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(444,444,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(445,445,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(446,446,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(447,447,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(448,448,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(449,449,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(450,450,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(451,451,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(452,452,1.00000,'system','2021-04-25 05:18:29.237','user','2021-05-12 07:59:19.938'),(453,453,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(454,454,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(455,455,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(456,456,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(457,457,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(458,458,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(459,459,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(460,460,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(461,461,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(462,462,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(463,463,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(464,464,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(465,465,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(466,466,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(467,467,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(468,468,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(469,469,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(470,470,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(471,471,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(472,472,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(473,473,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(474,474,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(475,475,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(476,476,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(477,477,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(478,478,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(479,479,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(480,480,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(481,481,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(482,482,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(483,483,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(484,484,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(485,485,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(486,486,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(487,487,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(488,488,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(489,489,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(490,490,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(491,491,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(492,492,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(493,493,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(494,494,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(495,495,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(496,496,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(497,497,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(498,498,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(499,499,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(500,500,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(501,501,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(502,502,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(503,503,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(504,504,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(505,505,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(506,506,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(507,507,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(508,508,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(509,509,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(510,510,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(511,511,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(512,512,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(513,513,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(514,514,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(515,515,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(516,516,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(517,517,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(518,518,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(519,519,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(520,520,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(521,521,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(522,522,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(523,523,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(524,524,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(525,525,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(526,526,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(527,527,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(528,528,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(529,529,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(530,530,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(531,531,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(532,532,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(533,533,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(534,534,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(535,535,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(536,536,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(537,537,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(538,538,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(539,539,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(540,540,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(541,541,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(542,542,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(543,543,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(544,544,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(545,545,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(546,546,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(547,547,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(548,548,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(549,549,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(550,550,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(551,551,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(552,552,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(553,553,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(554,554,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(555,555,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(556,556,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(557,557,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(558,558,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(559,559,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(560,560,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(561,561,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(562,562,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(563,563,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(564,564,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(565,565,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(566,566,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(567,567,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(568,568,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(569,569,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(570,570,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(571,571,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(572,572,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(573,573,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(574,574,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(575,575,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(576,576,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(577,577,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(578,578,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(579,579,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(580,580,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(581,581,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(582,582,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(583,583,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(584,584,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(585,585,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(586,586,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(587,587,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(588,588,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(589,589,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(590,590,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(591,591,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(592,592,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(593,593,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(594,594,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(595,595,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(596,596,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(597,597,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(598,598,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(599,599,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(600,600,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(601,601,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(602,602,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(603,603,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(604,604,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(605,605,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(606,606,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(607,607,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(608,608,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(609,609,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(610,610,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(611,611,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(612,612,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(613,613,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(614,614,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(615,615,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(616,616,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(617,617,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(618,618,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(619,619,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(620,620,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(621,621,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(622,622,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(623,623,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(624,624,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(625,625,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(626,626,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(627,627,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(628,628,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(629,629,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(630,630,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(631,631,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(632,632,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(633,633,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(634,634,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(635,635,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(636,636,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(637,637,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(638,638,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(639,639,12.00000,'system','2021-04-25 05:18:29.237','user','2021-05-26 04:39:15.917'),(640,640,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(641,641,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(642,642,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(643,643,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(644,644,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(645,645,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(646,646,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(647,647,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(648,648,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(649,649,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(650,650,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(651,651,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(652,652,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(653,653,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(654,654,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(655,655,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(656,656,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(657,657,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(658,658,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(659,659,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(660,660,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(661,661,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(662,662,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(663,663,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(664,664,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(665,665,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(666,666,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(667,667,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(668,668,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(669,669,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(670,670,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(671,671,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(672,672,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(673,673,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(674,674,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(675,675,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(676,676,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(677,677,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(678,678,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(679,679,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(680,680,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(681,681,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(682,682,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(683,683,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(684,684,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(685,685,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(686,686,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(687,687,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(688,688,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(689,689,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(690,690,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(691,691,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(692,692,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(693,693,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(694,694,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(695,695,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(696,696,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(697,697,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(698,698,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(699,699,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(700,700,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(701,701,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(702,702,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(703,703,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(704,704,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(705,705,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(706,706,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(707,707,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(708,708,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(709,709,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(710,710,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(711,711,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(712,712,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(713,713,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(714,714,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(715,715,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(716,716,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(717,717,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(718,718,-99963.00000,'system','2021-04-25 05:18:29.237','user','2021-05-17 17:25:05.818'),(719,719,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(720,720,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(721,721,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(722,722,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(723,723,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(724,724,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(725,725,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(726,726,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(727,727,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(728,728,1.00000,'system','2021-04-25 05:18:29.237','user','2021-05-07 13:23:44.334'),(729,729,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(730,730,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(731,731,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(732,732,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(733,733,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(734,734,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(735,735,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(736,736,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(737,737,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(738,738,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(739,739,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(740,740,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(741,741,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(742,742,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(743,743,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(744,744,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(745,745,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(746,746,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(747,747,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(748,748,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(749,749,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(750,750,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(751,751,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(752,752,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(753,753,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(754,754,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(755,755,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(756,756,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(757,757,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(758,758,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(759,759,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(760,760,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(761,761,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(762,762,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(763,763,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(764,764,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(765,765,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(766,766,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(767,767,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(768,768,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(769,769,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(770,770,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(771,771,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(772,772,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(773,773,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(774,774,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(775,775,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(776,776,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(777,777,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(778,778,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(779,779,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(780,780,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(781,781,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(782,782,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(783,783,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(784,784,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(785,785,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(786,786,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(787,787,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(788,788,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(789,789,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(790,790,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(791,791,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(792,792,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(793,793,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(794,794,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(795,795,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(796,796,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(797,797,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(798,798,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(799,799,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(800,800,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(801,801,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(802,802,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(803,803,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(804,804,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(805,805,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(806,806,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(807,807,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(808,808,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(809,809,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(810,810,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(811,811,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(812,812,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(813,813,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(814,814,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(815,815,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(816,816,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(817,817,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(818,818,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(819,819,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(820,820,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(821,821,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(822,822,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(823,823,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(824,824,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(825,825,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(826,826,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(827,827,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(828,828,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(829,829,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(830,830,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(831,831,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(832,832,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(833,833,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(834,834,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(835,835,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(836,836,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(837,837,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(838,838,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(839,839,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(840,840,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(841,841,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(842,842,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(843,843,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(844,844,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(845,845,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(846,846,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(847,847,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(848,848,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(849,849,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(850,850,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(851,851,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(852,852,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(853,853,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(854,854,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(855,855,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(856,856,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(857,857,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(858,858,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(859,859,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(860,860,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(861,861,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(862,862,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(863,863,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(864,864,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(865,865,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(866,866,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(867,867,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(868,868,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(869,869,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(870,870,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(871,871,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(872,872,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(873,873,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(874,874,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(875,875,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(876,876,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(877,877,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(878,878,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(879,879,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(880,880,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(881,881,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(882,882,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(883,883,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(884,884,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(885,885,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(886,886,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(887,887,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(888,888,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(889,889,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(890,890,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(891,891,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(892,892,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(893,893,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(894,894,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(895,895,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(896,896,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(897,897,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(898,898,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(899,899,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(900,900,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(901,901,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(902,902,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(903,903,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(904,904,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(905,905,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(906,906,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(907,907,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(908,908,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(909,909,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(910,910,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(911,911,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(912,912,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(913,913,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(914,914,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(915,915,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(916,916,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(917,917,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(918,918,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(919,919,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(920,920,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(921,921,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(922,922,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(923,923,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(924,924,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(925,925,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(926,926,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(927,927,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(928,928,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(929,929,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(930,930,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(931,931,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(932,932,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(933,933,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(934,934,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(935,935,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(936,936,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(937,937,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(938,938,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(939,939,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(940,940,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(941,941,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(942,942,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(943,943,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(944,944,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(945,945,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(946,946,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(947,947,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(948,948,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(949,949,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(950,950,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(951,951,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(952,952,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(953,953,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(954,954,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(955,955,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(956,956,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(957,957,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(958,958,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(959,959,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(960,960,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(961,961,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(962,962,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(963,963,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(964,964,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(965,965,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(966,966,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(967,967,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(968,968,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(969,969,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(970,970,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(971,971,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(972,972,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(973,973,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(974,974,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(975,975,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(976,976,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(977,977,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(978,978,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(979,979,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(980,980,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(981,981,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(982,982,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(983,983,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(984,984,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(985,985,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(986,986,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(987,987,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(988,988,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(989,989,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(990,990,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(991,991,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(992,992,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(993,993,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(994,994,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(995,995,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(996,996,20000.00000,'system','2021-04-25 05:18:29.237','user','2021-06-07 08:46:21.034'),(997,997,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(998,998,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(999,999,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL),(1000,1000,0.00000,'system','2021-04-25 05:18:29.237',NULL,NULL);
/*!40000 ALTER TABLE `hb_wallets` ENABLE KEYS */;
UNLOCK TABLES;