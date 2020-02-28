alter table nota
drop constraint fk_nota_aluno;

alter table nota
add constraint fk_nota_aluno foreign key (aluno_id) references aluno (id)
on delete cascade;


alter table nota
drop constraint fk_nota_materia;

alter table nota
add constraint fk_nota_materia foreign key (materia_id) references materia (id)
on delete cascade;


alter table nota
drop constraint fk_nota_boletim;

alter table nota
add constraint fk_nota_boletim foreign key (boletim_id) references boletim (id)
on delete no action;