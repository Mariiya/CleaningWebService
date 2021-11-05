-------------------------------------------------Test data for CLEANING_SERVICE ---------------------------------------------
----------SERVICE----------
INSERT INTO SERVICE (serviceId, name, description, isCustom)
VALUES (SEQ.nextval, 'Washing dishes', 'Cleaning all dirty dishes', 0);
INSERT INTO SERVICE (serviceId, name, description, isCustom)
VALUES (SEQ.nextval, 'Sweep the floor', 'Dry cleaning with a broom of the floor surface', 0);
INSERT INTO SERVICE (serviceId, name, description, isCustom)
VALUES (SEQ.nextval, 'Wash the floor', 'Wet cleaning of the floor surface', 0);
INSERT INTO SERVICE (serviceId, name, description, isCustom)
VALUES (SEQ.nextval, 'Wipe off the dust', 'Dry cleaning bookshelves', 0);
INSERT INTO SERVICE (serviceId, name, description, isCustom)
VALUES (SEQ.nextval, 'Test', 'Test', 1);
----------USERS----------
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (SEQ.nextval, 'test1@mail.com', '12345', '+380501021010', 'ROLE_CLIENT');
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (SEQ.nextval, 'test2@mail.com', 'password', '+380930946525', 'ROLE_CLIENT');
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (SEQ.nextval, 'test3@mail.com', 'qwerty', '+380991112233', 'ROLE_SERVICE_PROVIDER');
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (SEQ.nextval, 'test4@mail.com', '12345qwerty', '+380501111111', 'ROLE_SERVICE_PROVIDER');
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (SEQ.nextval, 'test5@mail.com', 'q1w2e3r4t5y', '+380992223344', 'ROLE_SERVICE_PROVIDER');
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (SEQ.nextval, 'test6@mail.com', '1q2w3e4r5t6y', '+380665554657', 'ROLE_SERVICE_PROVIDER');
----------CONSUMER----------
INSERT INTO CONSUMER (firstName, lastName, userId)
VALUES ('Ivan', 'Ivanov', (SELECT userID FROM USERS WHERE email = 'test1@mail.com'));
INSERT INTO CONSUMER (firstName, lastName, userId)
VALUES ('Petro', 'Petrenko', (SELECT userID FROM USERS WHERE email = 'test2@mail.com'));
----------VENDOR----------
INSERT INTO VENDOR (firstName, lastName, individual, userId)
VALUES ('Test', 'Test', 1, (SELECT userID FROM USERS WHERE email = 'test3@mail.com'));
INSERT INTO VENDOR (firstName, lastName, individual, userId)
VALUES ( 'Ivasik', 'Telesik', 1, (SELECT userID FROM USERS WHERE email = 'test4@mail.com'));
INSERT INTO VENDOR (firstName, lastName, individual, userId)
VALUES ('Rich', 'Firm', 0, (SELECT userID FROM USERS WHERE email = 'test5@mail.com'));
INSERT INTO VENDOR (firstName, lastName, individual, userId)
VALUES ('Poor', 'Company', 0, (SELECT userID FROM USERS WHERE email = 'test6@mail.com'));
----------ORDERS----------
INSERT INTO ORDERS (orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (SEQ.nextval, 'test', 'test description1', 'STATUS_COMPLETED', (SELECT USERID FROM CONSUMER WHERE lastName = 'Ivanov'), (SELECT USERID FROM VENDOR WHERE lastName = 'Test'), DATE '2021-01-12', DATE '2021-01-12', 300, 'Govorova st., 12');
INSERT INTO ORDERS (orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (SEQ.nextval, 'test2', 'test description2', 'STATUS_COMPLETED', (SELECT USERID FROM CONSUMER WHERE lastName = 'Petrenko'), (SELECT USERID FROM VENDOR WHERE lastName = 'Test'), DATE '2021-10-01',DATE  '2021-06-06', 211, null);
INSERT INTO ORDERS (orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (SEQ.nextval, 'test3', 'test description3', 'STATUS_COMPLETED', (SELECT USERID FROM CONSUMER WHERE lastName = 'Ivanov'), (SELECT USERID FROM VENDOR WHERE lastName = 'Telesik'), DATE '2021-04-04', DATE '2021-04-05', 123023, 'Deribasovskaya st. 10');
INSERT INTO ORDERS (orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (SEQ.nextval, 'test4', 'test description4', 'STATUS_OPEN', (SELECT USERID FROM CONSUMER WHERE lastName = 'Petrenko'), (SELECT USERID FROM VENDOR WHERE lastName = 'Telesik'),DATE  '2021-05-08', null, 250, 'Pushkina st. 105');
INSERT INTO ORDERS (orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (SEQ.nextval, 'test5', 'test description5', 'STATUS_IN_PROGRES', (SELECT USERID FROM CONSUMER WHERE lastName = 'Ivanov'), (SELECT USERID FROM VENDOR WHERE lastName = 'Firm'), DATE '2020-12-04',  null, 100, 'Zhukova st., 10a');
INSERT INTO ORDERS (orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (SEQ.nextval, 'test6', 'test description6', 'STATUS_CANCELED', (SELECT USERID FROM CONSUMER WHERE lastName = 'Petrenko'), (SELECT USERID FROM VENDOR WHERE lastName = 'Firm'), DATE  '2021-01-02',DATE  '2021-11-10', 0, 'Preobrazhenskaya st., 43');
INSERT INTO ORDERS (orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (SEQ.nextval, 'test7', 'test description7', 'STATUS_SUSPENDED', (SELECT USERID FROM CONSUMER WHERE lastName = 'Ivanov'), (SELECT USERID FROM VENDOR WHERE lastName = 'Company'), DATE  '2020-05-03',null, 12323, 'Nebesnoyi sotni st., 145');
INSERT INTO ORDERS (orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (SEQ.nextval, 'test8', 'test description8', 'STATUS_COMPLETED', (SELECT USERID FROM CONSUMER WHERE lastName = 'Petrenko'), (SELECT USERID FROM VENDOR WHERE lastName = 'Company'), DATE  '2021-10-10',DATE  '2021-10-10', 898, 'test');
----------SERVICECOLLECTION----------
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test5'), (SELECT serviceID FROM SERVICE WHERE name = 'Washing dishes'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test5'), (SELECT serviceID FROM SERVICE WHERE name = 'Sweep the floor'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test5'), (SELECT serviceID FROM SERVICE WHERE name = 'Wash the floor'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test5'), (SELECT serviceID FROM SERVICE WHERE name = 'Wipe off the dust'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test'), (SELECT serviceID FROM SERVICE WHERE name = 'Washing dishes'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test2'), (SELECT serviceID FROM SERVICE WHERE name = 'Sweep the floor'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test3'), (SELECT serviceID FROM SERVICE WHERE name = 'Wash the floor'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test4'), (SELECT serviceID FROM SERVICE WHERE name = 'Wipe off the dust'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test6'), (SELECT serviceID FROM SERVICE WHERE name = 'Washing dishes'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test6'), (SELECT serviceID FROM SERVICE WHERE name = 'Sweep the floor'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test7'), (SELECT serviceID FROM SERVICE WHERE name = 'Wash the floor'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test7'), (SELECT serviceID FROM SERVICE WHERE name = 'Wipe off the dust'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test7'), (SELECT serviceID FROM SERVICE WHERE name = 'Washing dishes'));
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (SEQ.nextval, (SELECT orderID FROM ORDERS WHERE title = 'test8'), (SELECT serviceID FROM SERVICE WHERE name = 'Washing dishes'));
