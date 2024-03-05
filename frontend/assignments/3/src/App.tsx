import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import { DashboardExplore } from "./components/Dashboard/DashboardExplore";
import { DashboardWatchlist } from "./components/Dashboard/DashboardWatchlist";
import { StockPage } from "./components/StockPage/StockPage";
import { Portfolio } from "./components/Portfolio/Portfolio";
import PageNotFound from "./components/Utils/PageNotFound";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/dashboard">
            <Route path="explore" element={<DashboardExplore />} />
            <Route path="watchlist" element={<DashboardWatchlist />} />
          </Route>
          <Route path="/stock/:stockSymbol" element={<StockPage />} />
          <Route path="/portfolio" element={<Portfolio />} />
          <Route path="*" element={<PageNotFound />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
