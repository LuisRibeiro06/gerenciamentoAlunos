package com.example.gerenciarAlunos.repository;

import com.example.gerenciarAlunos.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
