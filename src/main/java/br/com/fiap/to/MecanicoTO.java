package br.com.fiap.to;

import java.time.LocalDate;

public class MecanicoTO extends PessoaTO {
    private String endereco, especialidade;

    public MecanicoTO() {
    }

    public MecanicoTO(int id, String nome, String email, String senha, String genero, LocalDate dataNasc, int telefone, String endereco, String especialidade) {
        super(id, nome, email, senha, genero, dataNasc, especialidade);
        setEndereco(endereco);
        setEspecialidade(especialidade);

    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
