package com.inovasimples.transacao_api.controller;

import com.inovasimples.transacao_api.bussiness.services.EstatisticaService;
import com.inovasimples.transacao_api.controller.dtos.EstatisticaReponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticaController {

    private EstatisticaService estatisticaService;

    public ResponseEntity<EstatisticaReponseDTO> buscarEstatistica(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBuca) {
        return ResponseEntity.ok
                (estatisticaService.calcularEstatisticasTransacoes(intervaloBuca));
    }
}