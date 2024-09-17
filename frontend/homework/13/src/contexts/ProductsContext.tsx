import React, { createContext, useContext, useEffect, useState } from "react";
import { ProductAPI } from "../types/types";

interface ProductsContextProps{
    allProducts: ProductAPI[]
}

const ProductsContext = createContext<ProductsContextProps | null>(null)

export function useProductsContext(){
    const context = useContext(ProductsContext);
    if(!context) throw new Error("Use ProductsContext within the ProductsContextProvider")
    return context;
}

interface ProductsContextProviderProps{
    children: React.ReactNode
}

export const ProductsContextProvider: React.FC<ProductsContextProviderProps> = ({children}: ProductsContextProviderProps) => {
    const [allProducts, setAllProducts] = useState<ProductAPI[]>([]);

    useEffect(() => {
      fetch("https://fakestoreapi.com/products")
        .then((response) => response.json())
        .then((data) => setAllProducts(data));
    }, []);

    return (
        <ProductsContext.Provider value={{allProducts}}>
            {children}
        </ProductsContext.Provider>
        
    )
}