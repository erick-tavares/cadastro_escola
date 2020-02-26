package br.com.hbsis.ecolahb.aluno;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoService.class);

    private final IAlunoRepository iAlunoRepository;

    public AlunoService(IAlunoRepository iAlunoRepository) {
        this.iAlunoRepository = iAlunoRepository;
    }

    public AlunoDTO save(AlunoDTO alunoDTO) {

        this.validate(alunoDTO);

        LOGGER.info("Salvando aluno");
        LOGGER.debug("Aluno: {}", alunoDTO);

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setEndereco(alunoDTO.getEndereco());
        aluno.setTelefone(alunoDTO.getTelefone());

        aluno = this.iAlunoRepository.save(aluno);

        return AlunoDTO.of(aluno);
    }

    private void validate(AlunoDTO alunoDTO) {
        LOGGER.info("Validando Aluno");

        if (alunoDTO == null) {
            throw new IllegalArgumentException("AlunoDTO não deve ser nulo");
        }

        if (StringUtils.isEmpty(alunoDTO.getNome())) {
            throw new IllegalArgumentException("Nome não deve ser nulo");
        }

        if (StringUtils.isEmpty(alunoDTO.getEndereco())) {
            throw new IllegalArgumentException("Endereço não deve ser nulo");
        }

        if (StringUtils.isEmpty(alunoDTO.getTelefone())) {
            throw new IllegalArgumentException("Telefone não deve ser nulo");
        }

        if (alunoDTO.getTelefone().replaceAll("[\\d]", "").length() > 11 || alunoDTO.getTelefone().replaceAll("[\\d]", "").length() < 10) {
            throw new IllegalArgumentException("Telefone inválido, digite um telefone no formato -(99)9999-9999- ");
        }

    }

    public AlunoDTO findById(Long id) {
        Optional<Aluno> alunoOptional = this.iAlunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            return AlunoDTO.of(alunoOptional.get());
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public List<Aluno> findAll (){
        return iAlunoRepository.findAll();
    }

    public Aluno findByAlunoId (Long id){
        Optional<Aluno> alunoOptional = this.iAlunoRepository.findById(id);
        if (alunoOptional.isPresent()) {
            return alunoOptional.get();
        }
        throw new IllegalArgumentException(String.format("Aluno %s não está cadastrado", id));
    }


    public AlunoDTO update(AlunoDTO alunoDTO, Long id) {
        Optional<Aluno> alunoExistenteOptional = this.iAlunoRepository.findById(id);

        if (alunoExistenteOptional.isPresent()) {
            Aluno alunoExistente = alunoExistenteOptional.get();

            LOGGER.info("Atualizando aluno... id: [{}]", alunoExistente.getId());
            LOGGER.debug("Payload: {}", alunoDTO);
            LOGGER.debug("Aluno Existente: {}", alunoExistente);

            alunoExistente.setNome(alunoDTO.getNome());
            alunoExistente.setEndereco(alunoDTO.getEndereco());
            alunoExistente.setTelefone(alunoDTO.getTelefone());

            alunoExistente = this.iAlunoRepository.save(alunoExistente);

            return AlunoDTO.of(alunoExistente);
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para aluno de ID: [{}]", id);

        this.iAlunoRepository.deleteById(id);
    }

}
