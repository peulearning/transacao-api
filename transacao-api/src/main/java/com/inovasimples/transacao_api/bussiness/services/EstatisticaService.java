package com.inovasimples.transacao_api.bussiness.services;


import com.inovasimples.transacao_api.controller.dtos.EstatisticaReponseDTO;
import com.inovasimples.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticaService {

    private static final Logger log = LoggerFactory.getLogger(EstatisticaService.class);
    public TransacaoService transacaoService;

    public EstatisticaReponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca){
        log.info("Iniciada a busca por esatisticas de transacoes pelo período de tempo" + intervaloBusca);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacao(intervaloBusca);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();
        log.info("Estastiticas Retornadas com sucesso");
            return new EstatisticaReponseDTO(estatisticasTransacoes.getCount(),
                    estatisticasTransacoes.getSum(),
                    estatisticasTransacoes.getAverage(),
                    estatisticasTransacoes.getMin(),
                    estatisticasTransacoes.getMax());
    }

    }
