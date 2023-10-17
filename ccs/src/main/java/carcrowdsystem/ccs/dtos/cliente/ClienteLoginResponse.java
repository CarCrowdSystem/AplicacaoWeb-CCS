package carcrowdsystem.ccs.dtos.cliente;

public class ClienteLoginResponse {
        private Integer idCliente;
        private String nomeCliente;
        private String jwt;

        public Integer getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(Integer idCliente) {
            this.idCliente = idCliente;
        }

        public String getNomeCliente() {
            return nomeCliente;
        }

        public void setNomeCliente(String nomeCliente) {
            this.nomeCliente = nomeCliente;
        }

        public String getJwt() {
            return jwt;
        }

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }
}
