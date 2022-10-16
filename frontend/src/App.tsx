import { BrowserRouter, Route, Routes } from "react-router-dom";
import Certificados from "./pages/Certificados";
import Contato from "./pages/Contato";
import Home from "./pages/Home";
import Layout from "./pages/Layout";
import Projetos from "./pages/Projetos";
import SobreMim from "./pages/SobreMim";

const App = () => {
  return (
    <BrowserRouter>
      <Routes location={location} key={location.pathname}>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="/sobre-mim" element={<SobreMim />} />
          <Route path="/projetos" element={<Projetos />} />
          <Route path="/certificados" element={<Certificados />} />
          <Route path="/contato" element={<Contato />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
