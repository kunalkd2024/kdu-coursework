import React from "react";
import "./Items.scss";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { deleteItem, toggleCompletion } from "../redux/ToDoListSlice";

const Items: React.FC = () => {
  const dispatch = useDispatch();
  const items = useSelector((state: RootState) => state.ToDoList.items);
  const noItems = items.length === 0;
  const searchTerm = useSelector(
    (state: RootState) => state.ToDoList.searchTerm
  );
  const filteredItems = items.filter((item) =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div id="ContainerItems">
      <h2>Items</h2>
      {noItems && <p>No Match Found</p>}
      {!noItems && (
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
