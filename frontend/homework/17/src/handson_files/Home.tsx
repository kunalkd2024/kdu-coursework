import React, { useEffect, useRef, useState } from "react";
import { useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { ProductAPI } from "../types/types";
import searchIcon from "../types/search.png";
import "./Home.scss";
import CircularProgress from "@mui/material/CircularProgress";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";
import { AppDispatch } from "../redux/Store";
import { useProductsContext } from "../contexts/ProductsContext";
import { useCategoryContext } from "../contexts/CategoryContext";

export function Home() {
  const { allProducts, loading, error, setError } = useProductsContext();
  const [selectedProducts, setSelectedProducts] = useState<ProductAPI[]>([]);
  const { selectedCategory, handleCategoryChange } = useCategoryContext();
  const [sortOrder, setSortOrder] = useState<"ASC" | "DESC">("ASC");
  const [searchTerm, setSearchTerm] = useState("");
  const searchInputRef = useRef<HTMLInputElement>(null);
  const dispatch: AppDispatch = useDispatch();

  const handleCloseSnackbar = () => {
    setError(null);
  };

  useEffect(() => {
    setSelectedProducts(allProducts);
  }, [allProducts]);

  useEffect(() => {
    let filtered = [...allProducts];
    if (selectedCategory !== "All") {
      filtered = allProducts.filter(
        (product) => product.category === selectedCategory
      );
    }

    if (sortOrder === "ASC") {
      filtered.sort((a, b) => a.price - b.price);
    } else {
      filtered.sort((a, b) => b.price - a.price);
    }

    setSelectedProducts(filtered);
  }, [selectedCategory, sortOrder, allProducts]);

  useEffect(() => {
    const handleSearch = () => {
      const searchQuery =
        searchInputRef.current?.value.toLowerCase() ?? "";
      setSearchTerm(searchQuery);
    };

    const inputSearch = searchInputRef.current;
    if (inputSearch) {
      inputSearch.addEventListener("input", handleSearch);

      return () => {
        inputSearch.removeEventListener("input", handleSearch);
      };
    }
  }, []);

  const handleSortChange = (
    e: React.ChangeEvent<HTMLSelectElement>
  ) => {
    setSortOrder(e.target.value as "ASC" | "DESC");
  };

  const loaderStyle = {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    height: "60vh",
  };

  return (
    <div id="main">
      <div id="header">
        <div id="search">
          <input
            type="text"
            ref={searchInputRef}
            placeholder="Search..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
          <div id="magnifier">
            <img id="search-icon" src={searchIcon} alt="" />
          </div>
        </div>
        <div>
          Filter : &nbsp;
          {Array.isArray(allProducts) && allProducts.length > 0 && (
            <select
              value={selectedCategory}
              onChange={handleCategoryChange}
            >
              <option value="All">Brand</option>
              {[...new Set(allProducts.map((product) => product.category))].map(
                (category) => (
                  <option key={category} value={category}>
                    {category}
                  </option>
                )
              )}
            </select>
          )}
        </div>
        <div>
          Sort : &nbsp;
          <select value={sortOrder} onChange={handleSortChange}>
            <option value="ASC">Ascending</option>
            <option value="DESC">Descending</option>
          </select>
        </div>
      </div>
      <div id="heading">
        <span>
          <strong>KDU</strong>
        </span>
        <span>
          <strong id="second-half"> MARKETPLACE</strong>
        </span>
      </div>
      {loading ? (
        <div style={loaderStyle}>
          <CircularProgress />
        </div>
      ) : error ? (
        <Snackbar
          open={true}
          autoHideDuration={6000}
          onClose={handleCloseSnackbar}
        >
          <MuiAlert
            elevation={6}
            variant="filled"
            severity="error"
            onClose={handleCloseSnackbar}
          >
            {error}
          </MuiAlert>
        </Snackbar>
      ) : (
        <div id="products">
          {selectedProducts
            .filter(
              (product) =>
                product.title &&
                product.title.toLowerCase().includes(searchTerm.toLowerCase())
            )
            .map((product) => {
              return (
                <div key={product.id} className="product">
                  <div id="image">
                    <Link className="link" to={`/products/${product.id}`}>
                      <img
                        className="productImage"
                        src={product.image}
                        alt={product.description}
                      />
                    </Link>
                  </div>
                  <div className="details">
                    <span className="name">
                      <Link className="link" to={`/products/${product.id}`}>
                        {product.title}
                      </Link>
                    </span>
                    <span className="price">$ {product.price}</span>
                  </div>
                </div>
              );
            })}
        </div>
      )}
    </div>
  );
}
