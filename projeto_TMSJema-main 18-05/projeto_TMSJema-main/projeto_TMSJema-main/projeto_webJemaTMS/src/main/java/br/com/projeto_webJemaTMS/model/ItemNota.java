package br.com.projeto_webJemaTMS.model;
import jakarta.persistence.*;


@Entity
@Table(name = "item_nota")
public class ItemNota {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Integer quantidade;

	    private Double volume;

	    private Double peso;

	    // ligação com produto
	    @ManyToOne
	    @JoinColumn(name = "fk_produto")
	    private Produto produto;

	    // ligação com NF
	    @ManyToOne
	    @JoinColumn(name = "fk_nota")
	    private Nf notaFiscal;

	    public ItemNota() {
	    }

	    public ItemNota(Long id, Integer quantidade, Double volume, Double peso,
	                    Produto produto, Nf notaFiscal) {
	        this.id = id;
	        this.quantidade = quantidade;
	        this.volume = volume;
	        this.peso = peso;
	        this.produto = produto;
	        this.notaFiscal = notaFiscal;
	    }
}
