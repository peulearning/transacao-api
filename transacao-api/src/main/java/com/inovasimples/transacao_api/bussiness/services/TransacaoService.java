package com.inovasimples.transacao_api.bussiness.services;

import com.inovasimples.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.inovasimples.transacao_api.infraestructure.execeptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransacaoService {

    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto){
        log.info("Iniciado o processamento de gravar transcões." + dto);
        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a atual");
            throw new UnprocessableEntity("Data e Hora, maiores que as datas atuais.");
        }
        if(dto.valor() < 0){
            log.error("Valor não pode ser menor que 0");
            throw new UnprocessableEntity("Valor não pode ser menor que 0");
        }
        log.info("Transacoes adicionadas com sucesso");
        listaTransacoes.add(dto);
    }

    public void limparTransacoes(){
        log.info("Iniciado processamento para deletar transacoes");
        listaTransacoes.clear();
        log.info("Transacoes deletadas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacao(Integer intervaloBusca){
        log.info("Iniciada a busca por transações por tempo" + intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transacoes com sucesso");
        return listaTransacoes.stream()
                .filter(transacao-> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();
    }
}