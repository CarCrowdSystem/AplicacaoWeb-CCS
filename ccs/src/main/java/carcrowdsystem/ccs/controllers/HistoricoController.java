package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.entitys.HistoricoEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/historicos")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    private List<HistoricoEntity> getAllHistorico() {
        return historicoService.getAllHistorico();
    }

    @GetMapping
    public ResponseEntity gerarCsv() throws MyException {
        List<HistoricoEntity> listaHistorico = getAllHistorico();
        String nome = historicoService.gravaArquivoCsv(listaHistorico);
        return ResponseEntity.status(200).body("Arquivo '"+nome+"' criado com sucesso");
    }
}

