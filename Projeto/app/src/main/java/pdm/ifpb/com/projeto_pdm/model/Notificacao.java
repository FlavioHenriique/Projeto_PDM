package pdm.ifpb.com.projeto_pdm.model;

public class Notificacao {
    private String mensagem;
    private int trabalho;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(int trabalho) {
        this.trabalho = trabalho;
    }

    public Notificacao() {
    }

    public Notificacao(String mensagem, int trabalho) {
        this.mensagem = mensagem;
        this.trabalho = trabalho;
    }

}
