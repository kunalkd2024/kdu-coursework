import "./App.scss";
import { Home } from "./handson_files/Home";
import { Product } from "./handson_files/Product";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { ProductsContextProvider } from "./contexts/ProductsContext";
import { CategoryContextProvider } from "./contexts/CategoryContext";

function App() {
  return (
    <div id="container">
      <ProductsContextProvider>
        <CategoryContextProvider>
          <BrowserRouter>
            <Routes>
              <Route path="/products">
                <Route path="" element={<Home />} />
                <Route path=":id" element={<Product />} />
              </Route>
            </Routes>
          </BrowserRouter>
        </CategoryContextProvider>
      </ProductsContextProvider>
    </div>
  );
}

export default App;
