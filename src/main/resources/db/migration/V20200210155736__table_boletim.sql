CREATE TABLE boletim(

id    BIGINT IDENTITY (1, 1) PRIMARY KEY NOT NULL,
aluno_id BIGINT       NOT NULL,
periodo_id BIGINT       NOT NULL,

CONSTRAINT fk_boletim_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id),
CONSTRAINT fk_boletim_periodo FOREIGN KEY (periodo_id) REFERENCES periodo(id)

);