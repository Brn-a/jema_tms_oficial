package br.com.projeto_webJemaTMS.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lista")
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeRota;

    private Integer qtdNfs;
    private Integer qtdNfsEntregues;
    private Integer qtdNfsNaoEntregues;

    private Double pesoTotal;
    private Double volumetriaTotal;

    // 🔥 IMPORTANTE: inicializa aqui
    @OneToMany
    @JoinColumn(name = "fk_lista")
    private List<Nf> nfs = new ArrayList<>();

    // Construtor vazio
    public Lista() {
    }

    // Construtor completo
    public Lista(Long id, String nomeRota, Integer qtdNfs,
                 Integer qtdNfsEntregues, Integer qtdNfsNaoEntregues,
                 Double pesoTotal, Double volumetriaTotal, List<Nf> nfs) {

        this.id = id;
        this.nomeRota = nomeRota;
        this.qtdNfs = qtdNfs;
        this.qtdNfsEntregues = qtdNfsEntregues;
        this.qtdNfsNaoEntregues = qtdNfsNaoEntregues;
        this.pesoTotal = pesoTotal;
        this.volumetriaTotal = volumetriaTotal;
        this.nfs = nfs;
    }

    // getters e setters (importante pro Spring/JPA)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRota() {
        return nomeRota;
    }

    public void setNomeRota(String nomeRota) {
        this.nomeRota = nomeRota;
    }

    public Integer getQtdNfs() {
        return qtdNfs;
    }

    public void setQtdNfs(Integer qtdNfs) {
        this.qtdNfs = qtdNfs;
    }

    public Integer getQtdNfsEntregues() {
        return qtdNfsEntregues;
    }

    public void setQtdNfsEntregues(Integer qtdNfsEntregues) {
        this.qtdNfsEntregues = qtdNfsEntregues;
    }

    public Integer getQtdNfsNaoEntregues() {
        return qtdNfsNaoEntregues;
    }

    public void setQtdNfsNaoEntregues(Integer qtdNfsNaoEntregues) {
        this.qtdNfsNaoEntregues = qtdNfsNaoEntregues;
    }

    public Double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(Double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public Double getVolumetriaTotal() {
        return volumetriaTotal;
    }

    public void setVolumetriaTotal(Double volumetriaTotal) {
        this.volumetriaTotal = volumetriaTotal;
    }

    public List<Nf> getNfs() {
        return nfs;
    }

    public void setNfs(List<Nf> nfs) {
        this.nfs = nfs;
    }
}