package br.edu.ifspsaocarlos.sdm.sitesinteressantes.model;

import java.io.Serializable;

public class Site implements Serializable {

    // URL do site interessante
    private String url;
    // Caminho para imagem de favorito
    private int imagemFavorito;

    public Site() {
    }

    public Site(String url, int imagemFavorito) {
        this.url = url;
        this.imagemFavorito = imagemFavorito;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImagemFavorito() {
        return imagemFavorito;
    }

    public void setImagemFavorito(int imagemFavorito) {
        this.imagemFavorito = imagemFavorito;
    }
}