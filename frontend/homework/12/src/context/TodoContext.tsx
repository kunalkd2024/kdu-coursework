import React, { createContext, useContext, useState } from 'react';

export interface TodoItem {
    id: number;
    name: string;
}

interface TodoContextType {
  items: TodoItem[];
  addItem: (itemName: string) => void;
  deleteItem: (id: number) => void;
}

const TodoContext = createContext<TodoContextType | null>(null);

export const useTodoContext = () => {
  const context = useContext(TodoContext);
  if (!context) {
    throw new Error('useTodoContext must be used within a TodoContextProvider');
  }
  return context;
};

interface TodoContextProviderProps{
    children: React.ReactNode;
}

export const TodoContextProvider = ({ children }: TodoContextProviderProps) => {
  const [items, setItems] = useState<TodoItem[]>([]);

  const addItem = (itemName: string) => {
    const newItem: TodoItem = {
      id: Date.now(),
      name: itemName,
    };
    setItems([...items, newItem]);
  };

  const deleteItem = (id: number) => {
    const updatedItems = items.filter(item => item.id !== id);
    setItems(updatedItems);
  };

  return (
    <TodoContext.Provider value={{ items, addItem, deleteItem }}>
      {children}
    </TodoContext.Provider>
  );
};
