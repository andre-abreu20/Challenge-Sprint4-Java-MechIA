package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClienteTO extends PessoaTO{

    @NotBlank
    private String logradouro, complemento, bairro, cidade, estado;
    @NotNull
    private int cep;
    @PastOrPresent
    private LocalDate dataNasc;

    public ClienteTO() {
    }

    public ClienteTO(int id, @NotBlank String nome, @NotBlank String email, @NotBlank  String senha, @NotBlank String genero, @PastOrPresent LocalDate dataNasc, @NotBlank String telefone, @NotBlank String logradouro, @NotBlank String complemento, @NotBlank String bairro, @NotBlank String cidade, @NotBlank String estado, @NotNull int cep) {
        super(id, nome, email, senha, genero, dataNasc, telefone);
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }
}

