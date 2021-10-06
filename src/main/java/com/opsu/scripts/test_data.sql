-------------------------------------------------Test data for CLEANING_SERVICE ---------------------------------------------
----------SERVICE----------
INSERT INTO SERVICE (serviceId, name, description)
VALUES (obj_id_seq.nextval, 'Washing dishes', 'Cleaning all dirty dishes');
INSERT INTO SERVICE (serviceId, name, description)
VALUES (obj_id_seq.nextval, 'Sweep the floor', 'Dry cleaning with a broom of the floor surface');
INSERT INTO SERVICE (serviceId, name, description)
VALUES (obj_id_seq.nextval, 'Wash the floor', 'Wet cleaning of the floor surface');
INSERT INTO SERVICE (serviceId, name, description)
VALUES (obj_id_seq.nextval, 'Wipe off the dust', 'Dry cleaning bookshelves');
----------USERS----------
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (obj_id_seq.nextval, 'test1@mail.com', '12345', '+380501021010', 'ROLE_CLIENT');
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (obj_id_seq.nextval, 'test2@mail.com', 'password', '+380930946525', 'ROLE_CLIENT');
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (obj_id_seq.nextval, 'test3@mail.com', 'qwerty', '+380991112233', 'ROLE_SERVICE_PROVIDER');
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (obj_id_seq.nextval, 'test4@mail.com', '12345qwerty', '+380501111111', 'ROLE_SERVICE_PROVIDER');
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (obj_id_seq.nextval, 'test5@mail.com', 'q1w2e3r4t5y', '+380992223344', 'ROLE_SERVICE_PROVIDER');
INSERT INTO USERS (userId, email, password, phoneNumber, role)
VALUES (obj_id_seq.nextval, 'test6@mail.com', '1q2w3e4r5t6y', '+380665554657', 'ROLE_SERVICE_PROVIDER');
----------CONSUMER----------
INSERT INTO CONSUMER (consumerId, firstName, lastName, userId)
VALUES (obj_id_seq.nextval, 'Ivan', 'Ivanov', 1);
INSERT INTO CONSUMER (consumerId, firstName, lastName, userId)
VALUES (obj_id_seq.nextval, 'Petro', 'Petrenko', 2);
----------VENDOR----------
INSERT INTO VENDOR (vendorId, firstName, lastName, individual, userId)
VALUES (obj_id_seq.nextval, 'Test', 'Test', 1, 3);
INSERT INTO VENDOR (vendorId, firstName, lastName, individual, userId)
VALUES (obj_id_seq.nextval, 'Ivasik', 'Telesik', 1, 4);
INSERT INTO VENDOR (vendorId, firstName, lastName, individual, userId)
VALUES (obj_id_seq.nextval, 'Rich', 'Firm', 0, 5);
INSERT INTO VENDOR (vendorId, firstName, lastName, individual, userId)
VALUES (obj_id_seq.nextval, 'Poor', 'Company', 0, 6);
----------ORDERS----------
INSERT INTO ORDERS (orderId, title, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (obj_id_seq.nextval, 'test', 'STATUS_COMPLETED', 1, 1, '24-04-2020', '05-05-2020', 300, 'Govorova st., 12');
INSERT INTO ORDERS (orderId, title, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (obj_id_seq.nextval, 'test2', 'STATUS_COMPLETED', 2, 1, '10-01-2021', '06-06-2021', 211, null);
INSERT INTO ORDERS (orderId, title, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (obj_id_seq.nextval, 'test3', 'STATUS_COMPLETED', 1, 2, '24-04-2021', '26-05-2021', 123023, 'Deribasovskaya st. 10');
INSERT INTO ORDERS (orderId, title, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (obj_id_seq.nextval, 'test4', 'STATUS_OPEN', 2, 2, '8-05-2021', null, 250, 'Pushkina st. 105');
INSERT INTO ORDERS (orderId, title, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (obj_id_seq.nextval, 'test5', 'STATUS_IN_PROGRES', 1, 3, '20-12-2020', null, 100, 'Zhukova st., 10a');
INSERT INTO ORDERS (orderId, title, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (obj_id_seq.nextval, 'test6', 'STATUS_CANCELED', 2, 3, '1-01-2021', '2-01-2021', 0, 'Preobrazhenskaya st., 43');
INSERT INTO ORDERS (orderId, title, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (obj_id_seq.nextval, 'test7', 'STATUS_SUSPENDED', 1, 4, '19-05-2020', null, 12323, 'Nebesnoyi sotni st., 145');
INSERT INTO ORDERS (orderId, title, status, consumerId, vendorId, startDate, endDate, price, address)
VALUES (obj_id_seq.nextval, 'test8', 'STATUS_COMPLETED', 2, 4, '10-10-2021', '10-10-2021', 898, 'test');
----------SERVICECOLLECTION----------
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 5, 1);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 5, 2);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 5, 3);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 5, 4);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 1, 1);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 2, 2);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 3, 3);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 4, 4);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 6, 1);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 6, 2);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 7, 3);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 7, 4);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 7, 1);
INSERT INTO servicecollection (serviceCollectionId, orderId, serviceId)
VALUES (obj_id_seq.nextval, 8, 1);