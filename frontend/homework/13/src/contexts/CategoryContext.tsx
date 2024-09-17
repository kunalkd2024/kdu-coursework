import React, { createContext, useContext, useState } from "react";

interface CategoryContextProps{
    selectedCategory: string;
    handleCategoryChange: (e: React.ChangeEvent<HTMLSelectElement>)  => void;
}

const CategoryContext = createContext<CategoryContextProps | null>(null);

export function useCategoryContext(){
    const context = useContext(CategoryContext);
    if(!context) throw new Error("CategoryContext must be used within the CategoryContextProvider");
    return context;
}

interface CategoryContextProviderProps{
    children: React.ReactNode;
}

export const CategoryContextProvider: React.FC<CategoryContextProviderProps> = ({children}: CategoryContextProviderProps) => {
  const [selectedCategory, setSelectedCategory] = useState<string>("All");

  const handleCategoryChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedCategory(e.target.value);
  };

  return (
    <CategoryContext.Provider value={{selectedCategory, handleCategoryChange}}>
        {children}
    </CategoryContext.Provider>
  )

}