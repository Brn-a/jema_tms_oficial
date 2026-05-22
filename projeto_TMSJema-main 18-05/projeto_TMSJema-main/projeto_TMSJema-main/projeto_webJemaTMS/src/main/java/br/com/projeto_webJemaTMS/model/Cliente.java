package br.com.projeto_webJemaTMS.model;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente")	
public class Cliente {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String cpf;
	    private String cnpj;

	    private String numero; // telefone ou número do cliente

	    private String email;

	    private String nome;
	    private String sobrenome;

	    // Relacionamento com Endereco
	    @ManyToOne
	    @JoinColumn(name = "fk_endereco")
	    private Endereco endereco;

	    // Construtor vazio
	    public Cliente() {
	    }

	    // Construtor completo
	    public Cliente(Long id, String cpf, String cnpj, String numero, String email,
	                   String nome, String sobrenome, Endereco endereco) {
	        this.id = id;
	        this.cpf = cpf;
	        this.cnpj = cnpj;
	        this.numero = numero;
	        this.email = email;
	        this.nome = nome;
	        this.sobrenome = sobrenome;
	        this.endereco = endereco;
	    }

	    // Getters e Setters

	    public Long getId() {
	        return id;
	    }

	    public String getCpf() {
	        return cpf;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public void setCpf(String cpf) {
	        this.cpf = cpf;
	    }

	    public String getCnpj() {
	        return cnpj;
	    }

	    public void setCnpj(String cnpj) {
	        this.cnpj = cnpj;
	    }

	    public String getNumero() {
	        return numero;
	    }

	    public void setNumero(String numero) {
	        this.numero = numero;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getSobrenome() {
	        return sobrenome;
	    }

	    public void setSobrenome(String sobrenome) {
	        this.sobrenome = sobrenome;
	    }

	    public Endereco getEndereco() {
	        return endereco;
	    }

	    public void setEndereco(Endereco endereco) {
	        this.endereco = endereco;
	    }

		//public Object getTelefone() {
			// TODO Auto-generated method stub
			// return null;
		//}
}
