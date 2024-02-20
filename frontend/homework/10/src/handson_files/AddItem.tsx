import React, { useState } from "react";
import "./AddItem.css";

interface AddItemProps {
  onAdd: (itemName: string) => void;
}

const AddItem: React.FC<AddItemProps> = ({ onAdd }) => {
  const [newItemName, setNewItemName] = useState<string>("");

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNewItemName(e.target.value);
  };

  const handleAddButtonClick = () => {
    if (newItemName.trim() !== "") {
      onAdd(newItemName);
      setNewItemName("");
    }
  };

  return (
    <div id="containerAddItem">
      <h2>Add Items</h2>
      <input type="text" value={newItemName} onChange={handleInputChange} />
      <button onClick={handleAddButtonClick}>Submit</button>
    </div>
  );
};

export default AddItem;
