import React from 'react';
import './Items.scss';
import { useTodoContext } from '../context/TodoContext';
import { useSearchContext } from '../context/SearchContext';


const Items: React.FC = () => {
  const { items: allItems, deleteItem } = useTodoContext();
  const { searchTerm } = useSearchContext();

  const filteredItems = allItems.filter((item) =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const noItems = filteredItems.length === 0;

  return (
    <div id="ContainerItems">
      <h2>Items</h2>
      {noItems && <p style={{ textDecoration: 'none' }}>No Match Found</p>}
      {!noItems && (
        <div className="item-container">
          {filteredItems.map((item) => (
            <div key={item.id} className="item-row">
              <div>
                <span>{item.name}</span>
              </div>
              <div>
                <button onClick={() => deleteItem(item.id)}>X</button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Items;
