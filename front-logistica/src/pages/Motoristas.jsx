import React, { useState, useEffect } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import toast from 'react-hot-toast';
import api from '../services/api';

function Motoristas() {
  const [motoristas, setMotoristas] = useState([]);
  const [busca, setBusca] = useState('');
  
  const [mostrarModal, setMostrarModal] = useState(false);
  const fecharModal = () => setMostrarModal(false);
  const abrirModal = () => setMostrarModal(true);

  const [novoMotorista, setNovoMotorista] = useState({
    nome: '', 
    sobrenome: '',
    cpf: '',
    rg: '',
    genero: '',
    cnh: '',
    telefone: '',
    telefone_responsavel: '',
    observacoes_saude: '',
    horario_disponivel: ''
  });

  const [idEmEdicao, setIdEmEdicao] = useState(null);

  const abrirModalNovo = () => {
    
    setNovoMotorista({ nome: '', cnh: '', telefone: '' }); 
    setIdEmEdicao(null); 
    abrirModal();
  };

  const abrirModalEdicao = (motorista) => {
    setNovoMotorista({
      nome: motorista.nome || '',
      sobrenome: motorista.sobrenome || '',
      cnh: motorista.cnh || '',
      rg: motorista.rg || '',
      cpf: motorista.cpf || '',
      genero: motorista.genero || '',
      telefoneResponsavel: motorista.telefoneResponsavel || '',
      observacoes_saude: motorista.observacoes_saude || '',
      horario_disponivel: motorista.horario_disponivel || '',
      telefone: motorista.telefone || ''
    });
    setIdEmEdicao(motorista.id); 
    abrirModal();
  };

  const carregarMotoristas = async () => {
    try {
      const response = await api.get('/motoristas/todos');
      setMotoristas(response.data);
    } catch (error) {
      console.error("Aguardando backend...", error);
    }
  };

  const lidarComBusca = async (e) => {
    const valor = e.target.value;
    setBusca(valor);
    if (valor.trim() === '') {
      carregarMotoristas();
      return;
    }
    try {
      const response = await api.get(`/motoristas/cpf/${valor}`);
      
      setMotoristas(response.data); 
      
    } catch (error) {
      console.error("Erro ao filtrar:", error);
      setMotoristas([]);
    }
  };

  const lidarComMudancaFormulario = (e) => {
    const { name, value } = e.target;
    setNovoMotorista({ ...novoMotorista, [name]: value });
  };

  const salvarMotorista = async (e) => {
    e.preventDefault(); 
    try {
      if (idEmEdicao) {
        await api.put(`/motoristas/atualizar/${idEmEdicao}`, novoMotorista);
        alert('Motorista atualizado com sucesso!');
      } else {
        // MODO NOVO CADASTRO: Dispara o POST
        await api.post('/motoristas/novo', novoMotorista);
        alert('Motorista cadastrado com sucesso!');
      }
      
      fecharModal();
      carregarMotoristas(); 
    } catch (error) {
      console.error("Erro ao salvar:", error);
      alert('Erro na comunicação com o Java. Verifique o console.');
    }
  };
 
  const excluirMotorista = async (id) => {
    const confirmar = window.confirm('Tem certeza que deseja excluir este motorista?');
    if (confirmar) {
      try {
        await api.delete(`/motoristas/${id}`); 
        carregarMotoristas(); 
        alert('Motorista excluído com sucesso!');
      } catch (error) {
        console.error("Erro ao excluir motorista:", error);
        alert('Erro ao excluir. Verifique se o backend permite deletar este registro.');
      }
    }
  };

  useEffect(() => {
    carregarMotoristas();
  }, []);

  return (
    <div className="card shadow border-0 rounded-3 mt-4">
      <div className="card-header bg-dark text-white d-flex justify-content-between align-items-center py-3">
        <h5 className="mb-0 fw-bold">Gerenciamento de Motoristas</h5>
        <button className="btn btn-success fw-bold" onClick={abrirModalNovo}>
          + Cadastrar Motorista
        </button>
      </div>
      
      <div className="card-body p-4">
        <div className="mb-4">
          <input
            type="text"
            className="form-control form-control-lg border-secondary-subtle shadow-sm"
            placeholder="🔍 Buscar motorista por CPF..."
            value={busca}
            onChange={lidarComBusca}
          />
        </div>

        <div className="table-responsive shadow-sm rounded">
          <table className="table table-hover align-middle mb-0">
            <thead className="table-light text-secondary">
              <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>CNH</th>
                <th>Telefone</th>
                <th className="text-center">Ações</th>
              </tr>
            </thead>
            <tbody>
              {motoristas.length > 0 ? (
                motoristas.map((motorista) => (
                  <tr key={motorista.id}>
                    <td className="fw-bold">{motorista.id}</td>
                    <td>{motorista.nome} {motorista.sobrenome}</td>
                    <td>{motorista.cpf}</td>
                    <td>{motorista.cnh}</td>
                    <td>{motorista.telefone}</td>
                    <td className="text-center">
                      <button className="btn btn-outline-primary btn-sm me-2" onClick={() => abrirModalEdicao(motorista)}>
                        Editar
                      </button>
                      <button className="btn btn-outline-danger btn-sm" onClick={() => excluirMotorista(motorista.id)}>
                        Excluir
                      </button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="6" className="text-center text-muted py-5">
                    <em>Nenhum motorista carregado. Aguardando conexão.</em>
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>

      {/* Aumentei o tamanho do Modal (size="lg") para caberem todos os campos bonitinhos */}
      <Modal show={mostrarModal} onHide={fecharModal} size="lg" centered backdrop="static">
        <Modal.Header closeButton className="bg-light">
          <Modal.Title className="fw-bold">Novo Motorista da Frota</Modal.Title>
        </Modal.Header>
        
        <Modal.Body className="p-4">
          <Form onSubmit={salvarMotorista}>
            
            <h6 className="text-success mb-3 border-bottom pb-2">Dados Pessoais</h6>
            <div className="row">
              <Form.Group className="col-md-6 mb-3">
                <Form.Label className="fw-semibold">Nome</Form.Label>
                <Form.Control type="text" name="nome" value={novoMotorista.nome} onChange={lidarComMudancaFormulario} placeholder="Ex: Carlos" required />
              </Form.Group>
              <Form.Group className="col-md-6 mb-3">
                <Form.Label className="fw-semibold">Sobrenome</Form.Label>
                <Form.Control type="text" name="sobrenome" value={novoMotorista.sobrenome} onChange={lidarComMudancaFormulario} placeholder="Ex: Souza" required />
              </Form.Group>
            </div>

            <div className="row">
              <Form.Group className="col-md-4 mb-3">
                <Form.Label className="fw-semibold">CPF</Form.Label>
                <Form.Control type="text" name="cpf" value={novoMotorista.cpf} onChange={lidarComMudancaFormulario} placeholder="Somente números" required />
              </Form.Group>
              <Form.Group className="col-md-4 mb-3">
                <Form.Label className="fw-semibold">RG</Form.Label>
                <Form.Control type="text" name="rg" value={novoMotorista.rg} onChange={lidarComMudancaFormulario} required />
              </Form.Group>
              <Form.Group className="col-md-4 mb-3">
                <Form.Label className="fw-semibold">Gênero</Form.Label>
                <Form.Select name="genero" value={novoMotorista.genero} onChange={lidarComMudancaFormulario} required>
                  <option value="">Selecione...</option>
                  <option value="Masculino">Masculino</option>
                  <option value="Feminino">Feminino</option>
                  <option value="Outro">Outro</option>
                </Form.Select>
              </Form.Group>
            </div>

            <h6 className="text-success mb-3 mt-3 border-bottom pb-2">Contato e Saúde</h6>
            <div className="row">
              <Form.Group className="col-md-6 mb-3">
                <Form.Label className="fw-semibold">Telefone</Form.Label>
                <Form.Control type="text" name="telefone" value={novoMotorista.telefone} onChange={lidarComMudancaFormulario} placeholder="(11) 99999-9999" required />
              </Form.Group>
              <Form.Group className="col-md-6 mb-3">
                <Form.Label className="fw-semibold">Telefone de Emergência</Form.Label>
                <Form.Control type="text" name="telefone_responsavel" value={novoMotorista.telefone_responsavel} onChange={lidarComMudancaFormulario} placeholder="(11) 88888-8888" required />
              </Form.Group>
            </div>

            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Observações de Saúde</Form.Label>
              <Form.Control as="textarea" rows={2} name="observacoes_saude" value={novoMotorista.observacoes_saude} onChange={lidarComMudancaFormulario} placeholder="Alergias, medicamentos de uso contínuo, etc." />
            </Form.Group>

            <h6 className="text-success mb-3 mt-3 border-bottom pb-2">Dados Operacionais</h6>
            <div className="row">
              <Form.Group className="col-md-4 mb-4">
                <Form.Label className="fw-semibold">CNH</Form.Label>
                <Form.Control type="text" name="cnh" value={novoMotorista.cnh} onChange={lidarComMudancaFormulario} required />
              </Form.Group>
              <Form.Group className="col-md-4 mb-4">
                <Form.Label className="fw-semibold">Horário Disponível</Form.Label>
                <Form.Control type="text" name="horario_disponivel" value={novoMotorista.horario_disponivel} onChange={lidarComMudancaFormulario} placeholder="Ex: 08:00-18:00" required />
              </Form.Group>
              <Form.Group className="col-md-4 mb-4">
                <Form.Label className="fw-semibold">ID do Caminhão</Form.Label>
                <Form.Control type="number" name="fk_caminhao" value={novoMotorista.fk_caminhao} onChange={lidarComMudancaFormulario} placeholder="Ex: 1" required />
              </Form.Group>
            </div>

            <div className="d-grid gap-2 d-md-flex justify-content-md-end">
              <Button variant="secondary" onClick={fecharModal}>Cancelar</Button>
              <Button variant="success" type="submit">Salvar Motorista</Button>
            </div>
          </Form>
        </Modal.Body>
      </Modal>
    </div>
  );
}

export default Motoristas;