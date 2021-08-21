DROP TABLE IF EXISTS listings;
DROP TABLE IF EXISTS contacts;

create table listings(
id INT PRIMARY KEY,
make VARCHAR(100) NOT NULL,
price INT NOT NULL,
mileage INT NOT NULL,
seller_type VARCHAR(50) NOT NULL
);

create table contacts(
listing_id INT PRIMARY KEY,
contact_date VARCHAR(50) NOT NULL,
FOREIGN KEY (listing_id) REFERENCES listings(id)
);
