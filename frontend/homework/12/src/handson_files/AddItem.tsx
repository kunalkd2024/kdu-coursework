import React, { useState } from 'react';
import './AddItem.scss';
import { useTodoContext } from '../context/TodoContext';

const AddItem: React.FC = () => {
  const [newItemName, setNewItemName] = useState<string>('');
  const { addItem } = useTodoContext();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNewItemName(e.target.value);
  };

  const handleAddButtonClick = () => {
    if (newItemName.trim() !== '') {
      addItem(newItemName);
      setNewItemName('');
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
