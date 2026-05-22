package br.com.projeto_webJemaTMS.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "motorista")
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String rg;
    private String nome;
    private String sobrenome;
    private String genero;
    private String cnh;
    private String telefone;
    private String telefoneResponsavel;
    private String observacoesSaude;
    private String horarioDisponivel;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DiasSemanaEnum> diasDisponiveis;

    @OneToOne
    @JoinColumn(name = "fk_caminhao")
    private Caminhao caminhao;

    public Motorista() {}

    // GETTERS E SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getTelefoneResponsavel() { return telefoneResponsavel; }
    public void setTelefoneResponsavel(String telefoneResponsavel) { this.telefoneResponsavel = telefoneResponsavel; }

    public String getObservacoesSaude() { return observacoesSaude; }
    public void setObservacoesSaude(String observacoesSaude) { this.observacoesSaude = observacoesSaude; }

    public String getHorarioDisponivel() { return horarioDisponivel; }
    public void setHorarioDisponivel(String horarioDisponivel) { this.horarioDisponivel = horarioDisponivel; }

    public List<DiasSemanaEnum> getDiasDisponiveis() { return diasDisponiveis; }
    public void setDiasDisponiveis(List<DiasSemanaEnum> diasDisponiveis) { this.diasDisponiveis = diasDisponiveis; }

    public Caminhao getCaminhao() { return caminhao; }
    public void setCaminhao(Caminhao caminhao) { this.caminhao = caminhao; }
}