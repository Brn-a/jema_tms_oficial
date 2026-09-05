import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react';
import { Toaster } from 'react-hot-toast'; 
import Clientes from './pages/Clientes';
import Motoristas from './pages/Motoristas';
import Caminhoes from './pages/Caminhoes';

function App() {
  const [telaAtiva, setTelaAtiva] = useState('clientes');

  return (
    <div className="bg-light min-vh-100 pb-5">
      {/* 1. Header Principal - Foco total no nome do sistema */}
      <header className="bg-dark text-white py-4 shadow">
        <div className="container text-center">
          <h1 className="display-5 fw-bold mb-0">📦 Sistema TMS Logística</h1>
          <p className="text-secondary mt-2 mb-0 fs-5">Painel Interno de Controle Operacional</p>
        </div>
      </header>

      {/* 2. Menu de Navegação - Movido para baixo em um "card" flutuante */}
      <div className="container mt-4">
        <ul className="nav nav-pills justify-content-center mb-4 shadow-sm p-3 bg-white rounded-4 border">
          <li className="nav-item">
            <button 
              className={`nav-link fw-bold fs-6 px-4 py-2 rounded-pill ${telaAtiva === 'clientes' ? 'active bg-success shadow-sm' : 'text-secondary'}`}
              onClick={() => setTelaAtiva('clientes')}
            >
              🏢 Embarcadores
            </button>
          </li>
          <li className="nav-item mx-3">
            <button 
              className={`nav-link fw-bold fs-6 px-4 py-2 rounded-pill ${telaAtiva === 'motoristas' ? 'active bg-success shadow-sm' : 'text-secondary'}`}
              onClick={() => setTelaAtiva('motoristas')}
            >
              🧑‍✈️ Motoristas
            </button>
          </li>
          <li className="nav-item">
            <button 
              className={`nav-link fw-bold fs-6 px-4 py-2 rounded-pill ${telaAtiva === 'caminhoes' ? 'active bg-success shadow-sm' : 'text-secondary'}`}
              onClick={() => setTelaAtiva('caminhoes')}
            >
              🚛 Caminhões da Frota
            </button>
          </li>
        </ul>

        {/* 3. Conteúdo Dinâmico das Tabelas */}
        <main>
          {telaAtiva === 'clientes' && <Clientes />}
          {telaAtiva === 'motoristas' && <Motoristas />}
          {telaAtiva === 'caminhoes' && <Caminhoes />}
        </main>
      </div>
    </div>
  );
}

export default App;