import React, { createContext, useContext, useEffect, useState } from "react";
import { ProductAPI } from "../types/types";
import { useDispatch } from "react-redux";
import { getProducts } from "../thunks/getProducts";
import { AppDispatch } from "../redux/Store";

interface ProductsContextProps {
    allProducts: ProductAPI[];
    loading: boolean;
    error: string | null;
    setError: (error: string | null) => void;
}

const ProductsContext = createContext<ProductsContextProps | null>(null);

export function useProductsContext() {
    const context = useContext(ProductsContext);
    if (!context) throw new Error("Use ProductsContext within the ProductsContextProvider");
    return context;
}

interface ProductsContextProviderProps {
    children: React.ReactNode;
}

export const ProductsContextProvider: React.FC<ProductsContextProviderProps> = ({ children }: ProductsContextProviderProps) => {
    const [allProducts, setAllProducts] = useState<ProductAPI[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const dispatch: AppDispatch = useDispatch();

    useEffect(() => {
        const getData = async () => {
            try {
                const action = await dispatch(getProducts());
                if (getProducts.fulfilled.match(action)) {
                    setAllProducts(action.payload);
                    setLoading(false);
                } else {
                    setError("Error fetching products");
                    setLoading(false);
                }
            } catch (error) {
                console.error("Error fetching products", error);
                setError("Error fetching products");
                setLoading(false);
            }
        };
        getData();
    }, [dispatch]);

    const contextValue: ProductsContextProps = {
        allProducts,
        loading,
        error,
        setError
    };

    return (
        <ProductsContext.Provider value={contextValue}>
            {children}
        </ProductsContext.Provider>
    );
};
