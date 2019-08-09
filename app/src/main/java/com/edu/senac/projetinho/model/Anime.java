package com.edu.senac.projetinho.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

@DatabaseTable
public class Anime implements Serializable {
    @DatabaseField(generatedId = true)
    private Integer codigo;

    @DatabaseField(canBeNull = false)
    private String nome;

    @DatabaseField(canBeNull = false)
    private String foto;

    @DatabaseField(canBeNull = false)
    private Integer temporada;

    @DatabaseField(canBeNull = false)
    private Integer episodios;

    @DatabaseField
    private Date dataL;

    @DatabaseField
    private Date dataE;

    @DatabaseField(canBeNull = false)
    private String diretor;

    @DatabaseField(canBeNull = false)
    private String descricao;

    @DatabaseField
    private Integer episodioAtual;

    @DatabaseField
    private String personagemFavorito;

    @DatabaseField
    private String minhasNotas;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getEpisodios() {
        return episodios;
    }

    public void setEpisodios(Integer episodios) {
        this.episodios = episodios;
    }

    public Date getDataL() {
        return dataL;
    }

    public void setDataL(Date dataL) {
        this.dataL = dataL;
    }

    public Date getDataE() {
        return dataE;
    }

    public void setDataE(Date dataE) {
        this.dataE = dataE;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEpisodioAtual() {
        return episodioAtual;
    }

    public void setEpisodioAtual(Integer episodioAtual) {
        this.episodioAtual = episodioAtual;
    }

    public String getPersonagemFavorito() {
        return personagemFavorito;
    }

    public void setPersonagemFavorito(String personagemFavorito) {
        this.personagemFavorito = personagemFavorito;
    }

    public String getMinhasNotas() {
        return minhasNotas;
    }

    public void setMinhasNotas(String minhasNotas) {
        this.minhasNotas = minhasNotas;
    }
}
