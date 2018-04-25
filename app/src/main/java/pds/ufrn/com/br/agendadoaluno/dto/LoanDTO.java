package pds.ufrn.com.br.agendadoaluno.dto;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import pds.ufrn.com.br.agendadoaluno.util.DateUtil;

public class LoanDTO {
    private String autor;
    private String cpf_cnpj_usuario;
    private long data_devolucao;
    private long data_emprestimo;
    private long data_renovacao;
    private long id_biblioteca;
    private long id_emprestimo;
    private long prazo;
    private String tipo_emprestimo;
    private String titulo;

    public LoanDTO() {
        super();
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCpf_cnpj_usuario() {
        return cpf_cnpj_usuario;
    }

    public void setCpf_cnpj_usuario(String cpf_cnpj_usuario) {
        this.cpf_cnpj_usuario = cpf_cnpj_usuario;
    }

    public long getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(long data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public long getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(long data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public long getData_renovacao() {
        return data_renovacao;
    }

    public void setData_renovacao(long data_renovacao) {
        this.data_renovacao = data_renovacao;
    }

    public long getId_biblioteca() {
        return id_biblioteca;
    }

    public void setId_biblioteca(long id_biblioteca) {
        this.id_biblioteca = id_biblioteca;
    }

    public long getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(long id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    public long getPrazo() {
        return prazo;
    }

    public void setPrazo(long prazo) {
        this.prazo = prazo;
    }

    public String getTipo_emprestimo() {
        return tipo_emprestimo;
    }

    public void setTipo_emprestimo(String tipo_emprestimo) {
        this.tipo_emprestimo = tipo_emprestimo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static LoanDTO toObject(String json) {
        Gson gson = new Gson();

        if (json.startsWith("[")) {
            System.out.println(json.charAt(json.length() - 1));
            LoanDTO[] studentArray = toArrayObject(json);
            if (studentArray != null && studentArray.length == 1) {
                return studentArray[0];
            } else {
                return null;
            }
        } else {
            return gson.fromJson(json, LoanDTO.class);
        }
    }

    public static LoanDTO[] toArrayObject(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, LoanDTO[].class);
    }

    @Override
    public String toString(){
        String str = String.format("Título: %s \n Autor: %s \n Data de entrega: %s \n", titulo, autor, DateUtil.format(data_devolucao));
        return str;
    }

}