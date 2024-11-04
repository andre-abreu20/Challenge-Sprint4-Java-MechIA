package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class PessoaTO {

    private int id;
    @NotBlank
    private String nome, email, senha, genero, telefone;
    @PastOrPresent
    private LocalDate dataNasc;

    public PessoaTO() {
    }

    public PessoaTO(int id, @NotBlank String nome, @NotBlank String email, @NotBlank String senha, @NotBlank String genero, @PastOrPresent LocalDate dataNasc, @NotBlank String telefone) {
        setId(id);
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setGenero(genero);
        setDataNasc(dataNasc);
        setTelefone(telefone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
