package br.com.projeto_webJemaTMS.model;
import jakarta.persistence.*;

@Entity
@Table(name = "caminhao")
public class Caminhao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tipo (ENUM)
    @Enumerated(EnumType.STRING)
    private TipoCaminhaoEnum tipo;

    private String placa;

    private String modelo;

    // Construtor vazio
    public Caminhao() {
    }

    // Construtor completo
    public Caminhao(Long id, TipoCaminhaoEnum tipo, String placa, String modelo) {
        this.id = id;
        this.tipo = tipo;
        this.placa = placa;
        this.modelo = modelo;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public TipoCaminhaoEnum getTipo() {
        return tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(TipoCaminhaoEnum tipo) {
        this.tipo = tipo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
