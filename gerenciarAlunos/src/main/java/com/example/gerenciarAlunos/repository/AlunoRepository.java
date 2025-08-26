package com.example.gerenciarAlunos.repository;

import com.example.gerenciarAlunos.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
