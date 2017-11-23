select columnfamily_name from system.schema_columnfamilies where keyspace_name = 'lolkeyspace';

CREATE TABLE customer (id TimeUUID PRIMARY KEY, firstname text, lastname text);
CREATE INDEX customerfistnameindex ON customer (firstname);
CREATE INDEX customersecondnameindex ON customer (lastname);

CREATE KEYSPACE lolkeyspace
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

use lolkeyspace;

TRUNCATE customer;