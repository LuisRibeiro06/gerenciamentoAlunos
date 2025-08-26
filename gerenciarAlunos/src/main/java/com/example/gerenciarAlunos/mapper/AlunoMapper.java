package com.example.gerenciarAlunos.mapper;

import com.example.gerenciarAlunos.domain.Aluno;
import com.example.gerenciarAlunos.domain.Matricula;
import com.example.gerenciarAlunos.dto.AlunoRequest;
import com.example.gerenciarAlunos.dto.AlunoResponse;
import com.example.gerenciarAlunos.dto.MatriculaDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlunoMapper {

    public Aluno toModel(AlunoRequest alunoRequest) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequest.nome());
        aluno.setTelefone(alunoRequest.telefone());
        aluno.setDataNascimento(alunoRequest.dataNascimento());
        List<Matricula> matriculas = alunoRequest.matriculas().stream().map(matriculaDTO -> {
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(matriculaDTO.codigoMatricula());
            matricula.setCurso(matriculaDTO.curso());
            matricula.setDataMatricula(matriculaDTO.dataMatricula());
            matricula.setAluno(aluno);

            return matricula;
        }).toList();

        aluno.setMatriculas(matriculas);

        return aluno;
    }

    public AlunoResponse toResponse(Aluno aluno) {
        List<MatriculaDTO> matriculas = aluno.getMatriculas().stream().map(matricula -> {
            MatriculaDTO matriculaDTO = new MatriculaDTO(matricula.getCodigoMatricula(), matricula.getCurso(), matricula.getDataMatricula());
            return matriculaDTO;
        }).toList();
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getTelefone(), aluno.getDataNascimento(), matriculas);
    }
}
