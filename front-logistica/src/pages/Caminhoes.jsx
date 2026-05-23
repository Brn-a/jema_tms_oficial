import React, { useState, useEffect } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import api from '../services/api';

function Caminhoes() {
  const [caminhoes, setCaminhoes] = useState([]);
  const [busca, setBusca] = useState('');
  
  // Controle do Modal
  const [mostrarModal, setMostrarModal] = useState(false);
  const fecharModal = () => setMostrarModal(false);
  const abrirModal = () => setMostrarModal(true);

  // Dados do formulário de Caminhão
  const [novoCaminhao, setNovoCaminhao] = useState({
    placa: '',
    modelo: '',
    marca: '',
    tipo: 'TRUCK' // Valor padrão ligado ao Enum do seu backend
  });

  const carregarCaminhoes = async () => {
    try {
      const response = await api.get('/caminhoes/todos');
      setCaminhoes(response.data);
    } catch (error) {
      console.error("Aguardando backend ligar para listar caminhões...", error);
    }
  };

  const lidarComBusca = async (e) => {
    const valor = e.target.value;
    setBusca(valor);

    if (valor.trim() === '') {
      carregarCaminhoes();
      return;
    }

    try {
      // Supondo rota de busca parametrizada por placa ou modelo
      const response = await api.get(`/caminhoes/busca/${valor}`);
      setCaminhoes(response.data);
    } catch (error) {
      console.error("Erro ao filtrar caminhões:", error);
    }
  };

  const lidarComMudancaFormulario = (e) => {
    const { name, value } = e.target;
    setNovoCaminhao({ ...novoCaminhao, [name]: value });
  };

  const salvarCaminhao = async (e) => {
    e.preventDefault();
    try {
      await api.post('/caminhoes/novo', novoCaminhao);
      fecharModal();
      setNovoCaminhao({ placa: '', modelo: '', marca: '', tipo: 'TRUCK' });
      carregarCaminhoes();
      alert('Caminhão cadastrado com sucesso!');
    } catch (error) {
      console.error("Erro ao salvar:", error);
      alert('Tentativa de salvar realizada. Verifique se o backend está rodando.');
    }
  };

  useEffect(() => {
    carregarCaminhoes();
  }, []);

  return (
    <div className="card shadow border-0 rounded-3 mt-4">
      <div className="card-header bg-dark text-white d-flex justify-content-between align-items-center py-3">
        <h5 className="mb-0 fw-bold">Gerenciamento da Frota (Caminhões)</h5>
        <button className="btn btn-success fw-bold" onClick={abrirModal}>
          + Cadastrar Veículo
        </button>
      </div>
      
      <div className="card-body p-4">
        <div className="mb-4">
          <input
            type="text"
            className="form-control form-control-lg border-secondary-subtle shadow-sm"
            placeholder="🔍 Buscar caminhão por placa ou modelo..."
            value={busca}
            onChange={lidarComBusca}
          />
        </div>

        <div className="table-responsive shadow-sm rounded">
          <table className="table table-hover align-middle mb-0">
            <thead className="table-light text-secondary">
              <tr>
                <th>ID</th>
                <th>Placa</th>
                <th>Marca / Modelo</th>
                <th>Tipo de Veículo</th>
                <th className="text-center">Ações</th>
              </tr>
            </thead>
            <tbody>
              {caminhoes.length > 0 ? (
                caminhoes.map((caminhao) => (
                  <tr key={caminhao.id}>
                    <td className="fw-bold">{caminhao.id}</td>
                    <td className="text-uppercase">{caminhao.placa}</td>
                    <td>{caminhao.marca} {caminhao.modelo}</td>
                    <td><span className="badge bg-info text-dark">{caminhao.tipo}</span></td>
                    <td className="text-center">
                      <button className="btn btn-outline-primary btn-sm me-2">Editar</button>
                      <button className="btn btn-outline-danger btn-sm">Excluir</button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="5" className="text-center text-muted py-5">
                    <em>Nenhum veículo carregado. Aguardando conexão com o banco de dados.</em>
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>

      <Modal show={mostrarModal} onHide={fecharModal} centered backdrop="static">
        <Modal.Header closeButton className="bg-light">
          <Modal.Title className="fw-bold">Novo Caminhão</Modal.Title>
        </Modal.Header>
        
        <Modal.Body className="p-4">
          <Form onSubmit={salvarCaminhao}>
            <div className="row">
              <Form.Group className="col-md-6 mb-3">
                <Form.Label className="fw-semibold">Placa</Form.Label>
                <Form.Control 
                  type="text" 
                  name="placa"
                  value={novoCaminhao.placa}
                  onChange={lidarComMudancaFormulario}
                  placeholder="ABC-1234"
                  className="text-uppercase"
                  required 
                />
              </Form.Group>

              <Form.Group className="col-md-6 mb-3">
                <Form.Label className="fw-semibold">Marca</Form.Label>
                <Form.Control 
                  type="text" 
                  name="marca"
                  value={novoCaminhao.marca}
                  onChange={lidarComMudancaFormulario}
                  placeholder="Ex: Volvo, Scania" 
                  required 
                />
              </Form.Group>
            </div>

            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Modelo</Form.Label>
              <Form.Control 
                type="text" 
                name="modelo"
                value={novoCaminhao.modelo}
                onChange={lidarComMudancaFormulario}
                placeholder="Ex: FH 540" 
                required 
              />
            </Form.Group>

            <Form.Group className="mb-4">
              <Form.Label className="fw-semibold">Tipo do Caminhão</Form.Label>
              <Form.Select 
                name="tipo"
                value={novoCaminhao.tipo}
                onChange={lidarComMudancaFormulario}
              >
                <option value="TOCO">Toco (2 Eixos)</option>
                <option value="TRUCK">Truck (3 Eixos)</option>
                <option value="CARRETA">Carreta Simples</option>
                <option value="BITREM">Bitrem / Rodotrem</option>
                <option value="VUC">VUC (Veículo Urbano de Carga)</option>
              </Form.Select>
            </Form.Group>

            <div className="d-grid gap-2 d-md-flex justify-content-md-end">
              <Button variant="secondary" onClick={fecharModal}>
                Cancelar
              </Button>
              <Button variant="success" type="submit">
                Salvar Veículo
              </Button>
            </div>
          </Form>
        </Modal.Body>
      </Modal>
    </div>
  );
}

export default Caminhoes;