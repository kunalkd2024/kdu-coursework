import React, { useEffect } from "react";
import "./Items.scss";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { TodoItem, addItem, deleteItem, toggleCompletion } from "../redux/ToDoListSlice";

const Items: React.FC = () => {
  const dispatch = useDispatch();
  const items = useSelector((state: RootState) => state.ToDoList.items);
  const searchTerm = useSelector(
    (state: RootState) => state.ToDoList.searchTerm
  );
  
  useEffect(() => {
    const storedItems = localStorage.getItem('todoItems');
    if (storedItems) {
      try {
        const parsedItems: string[] = JSON.parse(storedItems).map((item: TodoItem) => item.name);
        if (Array.isArray(parsedItems)) {
          parsedItems.map(name => dispatch(addItem(name)));
        } else {
          console.error('Stored items is not an array:', storedItems);
        }
      } catch (error) {
        console.error('Error parsing stored items:', error);
      }
    }
  }, []);
  
  useEffect(() => {
    localStorage.setItem('todoItems', JSON.stringify(items));
  }, [items]);
  
  const filteredItems = items.filter((item) =>
  item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );
  const noItems = items.length === 0;
  const noFilteredItems = filteredItems.length === 0;


  return (
    <div id="ContainerItems">
      <h2>Items</h2>
      {noItems && <p>List Is Empty.</p>}
      {!noItems && noFilteredItems && <p>No matching items found.</p>}
      {!noItems && !noFilteredItems && (
        <div className="item-container">
          {filteredItems.map((item) => (
            <div key={item.id} className="item-row">
              <div>
                <input
                  type="checkbox"
                  checked={item.completed}
                  onChange={() => dispatch(toggleCompletion(item.id))}
                />
                <label
                  style={{
                    textDecoration: item.completed ? "line-through" : "none",
                  }}
                >
                  {item.name}
                </label>
              </div>
              <div>
                <button onClick={() => dispatch(deleteItem(item.id))}>X</button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Items;
