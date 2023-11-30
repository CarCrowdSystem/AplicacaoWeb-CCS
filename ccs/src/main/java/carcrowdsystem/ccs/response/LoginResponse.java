package carcrowdsystem.ccs.response;

public class LoginResponse {
    private Integer idEstacionamento;
    private String nomeEstacionamento;
    private String jwt;
    private Boolean isAdm;

    public Integer getIdEstacionamento() {
        return idEstacionamento;
    }

    public void setIdEstacionamento(Integer idEstacionamento) {
        this.idEstacionamento = idEstacionamento;
    }

    public String getNomeEstacionamento() {
        return nomeEstacionamento;
    }

    public void setNomeEstacionamento(String nomeEstacionamento) {
        this.nomeEstacionamento = nomeEstacionamento;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Boolean getAdm() {
        return isAdm;
    }

    public void setAdm(Boolean adm) {
        isAdm = adm;
    }
}
