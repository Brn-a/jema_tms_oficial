package br.com.projeto_webJemaTMS.model;

import jakarta.persistence.*;
@Entity
@Table(name = "produto")
public class Produto {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nome;

	    @Enumerated(EnumType.STRING)
	    private CorProdutoEnum cor;

	    private String sku;

	    private Double volumetriaTotal;

	    private Double preco;

	    // Construtor vazio
	    public Produto() {
	    }

	    // Construtor completo
	    public Produto(Long id,  String nome, CorProdutoEnum cor,
	                   String sku, Double volumetriaTotal, Double preco) {
	        this.id = id;
	        this.nome = nome;
	        this.cor = cor;
	        this.sku = sku;
	        this.volumetriaTotal = volumetriaTotal;
	        this.preco = preco;
	    }

	    // Getters e Setters

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public CorProdutoEnum getCor() {
	        return cor;
	    }

	    public void setCor(CorProdutoEnum cor) {
	        this.cor = cor;
	    }

	    public String getSku() {
	        return sku;
	    }

	    public void setSku(String sku) {
	        this.sku = sku;
	    }

	    public Double getVolumetriaTotal() {
	        return volumetriaTotal;
	    }

	    public void setVolumetriaTotal(Double volumetriaTotal) {
	        this.volumetriaTotal = volumetriaTotal;
	    }

	    public Double getPreco() {
	        return preco;
	    }

	    public void setPreco(Double preco) {
	        this.preco = preco;
	    }
}
