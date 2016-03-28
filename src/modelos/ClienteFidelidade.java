package modelos;


public class ClienteFidelidade extends Cliente {
    private int numeroCartao;

    public ClienteFidelidade(String nome, String cpf, int numeroCartao) {
        super(nome, cpf);
        this.numeroCartao = numeroCartao;
    }

    public int getNumeroCartao() {
        return this.numeroCartao;
    }
}
