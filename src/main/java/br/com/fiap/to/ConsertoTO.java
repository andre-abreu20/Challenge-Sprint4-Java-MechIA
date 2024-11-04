package br.com.fiap.to;

import java.time.LocalDate;

public class ConsertoTO{

    private LocalDate dataConserto;
    private String horaConserto;
    private int idVeiculo, idMecanico, idDiagnostico;



    public LocalDate getDataConserto() {
        return dataConserto;
    }

    public void setDataConserto(LocalDate dataConserto) {
        this.dataConserto = dataConserto;
    }

    public String getHoraConserto() {
        return horaConserto;
    }

    public void setHoraConserto(String horaConserto) {
        this.horaConserto = horaConserto;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }
}
