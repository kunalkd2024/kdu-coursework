import React from "react";
import "./Items.css";

interface TodoItem {
  id: number;
  name: string;
}

interface ItemsProps {
  items: TodoItem[];
  onDelete: (id: number) => void;
}

const Items: React.FC<ItemsProps> = ({ items, onDelete }) => {
  const noItems = items.length === 0;

  return (
    <div id="ContainerItems">
      <h2>Items</h2>
      {noItems && <p style={{ textDecoration: "none" }}>No Match Found</p>}
      {!noItems && (
        <div className="item-container">
          {items.map((item) => (
            <div key={item.id} className="item-row">
              <div>
                <span>{item.name}</span>
              </div>
              <div>
                <button onClick={() => onDelete(item.id)}>X</button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Items;
