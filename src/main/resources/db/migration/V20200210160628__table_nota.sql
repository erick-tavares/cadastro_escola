CREATE TABLE nota(

id    BIGINT IDENTITY (1, 1) PRIMARY KEY NOT NULL,
nota DECIMAL NOT NULL,
materia_id BIGINT       NOT NULL,
boletim_id BIGINT       NOT NULL,

CONSTRAINT fk_nota_materia FOREIGN KEY (materia_id) REFERENCES materia(id),
CONSTRAINT fk_nota_boletim FOREIGN KEY (boletim_id) REFERENCES boletim(id)

);