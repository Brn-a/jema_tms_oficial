package br.com.projeto_webJemaTMS.model;

public enum TipoCaminhaoEnum {
	VUC("Veículo Urbano de Carga", 3.0),
    TOCO("Caminhão Toco", 6.0),
    TRUCK("Caminhão Truck", 14.0),
    BITRUCK("Bitruck", 22.0),
    CARRETA_2_EIXOS("Carreta 2 Eixos", 33.0),
    CARRETA_3_EIXOS("Carreta 3 Eixos", 41.5),
    CAVALO_TRUCADO("Cavalo Trucado", 45.0),
    BITREM("Bitrem", 57.0),
    RODOTREM("Rodotrem", 74.0);

    private String descricao;
    private Double capacidadeToneladas;

    TipoCaminhaoEnum(String descricao, Double capacidadeToneladas) {
        this.descricao = descricao;
        this.capacidadeToneladas = capacidadeToneladas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getCapacidadeToneladas() {
        return capacidadeToneladas;
    }
}
