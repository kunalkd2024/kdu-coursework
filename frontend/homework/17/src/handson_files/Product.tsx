import { ProductAPI } from "../types/types";
import { useParams, Link } from "react-router-dom";
import { useProductsContext } from "../contexts/ProductsContext";
import searchIcon from "../types/search.png";
import "./Product.scss";

export function Product() {
  const { allProducts } = useProductsContext();

  const { id } = useParams();

  const currentProduct: ProductAPI | undefined = allProducts.find(
    (product) => product.id === parseInt(id!)
  );

  if (!currentProduct) {
    return <div>Loading...</div>;
  }

  return (
    <>
      <div id="header">
        <div id="search">
          <input type="text" placeholder="Search..." />
          <div id="magnifier">
            <img id="search-icon" src={searchIcon} alt="" />
          </div>
        </div>
      </div>
      <div id="main-section">
        <h1 id="heading">{currentProduct.title}</h1>
        <div id="mid-sec">
          <div id="left">
            <img className="productImage" src={currentProduct.image} />
          </div>
          <div id="right">
            <div id="product">Product: {currentProduct.title}</div>
            <div id="category">Category : {currentProduct.category} </div>
            <div id="price">Price : $ {currentProduct.price} </div>
            Rating : {currentProduct.rating.rate}⭐(
            {currentProduct.rating.count} reviews) <br />
            <div id="description-heading">Product Description: <br /> </div>
            <div id="description-content">{currentProduct.description} </div>
            <div id="link">
              <Link id="content" to="/products">Back To Products</Link>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
