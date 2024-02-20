import React from "react";
import AddItem from "./AddItem";
import Items from "./Items";
import "./MainSection.css";

interface TodoItem {
  id: number;
  name: string;
}

interface MainSectionProps {
  items: TodoItem[];
  onDelete: (id: number) => void;
  onAdd: (itemName: string) => void;
}

const MainSection: React.FC<MainSectionProps> = ({
  items,
  onDelete,
  onAdd,
}) => {
  return (
    <div id="containerMainSec">
      <AddItem onAdd={onAdd} />
      <Items items={items} onDelete={onDelete} />
    </div>
  );
};

export default MainSection;
