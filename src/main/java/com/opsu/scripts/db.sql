begin
execute immediate 'DROP function seq_next';
execute immediate 'DROP function seq_curr';
execute immediate 'DROP SEQUENCE SEQ';
exception
    when others then null;
end;

begin
execute immediate 'drop table consumer CASCADE CONSTRAINTS';
exception
    when others then null;
end;
/

begin
execute immediate 'drop table orders CASCADE CONSTRAINTS';
exception
    when others then null;
end;
/

begin
execute immediate 'drop table service CASCADE CONSTRAINTS';
exception
    when others then null;
end;
/

begin
execute immediate 'drop table servicecollection CASCADE CONSTRAINTS';
exception
    when others then null;
end;
/

begin
execute immediate 'drop table users CASCADE CONSTRAINTS';
exception
    when others then null;
end;
/

begin
execute immediate 'drop table vendor CASCADE CONSTRAINTS';
exception
    when others then null;
end;
/

--
CREATE TABLE "USERS" (
                         userId int NOT NULL,
                         email varchar(255) DEFAULT NULL,
                         password varchar(255) DEFAULT NULL,
                         phoneNumber varchar(45) DEFAULT NULL,
                         role varchar(255) DEFAULT NULL,
                         PRIMARY KEY (userId)
) ;

--
CREATE TABLE  consumer  (
                            consumerId  int NOT NULL,
                            firstName  varchar2(255) DEFAULT NULL,
                            lastName  varchar2(255) DEFAULT NULL,
                            userId  int DEFAULT NULL,
                            PRIMARY KEY ( consumerId ),
                            CONSTRAINT  consumer_ibfk_1  FOREIGN KEY ( userId ) REFERENCES  "USER"  ( userId )
);

--
CREATE TABLE vendor (
                        vendorId int NOT NULL  PRIMARY KEY ,
                        firstName varchar2(255) DEFAULT NULL,
                        lastName varchar2(255) DEFAULT NULL,
                        individual INT DEFAULT '1',
                        userId int NOT NULL,
                        CONSTRAINT vendor_ibfk_1 FOREIGN KEY (userId) REFERENCES "USERS" (userId)
);

--
CREATE TABLE orders (
                        orderId int NOT NULL,
                        consumerId int DEFAULT NULL,
                        vendorId int DEFAULT NULL,
                        startDate date DEFAULT NULL,
                        endDate date DEFAULT NULL,
                        price float DEFAULT NULL,
                        PRIMARY KEY (orderId),
                        CONSTRAINT orders_ibfk_1 FOREIGN KEY (consumerId) REFERENCES consumer (consumerId),
                        CONSTRAINT orders_ibfk_2 FOREIGN KEY (vendorId) REFERENCES vendor (vendorId)
);

--
CREATE TABLE service (
                         serviceId int NOT NULL,
                         name varchar(255) DEFAULT NULL,
                         description varchar(255) DEFAULT NULL,
                         PRIMARY KEY (serviceId)
);

--
CREATE TABLE servicecollection (
                                   serviceCollectionId int NOT NULL,
                                   orderId int DEFAULT NULL,
                                   serviceId int DEFAULT NULL,
                                   PRIMARY KEY (serviceCollectionId),
                                   CONSTRAINT servicecollection_ibfk_1 FOREIGN KEY (orderId) REFERENCES orders (orderId),
                                   CONSTRAINT servicecollection_ibfk_2 FOREIGN KEY (serviceId) REFERENCES service (serviceId)
);

CREATE SEQUENCE SEQ START WITH 1 MAXVALUE 999999 INCREMENT BY 1 NOCACHE CYCLE;
CREATE FUNCTION seq_next RETURN NUMBER RESULT_CACHE IS
BEGIN
RETURN SEQ.nextval;
END seq_next;
/
CREATE FUNCTION seq_curr RETURN NUMBER RESULT_CACHE IS
BEGIN
RETURN SEQ.currval;
END seq_curr;
