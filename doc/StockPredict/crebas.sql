/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 12                       */
/* Created on:     2022/4/7 16:47:41                            */
/*==============================================================*/


drop index if exists "User".User_PK;

drop table if exists "User";

/*==============================================================*/
/* Table: "User"                                                */
/*==============================================================*/
create table "User" 
(
   id                   varchar(32)                    not null,
   name                 varchar(10)                    null,
   password             varchar(32)                    null,
   birthday             date                           null,
   score                float                          null,
   constraint PK_USER primary key (id)
);

/*==============================================================*/
/* Index: User_PK                                               */
/*==============================================================*/
create unique index User_PK on "User" (
id ASC
);

