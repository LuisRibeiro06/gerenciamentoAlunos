package com.example.gerenciarAlunos.service;

import com.example.gerenciarAlunos.domain.Aluno;
import com.example.gerenciarAlunos.domain.Matricula;
import com.example.gerenciarAlunos.dto.AlunoRequest;
import com.example.gerenciarAlunos.dto.AlunoResponse;
import com.example.gerenciarAlunos.dto.MatriculaDTO;
import com.example.gerenciarAlunos.mapper.AlunoMapper;
import com.example.gerenciarAlunos.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoMapper alunoMapper;
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoMapper alunoMapper, AlunoRepository alunoRepository) {
        this.alunoMapper = alunoMapper;
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponse cadastrarAluno(AlunoRequest alunoRequest) {
        Aluno aluno = alunoMapper.toModel(alunoRequest);
        aluno = alunoRepository.save(aluno);
        return alunoMapper.toResponse(aluno);
    }

    public List<AlunoResponse> listarTodosAlunos() {
        return alunoRepository.findAll().stream().map(alunoMapper::toResponse).toList();
    }

    public List<MatriculaDTO> listarMatriculas(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado")) ;
        return aluno.getMatriculas().stream().map(matricula -> new MatriculaDTO(matricula.getCodigoMatricula(), matricula.getCurso(), matricula.getDataMatricula())).toList();
    }

    public void removerAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado");
        }

        alunoRepository.deleteById(id);
    }

    public AlunoResponse atualizarAluno(Long id, AlunoRequest alunoRequest) {
        Aluno aluno =alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
        aluno.setNome(alunoRequest.nome());
        aluno.setTelefone(alunoRequest.telefone());
        aluno.setDataNascimento(alunoRequest.dataNascimento());

        for (MatriculaDTO matriculaDTO : alunoRequest.matriculas()) {
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(matriculaDTO.codigoMatricula());
            matricula.setCurso(matriculaDTO.curso());
            matricula.setDataMatricula(matriculaDTO.dataMatricula());
            matricula.setAluno(aluno);
            aluno.getMatriculas().add(matricula);
        }
        aluno = alunoRepository.save(aluno);
        return alunoMapper.toResponse(aluno);
    }
}
