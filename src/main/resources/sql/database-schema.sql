create table dict
(
   id                   bigint not null,
   dict_type            int not null,
   name                 varchar(128),
   sequence       		int,
   enable 		        int,
   primary key (id)
);