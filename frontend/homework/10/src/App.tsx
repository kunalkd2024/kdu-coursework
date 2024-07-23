import React, { useState } from "react";
import Header from "./handson_files/Header";
import MainSection from "./handson_files/MainSection";
import "./App.css";

interface TodoItem {
  id: number;
  name: string;
}

const TodoApp: React.FC = () => {
  const [items, setItems] = useState<TodoItem[]>([]);
  const [searchTerm, setSearchTerm] = useState<string>("");

  const handleAddItem = (itemName: string) => {
    const newItem: TodoItem = {
      id: Date.now(),
      name: itemName,
    };
    setItems([...items, newItem]);
  };

  const handleDeleteItem = (id: number) => {
    const updatedItems = items.filter((item) => item.id !== id);
    setItems(updatedItems);
  };

  const handleSearch = (term: string) => {
    setSearchTerm(term);
  };

  const filteredItems = items.filter((item) =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div id="containerApp">
      <Header onSearch={handleSearch} />
      <MainSection
        items={filteredItems}
        onDelete={handleDeleteItem}
        onAdd={handleAddItem}
      />
    </div>
  );
};

export default TodoApp;
