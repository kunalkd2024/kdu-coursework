import { useEffect, useRef, useState } from "react";
import { useProductsContext } from "../contexts/ProductsContext";
import { useCategoryContext } from "../contexts/CategoryContext";
import { Link } from "react-router-dom";
import { ProductAPI } from "../types/types";
import searchIcon from "../types/search.png";
import "./Home.scss";

export function Home() {
  const { allProducts } = useProductsContext();
  const [selectedProducts, setSelectedProducts] = useState<ProductAPI[]>([]);
  const { selectedCategory, handleCategoryChange } = useCategoryContext();
  const [sortOrder, setSortOrder] = useState<"ASC" | "DESC">("ASC");
  const searchInputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    setSelectedProducts(allProducts);
  }, [allProducts]);

  useEffect(() => {
    let filtered = allProducts;
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
      const searchQuery = searchInputRef.current?.value.toLowerCase();
      if (!searchQuery) {
        setSelectedProducts(allProducts);
        return;
      }
      const selected: ProductAPI[] = allProducts.filter((product) =>
        product.title.toLowerCase().includes(searchQuery)
      );
      setSelectedProducts(selected);
    };

    const inputSearch = searchInputRef.current;
    if (inputSearch) {
      inputSearch.addEventListener("input", handleSearch);

      return () => {
        inputSearch.removeEventListener("input", handleSearch);
      };
    }
  }, [selectedProducts]);

  const handleSortChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSortOrder(e.target.value as "ASC" | "DESC");
  };

  return (
    <div id="main">
      <div id="header">
        <div id="search">
          <input type="text" ref={searchInputRef} placeholder="Search..." />
          <div id="magnifier">
            <img id="search-icon" src={searchIcon} alt="" />
          </div>
        </div>
        <div>
          Filter : &nbsp;
          <select value={selectedCategory} onChange={handleCategoryChange}>
            <option value="All">Brand</option>
            {[...new Set(allProducts.map((product) => product.category))].map(
              (category) => (
                <option key={category} value={category}>
                  {category}
                </option>
              )
            )}
          </select>
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
      <div id="products">
        {selectedProducts.map((product) => {
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
    </div>
  );
}
