package br.com.projeto_webJemaTMS.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "nota_fiscal")
public class Nf {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private Double volume;
    private Double peso;

    private String quemRecebe;

    private Double valorFinal;
    private Double valorFrete;

    // Cliente (muitas NFs para 1 cliente)
    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    // Lista de produtos da NF
    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
    private List<ItemNota> itens;

    public Nf() {
    }

    public Nf(Long id, Integer numero, Double volume, Double peso,
                      String quemRecebe, Double valorFinal, Double valorFrete,
                      Cliente cliente) {
        this.id = id;
        this.numero = numero;
        this.volume = volume;
        this.peso = peso;
        this.quemRecebe = quemRecebe;
        this.valorFinal = valorFinal;
        this.valorFrete = valorFrete;
        this.cliente = cliente;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getQuemRecebe() {
        return quemRecebe;
    }

    public void setQuemRecebe(String quemRecebe) {
        this.quemRecebe = quemRecebe;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemNota> getItens() {
        return itens;
    }

    public void setItens(List<ItemNota> itens) {
        this.itens = itens;
    }

}
