package pdm.ifpb.com.projeto_pdm.pdm.ifpb.com.projeto_pdm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trabalho {

    private String titulo;
    private String estado;
    private String cidade;
    private int codigo;
    private float valor;
    private String horario;
    private String data;
    private String categoria;
    private String descricao;
    private Usuario contratado;
    private Usuario contratante;
    private List<Usuario> solicitantes;

    public Trabalho() {

    }

    public Trabalho(String titulo, String estado, String cidade, float valor,
                    String horario, String data, String descricao, Usuario contratante, int codigo,
                    String categoria) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.codigo = codigo;
        this.estado = estado;
        this.cidade = cidade;
        this.valor = valor;
        this.horario = horario;
        this.data = data;
        this.descricao = descricao;
        this.contratante = contratante;
        this.solicitantes = new ArrayList<>();

    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Usuario getContratado() {
        return contratado;
    }

    public void setContratado(Usuario contratado) {
        this.contratado = contratado;
    }

    public List<Usuario> getSolicitantes() {
        return solicitantes;
    }

    public void setSolicitantes(List<Usuario> solicitantes) {
        this.solicitantes = solicitantes;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public Usuario getContratante() {
        return contratante;
    }

    public void setContratante(Usuario contratante) {
        this.contratante = contratante;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.titulo);
        hash = 97 * hash + Objects.hashCode(this.estado);
        hash = 97 * hash + Objects.hashCode(this.cidade);
        hash = 97 * hash + this.codigo;
        hash = 97 * hash + Float.floatToIntBits(this.valor);
        hash = 97 * hash + Objects.hashCode(this.horario);
        hash = 97 * hash + Objects.hashCode(this.data);
        hash = 97 * hash + Objects.hashCode(this.categoria);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        hash = 97 * hash + Objects.hashCode(this.contratado);
        hash = 97 * hash + Objects.hashCode(this.contratante);
        hash = 97 * hash + Objects.hashCode(this.solicitantes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trabalho other = (Trabalho) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (Float.floatToIntBits(this.valor) != Float.floatToIntBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.horario, other.horario)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.contratado, other.contratado)) {
            return false;
        }
        if (!Objects.equals(this.contratante, other.contratante)) {
            return false;
        }
        if (!Objects.equals(this.solicitantes, other.solicitantes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trabalho{" + "titulo=" + titulo + ", estado=" + estado + ","
                + " cidade=" + cidade + ", codigo=" + codigo + ", valor=" + valor
                + ", horario=" + horario + ", data=" + data + ", categoria=" +
                categoria + ", descricao=" + descricao + ", contratado=" +
                contratado + ", contratante=" + contratante + ", solicitantes="
                + solicitantes + '}';
    }

}
