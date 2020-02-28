alter table boletim
drop constraint fk_boletim_aluno;

alter table boletim
add constraint fk_boletim_aluno foreign key (aluno_id) references aluno (id)
on delete cascade;