package pds.ufrn.com.br.agendadoaluno.dto;

import com.google.gson.Gson;

import pds.ufrn.com.br.agendadoaluno.util.DateUtil;

public class TaskDTO {
    private int id_turma;
    private String nomeComponente;
    private String titulo;
    private String conteudo;
    private long data_inicio;
    private long data_entrega;
    private int hora_inicio;
    private int hora_entrega;
    private int minuto_inicio;
    private int minuto_entrega;

    public TaskDTO(int id_turma, String titulo, String conteudo, long data_inicio, long data_entrega,
                   int hora_inicio, int hora_entrega, int minuto_inicio, int minuto_entrega) {
        super();
        this.id_turma = id_turma;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.data_inicio = data_inicio;
        this.data_entrega = data_entrega;
        this.hora_inicio = hora_inicio;
        this.hora_entrega = hora_entrega;
        this.minuto_inicio = minuto_inicio;
        this.minuto_entrega = minuto_entrega;
    }

    public String getNomeComponente() {
        return nomeComponente;
    }

    public void setNomeComponente(String nomeComponente) {
        this.nomeComponente = nomeComponente;
    }
    public int getId_turma() {
        return id_turma;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public long getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(long data_inicio) {
        this.data_inicio = data_inicio;
    }

    public long getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(long data_entrega) {
        this.data_entrega = data_entrega;
    }

    public int getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(int hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public int getHora_entrega() {
        return hora_entrega;
    }

    public void setHora_entrega(int hora_entrega) {
        this.hora_entrega = hora_entrega;
    }

    public int getMinuto_inicio() {
        return minuto_inicio;
    }

    public void setMinuto_inicio(int minuto_inicio) {
        this.minuto_inicio = minuto_inicio;
    }

    public int getMinuto_entrega() {
        return minuto_entrega;
    }

    public void setMinuto_entrega(int minuto_entrega) {
        this.minuto_entrega = minuto_entrega;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static TaskDTO toObject(String json) {
        Gson gson = new Gson();

        return gson.fromJson(trataJson(json), TaskDTO.class);
    }

    public static TaskDTO[] toArrayObject(String json) {
        Gson gson = new Gson();
        //json = trataJson(json);
        return gson.fromJson(json, TaskDTO[].class);
    }

    public static String trataJson(String json) {
        if (json.contains("-")) {
            return json.replace("-", "_");
        } else {
            return json;
        }
    }

    @Override
    public String toString(){
        String str = nomeComponente + "\n";
        str += titulo + "\n";
        str += conteudo + "\n";
        str += "Data início: " + DateUtil.format(data_inicio) + " às " + hora_inicio + ":" + minuto_inicio + "\n";
        str += "Data entrega: " + DateUtil.format(data_entrega) + " às " + hora_entrega + ":" + minuto_entrega + "\n";
        return str;
    }
}