-- for PostgreSQL

-- Drop tables

DROP TABLE IF EXISTS oauth_client_details;
DROP TABLE IF EXISTS oauth_client_token;
DROP TABLE IF EXISTS oauth_access_token;
DROP TABLE IF EXISTS oauth_refresh_token;
DROP TABLE IF EXISTS oauth_code;
DROP TABLE IF EXISTS oauth_approvals;

-- Create tables

create table if not exists oauth_client_details
(
   client_id VARCHAR (256) PRIMARY KEY,
   resource_ids VARCHAR (256),
   client_secret VARCHAR (256),
   scope VARCHAR (256),
   authorized_grant_types VARCHAR (256),
   web_server_redirect_uri VARCHAR (256),
   authorities VARCHAR (256),
   access_token_validity INTEGER,
   refresh_token_validity INTEGER,
   additional_information VARCHAR (4096),
   autoapprove VARCHAR (256)
);
create table if not exists oauth_client_token
(
   token_id VARCHAR (256),
   token BYTEA,
   authentication_id VARCHAR (256) PRIMARY KEY,
   user_name VARCHAR (256),
   client_id VARCHAR (256)
);
create table if not exists oauth_access_token
(
   token_id VARCHAR (256),
   token BYTEA,
   authentication_id VARCHAR (256) PRIMARY KEY,
   user_name VARCHAR (256),
   client_id VARCHAR (256),
   authentication BYTEA,
   refresh_token VARCHAR (256)
);
create table if not exists oauth_refresh_token
(
   token_id VARCHAR (256),
   token BYTEA,
   authentication BYTEA
);
create table if not exists oauth_code
(
   code VARCHAR (256),
   authentication BYTEA
);
create table if not exists oauth_approvals
(
   userId VARCHAR (256),
   clientId VARCHAR (256),
   scope VARCHAR (256),
   status VARCHAR (10),
   expiresAt TIMESTAMP (0),
   lastModifiedAt TIMESTAMP (0)
);

-- Default data

INSERT INTO oauth_client_details
(
   client_id,
   resource_ids,
   client_secret,
   scope,
   authorized_grant_types,
   web_server_redirect_uri,
   authorities,
   access_token_validity,
   refresh_token_validity,
   additional_information,
   autoapprove
)
VALUES
(
   'ocean', -- b2NlYW4=
   'resource',
   '$2a$10$Sd8AZh/bJv3Es7TKvKHHD.UYB1msnYPyqO.Nce2wHLTJWjKC829.q', -- 12341234
   'all',
   'authorization_code,password,refresh_token',
   'http://localhost:9001',
   NULL,
   NULL,
   NULL,
   NULL,
   NULL
);
