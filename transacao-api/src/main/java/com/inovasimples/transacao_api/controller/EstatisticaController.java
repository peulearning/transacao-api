package com.inovasimples.transacao_api.controller;

import com.inovasimples.transacao_api.bussiness.services.EstatisticaService;
import com.inovasimples.transacao_api.controller.dtos.EstatisticaReponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticaController {

    private EstatisticaService estatisticaService;

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar estatisticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatísticas de transações"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")

    })
    public ResponseEntity<EstatisticaReponseDTO> buscarEstatistica(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBuca) {
        return ResponseEntity.ok
                (estatisticaService.calcularEstatisticasTransacoes(intervaloBuca));
    }
}