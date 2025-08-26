package com.example.gerenciarAlunos.dto;

import java.time.LocalDate;

public record MatriculaDTO(String codigoMatricula, String curso, LocalDate dataMatricula) {
}
