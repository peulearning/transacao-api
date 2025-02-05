package com.inovasimples.transacao_api.controller;


import com.inovasimples.transacao_api.bussiness.services.TransacaoService;
import com.inovasimples.transacao_api.controller.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private TransacaoService transacaoService;

    @Operation(description = "Endpoint responsável por adicionar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação gravada com sucesso !"),
            @ApiResponse(responseCode = "422", description = "Campos não antendem os requisitos da transação !"),
            @ApiResponse(responseCode = "400", description = "Erro de Requisição !"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor !")

    })
    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto){
        transacaoService.adicionarTransacoes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsável por deletar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação deletada com sucesso !"),
            @ApiResponse(responseCode = "400", description = "Erro de Requisição !"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor !")

    })
    public ResponseEntity<Void> deletarTransacoes(){
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
