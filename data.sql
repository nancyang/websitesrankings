create database if not exists trackingDB;
 
use trackingDB;

drop table if exists tracking;
create table tracking (
   id     int NOT NULL AUTO_INCREMENT,
   visitdate date,
   website  varchar(100),
   visit    int,
   primary key (id));
   
INSERT INTO tracking (visitdate,website, visit) VALUES
('2013-01-06', 'www.bing.com', '14065457'),
('2013-01-06', 'www.ebay.com.au', '19831166'),
('2013-01-06', 'www.facebook.com', '104346720'),
('2013-01-06', 'mail.live.com', '21536612'),
('2013-01-06', 'www.wikipedia.org', '13246531'),
('2013-01-27', 'www.ebay.com.au', '23154653'),
('2013-01-06', 'au.yahoo.com', '11492756'),
('2013-01-06', 'www.google.com', '26165099'),
('2013-01-13', 'www.youtube.com', '68487810'),
('2013-01-27', 'www.wikipedia.org', '16550230'),

('2013-01-06', 'ninemsn.com.au', '21734381'),
('2013-01-20', 'mail.live.com', '24344783'),
('2013-01-20', 'www.ebay.com.au', '22598506'),
('2013-01-27', 'mail.live.com', '24272437'),
('2013-01-27', 'www.bing.com', '16041776'),
('2013-01-20', 'ninemsn.com.au', '24241574'),
('2013-01-20', 'www.facebook.com', '118984483'),
('2013-01-27', 'ninemsn.com.au', '24521168'),
('2013-01-27', 'www.facebook.com', '123831275'),
('2013-01-20', 'www.bing.com', '16595739'),

('2013-01-13', 'www.facebook.com', '118506019'),
('2013-01-20', 'www.google.com.au', '170020924'),
('2013-01-27', 'www.youtube.com', '69327140'),
('2013-01-13', 'mail.live.com', '24772355'),
('2013-01-13', 'ninemsn.com.au', '24555033'),
('2013-01-20', 'www.google.com', '28996455'),
('2013-01-13', 'www.bing.com', '16618315'),
('2013-01-27', 'www.google.com.au', '171842376'),
('2013-01-06', 'www.youtube.com', '59811438'),
('2013-01-13', 'www.netbank.commbank.com.au', '13316233'),

('2013-01-20', 'www.netbank.commbank.com.au', '13072234'),
('2013-01-13', 'www.ebay.com.au', '22785028'),
('2013-01-20', 'www.wikipedia.org', '16519992'),
('2013-01-27', 'www.bom.gov.au', '14369775'),
('2013-01-27', 'www.google.com', '29422150'),
('2013-01-20', 'www.youtube.com', '69064107'),
('2013-01-06', 'www.google.com.au', '151749278'),
('2013-01-13', 'www.wikipedia.org', '16015926'),
('2013-01-13', 'www.google.com', '29203671'),
('2013-01-13', 'www.google.com.au', '172220397');