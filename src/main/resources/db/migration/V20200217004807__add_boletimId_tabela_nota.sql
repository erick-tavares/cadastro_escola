alter table nota
add boletim_id BIGINT;

alter table nota
add constraint fk_nota_boletim  foreign key (boletim_id) references boletim(id);