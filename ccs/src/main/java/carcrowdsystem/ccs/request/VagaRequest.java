package carcrowdsystem.ccs.request;

import carcrowdsystem.ccs.request.dtos.VagaDtoRequest;

import java.util.List;

public class VagaRequest {
    private List<VagaDtoRequest> vagas;

    public List<VagaDtoRequest> getVagas() {
        return vagas;
    }

    public void setVagas(List<VagaDtoRequest> vagas) {
        this.vagas = vagas;
    }
}
