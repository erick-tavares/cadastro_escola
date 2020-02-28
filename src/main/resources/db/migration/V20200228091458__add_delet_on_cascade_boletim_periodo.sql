alter table boletim
drop constraint fk_boletim_periodo;

alter table boletim
add constraint fk_boletim_periodo foreign key (periodo_id) references periodo (id)
on delete cascade;