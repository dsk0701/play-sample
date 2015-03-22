# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table authentication (
  id                        bigint not null,
  user_id                   varchar(255),
  access_token              varchar(255),
  refresh_token             varchar(255),
  expired_in                bigint,
  constraint pk_authentication primary key (id))
;

create table account (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_account primary key (email))
;

create sequence authentication_seq;

create sequence account_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists authentication;

drop table if exists account;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists authentication_seq;

drop sequence if exists account_seq;

