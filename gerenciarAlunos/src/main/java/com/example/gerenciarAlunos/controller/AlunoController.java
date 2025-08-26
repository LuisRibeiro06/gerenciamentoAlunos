package com.example.gerenciarAlunos.controller;

import com.example.gerenciarAlunos.dto.AlunoRequest;
import com.example.gerenciarAlunos.dto.AlunoResponse;
import com.example.gerenciarAlunos.dto.MatriculaDTO;
import com.example.gerenciarAlunos.service.AlunoService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> criar(@Valid @RequestBody AlunoRequest alunoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.cadastrarAluno(alunoRequest));
    }

    @GetMapping
    public List<AlunoResponse> listar(){
        return alunoService.listarTodosAlunos();
    }

    @GetMapping("/{id}/matriculas")
    public List<MatriculaDTO> listarMatriculas(@Valid @PathVariable Long id){
        return alunoService.listarMatriculas(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> atualizar(@Valid @PathVariable Long id,@Valid @RequestBody AlunoRequest alunoRequest){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.atualizarAluno(id, alunoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@Valid @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
