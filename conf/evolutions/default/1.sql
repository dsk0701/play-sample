# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table authentication (
  id                        bigint not null,
  user_id                   varchar(255),
  access_token              varchar(255),
  refresh_token             varchar(255),
  expired_in                timestamp,
  constraint pk_authentication primary key (id))
;

create sequence authentication_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists authentication;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists authentication_seq;

