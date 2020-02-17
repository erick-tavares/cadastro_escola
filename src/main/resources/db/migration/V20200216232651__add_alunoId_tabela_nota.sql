alter table nota
add aluno_id BIGINT;

alter table nota
add constraint fk_nota_aluno  foreign key (aluno_id) references aluno(id);

