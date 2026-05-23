import React, { useState, useEffect } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import api from '../services/api';

function Clientes() {
  const [clientes, setClientes] = useState([]);
  const [busca, setBusca] = useState('');
  
  // Controle da Janela Flutuante (Modal)
  const [mostrarModal, setMostrarModal] = useState(false);
  const fecharModal = () => setMostrarModal(false);
  const abrirModal = () => setMostrarModal(true);

  // Dados do formulário
  const [novaEmpresa, setNovaEmpresa] = useState({
    nome: '',
    sobrenome: '',
    cpf: '', // O seu backend usa a mesma variável para CNPJ/CPF no model
    email: ''
  });

  const carregarClientes = async () => {
    try {
      const response = await api.get('/clientes/todos');
      setClientes(response.data);
    } catch (error) {
      console.error("Aguardando backend ligar para listar clientes...", error);
    }
  };

  const lidarComBusca = async (e) => {
    const valor = e.target.value;
    setBusca(valor);

    if (valor.trim() === '') {
      carregarClientes();
      return;
    }

    try {
      const response = await api.get(`/clientes/nome/${valor}`);
      setClientes(response.data);
    } catch (error) {
      console.error("Erro ao filtrar clientes:", error);
    }
  };

  const lidarComMudancaFormulario = (e) => {
    const { name, value } = e.target;
    setNovaEmpresa({ ...novaEmpresa, [name]: value });
  };

  const salvarEmpresa = async (e) => {
    e.preventDefault(); // Evita que a página recarregue ao clicar em Salvar
    try {
      // Envia os dados para a rota do seu Controller Java
      await api.post('/clientes/novo', novaEmpresa);
      
      // Se der certo, fecha a janela, limpa o formulário e atualiza a tabela
      fecharModal();
      setNovaEmpresa({ nome: '', sobrenome: '', cpf: '', email: '' });
      carregarClientes();
      alert('Empresa cadastrada com sucesso!');
    } catch (error) {
      console.error("Erro ao salvar:", error);
      alert('A interface tentou salvar, mas não alcançou o Java. Verifique se o backend está rodando.');
    }
  };

  useEffect(() => {
    carregarClientes();
  }, []);

  return (
    <div className="card shadow border-0 rounded-3 mt-4">
      <div className="card-header bg-dark text-white d-flex justify-content-between align-items-center py-3">
        <h5 className="mb-0 fw-bold">Gerenciamento de Embarcadores</h5>
        <button className="btn btn-success fw-bold" onClick={abrirModal}>
          + Cadastrar Empresa
        </button>
      </div>
      
      <div className="card-body p-4">
        {/* Barra de Busca Parametrizada */}
        <div className="mb-4">
          <input
            type="text"
            className="form-control form-control-lg border-secondary-subtle shadow-sm"
            placeholder="🔍 Buscar cliente por razão social ou nome..."
            value={busca}
            onChange={lidarComBusca}
          />
        </div>

        {/* Tabela Corporativa */}
        <div className="table-responsive shadow-sm rounded">
          <table className="table table-hover align-middle mb-0">
            <thead className="table-light text-secondary">
              <tr>
                <th>ID</th>
                <th>Nome / Razão Social</th>
                <th>CPF / CNPJ</th>
                <th>E-mail</th>
                <th className="text-center">Ações</th>
              </tr>
            </thead>
            <tbody>
              {clientes.length > 0 ? (
                clientes.map((cliente) => (
                  <tr key={cliente.id}>
                    <td className="fw-bold">{cliente.id}</td>
                    <td>{cliente.nome} {cliente.sobrenome}</td>
                    <td>{cliente.cpf || cliente.cnpj}</td>
                    <td>{cliente.email}</td>
                    <td className="text-center">
                      <button className="btn btn-outline-primary btn-sm me-2">Editar</button>
                      <button className="btn btn-outline-danger btn-sm">Excluir</button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="5" className="text-center text-muted py-5">
                    <em>Nenhum cliente carregado. Aguardando conexão com o banco de dados.</em>
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>

      {/* Janela Flutuante (Modal) de Cadastro */}
      <Modal show={mostrarModal} onHide={fecharModal} centered backdrop="static">
        <Modal.Header closeButton className="bg-light">
          <Modal.Title className="fw-bold">Nova Empresa Parceira</Modal.Title>
        </Modal.Header>
        
        <Modal.Body className="p-4">
          <Form onSubmit={salvarEmpresa}>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Razão Social / Nome</Form.Label>
              <Form.Control 
                type="text" 
                name="nome"
                value={novaEmpresa.nome}
                onChange={lidarComMudancaFormulario}
                placeholder="Ex: Indústrias Jema S/A" 
                required 
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Nome Fantasia / Sobrenome</Form.Label>
              <Form.Control 
                type="text" 
                name="sobrenome"
                value={novaEmpresa.sobrenome}
                onChange={lidarComMudancaFormulario}
                placeholder="Ex: Jema Componentes" 
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">CNPJ / CPF</Form.Label>
              <Form.Control 
                type="text" 
                name="cpf"
                value={novaEmpresa.cpf}
                onChange={lidarComMudancaFormulario}
                placeholder="00.000.000/0000-00" 
                required 
              />
            </Form.Group>

            <Form.Group className="mb-4">
              <Form.Label className="fw-semibold">E-mail de Contato</Form.Label>
              <Form.Control 
                type="email" 
                name="email"
                value={novaEmpresa.email}
                onChange={lidarComMudancaFormulario}
                placeholder="contato@empresa.com.br" 
                required 
              />
            </Form.Group>

            <div className="d-grid gap-2 d-md-flex justify-content-md-end">
              <Button variant="secondary" onClick={fecharModal}>
                Cancelar
              </Button>
              <Button variant="success" type="submit">
                Salvar Empresa
              </Button>
            </div>
          </Form>
        </Modal.Body>
      </Modal>
    </div>
  );
}

export default Clientes;