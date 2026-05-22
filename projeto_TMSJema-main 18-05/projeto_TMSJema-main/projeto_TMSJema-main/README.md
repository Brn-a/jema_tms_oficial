# Documentação de Endpoints - Projeto Web JemaTMS

## Base URL



```Introdução
A Jema é uma empresa fictícia especializada no desenvolvimento de soluções tecnológicas para logística, com foco em sistemas TMS (Transportation Management System). Seu principal produto é uma plataforma inteligente de gerenciamento de transporte, projetada para aumentar a visibilidade e o controle das operações logísticas.
A solução da Jema tem como objetivo principal manter tanto o cliente final quanto o operador logístico constantemente informados sobre o status das entregas. Por meio de um sistema de monitoramento em tempo real, a plataforma identifica e comunica automaticamente qualquer evento relevante durante o transporte, como atrasos, desvios de rota ou incidentes com a carga.
```

```text
http://localhost:8081
```

---

# 🚚 Caminhões

## Listar todos

```http
GET /caminhoes/todos
```

---

## Buscar por ID

```http
GET /caminhoes/{id}
```

Exemplo:

```http
GET /caminhoes/1
```

---

## Inserir caminhão

```http
POST /caminhoes/novo
```

### Body JSON

```json
{
  "tipo": "TRUCK",
  "placa": "ABC1D23",
  "modelo": "Volkswagen Delivery"
}
```

---

## Deletar caminhão

```http
DELETE /caminhoes/{id}
```

---

# 👤 Clientes

## Listar todos

```http
GET /clientes/todos
```

---

## Listar todos com cache

```http
GET /clientes/todos_cache
```

---

## Buscar por ID

```http
GET /clientes/{id}
```

---

## Inserir cliente

```http
POST /clientes/novo
```

### Body JSON

```json
{
  "cpf": "12345678900",
  "cnpj": null,
  "numero": "11999999999",
  "email": "cliente@email.com",
  "nome": "João",
  "sobrenome": "Silva",
  "endereco": {
    "id": 1
  }
}
```

---

## Atualizar cliente

```http
PUT /clientes/atualizar/{id}
```

---

## Remover cliente

```http
DELETE /clientes/{id}/remover
```

---

# 📍 Endereços

## Listar todos

```http
GET /enderecos/todos
```

---

## Listar com cache

```http
GET /enderecos/todos_cache
```

---

## Buscar por ID

```http
GET /enderecos/{id}
```

---

## Inserir endereço

```http
POST /enderecos/novo
```

### Body JSON

```json
{
  "logradouro": "Rua das Flores",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "uf": "SP",
  "num": 123,
  "cep": "01001000",
  "complemento": "Apartamento 45"
}
```

---

## Atualizar endereço

```http
PUT /enderecos/atualizar/{id}
```

---

## Remover endereço

```http
DELETE /enderecos/{id}/remover
```

---

# 📦 Produtos

## Listar todos

```http
GET /produtos/todos
```

---

## Listar com cache

```http
GET /produtos/todos_cache
```

---

## Buscar por ID

```http
GET /produtos/{id}
```

---

## Inserir produto

```http
POST /produtos/novo
```

### Body JSON

```json
{
  "nome": "Notebook Gamer",
  "cor": "PRETO",
  "sku": "SKU12345",
  "volumetriaTotal": 0.045,
  "preco": 5500.00
}
```

---

## Atualizar produto

```http
PUT /produtos/atualizar/{id}
```

---

## Remover produto

```http
DELETE /produtos/{id}/remover
```

---

# 🧾 Notas Fiscais

## Listar todas

```http
GET /nfs/todos
```

---

## Buscar por ID

```http
GET /nfs/{id}
```

---

## Inserir NF

```http
POST /nfs/novo
```

### Body JSON

```json
{
  "numero": 1001,
  "volume": 2.5,
  "peso": 150.0,
  "quemRecebe": "Maria",
  "valorFinal": 6200.00,
  "valorFrete": 350.00,
  "cliente": {
    "id": 1
  }
}
```

---

## Deletar NF

```http
DELETE /nfs/{id}
```

---

# 📋 Listas

## Listar todas

```http
GET /listas/todos
```

---

## Buscar por ID

```http
GET /listas/{id}
```

---

## Criar lista

```http
POST /listas/novo
```

### Body JSON

```json
{
  "nomeRota": "Rota Zona Sul",
  "qtdNfs": 1,
  "qtdNfsEntregues": 0,
  "qtdNfsNaoEntregues": 1,
  "pesoTotal": 150.0,
  "volumetriaTotal": 12.5
}
```

---

## Adicionar NF na lista

```http
POST /listas/{listaId}/adicionar-nf/{nfId}
```

Exemplo:

```http
POST /listas/1/adicionar-nf/1
```

---

## Deletar lista

```http
DELETE /listas/{id}
```

---

# 👨‍✈️ Motoristas

## Listar todos

```http
GET /motoristas/todos
```

---

## Buscar por ID

```http
GET /motoristas/{id}
```

---

## Inserir motorista

```http
POST /motoristas/novo
```

### Body JSON

```json
{
  "cpf": "98765432100",
  "rg": "123456789",
  "nome": "Carlos",
  "sobrenome": "Souza",
  "genero": "Masculino",
  "cnh": "12345678900",
  "telefone": "11988887777",
  "telefoneResponsavel": "11977776666",
  "observacoesSaude": "Nenhuma",
  "horarioDisponivel": "08:00-18:00",
  "diasDisponiveis": [
    "SEGUNDA",
    "TERCA",
    "QUARTA"
  ],
  "caminhao": {
    "id": 1
  }
}
```

---

## Deletar motorista

```http
DELETE /motoristas/{id}
```

---

# 🚛 Alocação de Motoristas

## Buscar motoristas disponíveis para lista

```http
GET /alocacao/lista/{listaId}/motoristas-disponiveis
```

Exemplo:

```http
GET /alocacao/lista/1/motoristas-disponiveis
```

---

# 📚 ENUMS UTILIZADOS

## TipoCaminhaoEnum

```text
VUC
TOCO
TRUCK
BITRUCK
CARRETA_2_EIXOS
CARRETA_3_EIXOS
CAVALO_TRUCADO
BITREM
RODOTREM
```

---

## CorProdutoEnum

```text
VERMELHO
AZUL
VERDE
PRETO
BRANCO
```

---

## DiasSemanaEnum

```text
SEGUNDA
TERCA
QUARTA
QUINTA
SEXTA
SABADO
DOMINGO
```

