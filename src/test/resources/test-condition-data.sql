set foreign_key_checks=0;
truncate jobs;
truncate benefits;
set foreign_key_checks=1;
insert into need.jobs(name) values('학생'), ('대학생'), ('취준생');
insert into need.benefits(name) values('취업'), ('생활'), ('교통'), ('학습');