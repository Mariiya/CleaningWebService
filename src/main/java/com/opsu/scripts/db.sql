drop table servicecollection;
drop table service;
drop table orders;
drop table consumer;
drop table vendor;
drop table users;

--
CREATE TABLE USERS (
                       userId SERIAL NOT NULL,
                       email varchar(255) DEFAULT NULL,
                       password varchar(255) DEFAULT NULL,
                       phoneNumber varchar(45) DEFAULT NULL,
                       role varchar(255) DEFAULT NULL,
                       PRIMARY KEY (userId)
) ;

--
CREATE TABLE  consumer  (
                            firstName  varchar(255) DEFAULT NULL,
                            lastName  varchar(255) DEFAULT NULL,
                            userId  int DEFAULT NULL,
                            PRIMARY KEY ( userId ),
                            CONSTRAINT  consumer_ibfk_1  FOREIGN KEY ( userId ) REFERENCES  USERS  ( userId )
);

--
CREATE TABLE vendor (
                        firstName varchar(255) DEFAULT NULL,
                        lastName varchar(255) DEFAULT NULL,
                        individual BOOLEAN DEFAULT '1',
                        userId int NOT NULL  PRIMARY KEY,
                        CONSTRAINT vendor_ibfk_1 FOREIGN KEY (userId) REFERENCES USERS (userId)
);

--
CREATE TABLE orders (
                        orderId SERIAL NOT NULL,
                        title varchar(255) DEFAULT NULL,
                        description varchar(255) DEFAULT NULL,
                        status varchar(255) DEFAULT NULL,
                        consumerId int DEFAULT NULL,
                        vendorId int DEFAULT NULL,
                        startDate date DEFAULT NULL,
                        endDate date DEFAULT NULL,
                        price float DEFAULT NULL,
                        address varchar(255) DEFAULT NULL,
                        PRIMARY KEY (orderId),
                        CONSTRAINT orders_ibfk_1 FOREIGN KEY (consumerId) REFERENCES consumer (userId),
                        CONSTRAINT orders_ibfk_2 FOREIGN KEY (vendorId) REFERENCES vendor (userId)
);

--
CREATE TABLE service (
                         serviceId SERIAL NOT NULL,
                         name varchar(255) DEFAULT NULL,
                         description varchar(255) DEFAULT NULL,
                         isCustom BOOLEAN DEFAULT '1',
                         PRIMARY KEY (serviceId)
);

--
CREATE TABLE servicecollection (
                                   serviceCollectionId SERIAL NOT NULL,
                                   orderId int DEFAULT NULL,
                                   serviceId int DEFAULT NULL,
                                   PRIMARY KEY (serviceCollectionId),
                                   CONSTRAINT servicecollection_ibfk_1 FOREIGN KEY (orderId) REFERENCES orders (orderId),
                                   CONSTRAINT servicecollection_ibfk_2 FOREIGN KEY (serviceId) REFERENCES service (serviceId)
);

