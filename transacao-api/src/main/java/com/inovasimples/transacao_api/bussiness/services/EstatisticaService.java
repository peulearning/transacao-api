package com.inovasimples.transacao_api.bussiness.services;


import com.inovasimples.transacao_api.controller.dtos.EstatisticaReponseDTO;
import com.inovasimples.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticaService {

    public TransacaoService transacaoService;

    public EstatisticaReponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca){
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacao(intervaloBusca);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();
            return new EstatisticaReponseDTO(estatisticasTransacoes.getCount(),
                    estatisticasTransacoes.getSum(),
                    estatisticasTransacoes.getAverage(),
                    estatisticasTransacoes.getMin(),
                    estatisticasTransacoes.getMax());
    }

    }
