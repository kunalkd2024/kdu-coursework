import React, { useEffect } from "react";
import "./Items.scss";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { deleteItem, toggleCompletion } from "../redux/ToDoListSlice";
import { useLocalStorageItems } from "../hooks/useLocalStorageItems";

const Items: React.FC = () => {
  const dispatch = useDispatch();
  const items = useSelector((state: RootState) => state.ToDoList.items);
  const searchTerm = useSelector(
    (state: RootState) => state.ToDoList.searchTerm
  );

  const { saveItemsToLocalStorage } = useLocalStorageItems();

  useEffect(() => {
    saveItemsToLocalStorage(items);
  }, [items, saveItemsToLocalStorage]);

  const filteredItems = items.filter((item) =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );
  const noItems = items.length === 0;
  const noFilteredItems = filteredItems.length === 0;

  return (
    <div data-testid="ContainerItemsTest" id="ContainerItems">
      <h2 id="itemsHeading">Items</h2>
      {noItems && <p id="emptyListMessage">List Is Empty.</p>}
      {!noItems && noFilteredItems && (
        <p id="noMatchMessage">No matching items found.</p>
      )}
      {!noItems && !noFilteredItems && (
        <div className="item-container" id="itemsContainer">
          {filteredItems.map((item) => (
            <div key={item.id} className="item-row" id={`item-${item.id}`}>
              <div>
                <input
                  type="checkbox"
                  checked={item.completed}
                  onChange={() => dispatch(toggleCompletion(item.id))}
                  id={`itemCheckbox-${item.id}`}
                />
                <label
                  style={{
                    textDecoration: item.completed ? "line-through" : "none",
                  }}
                  id={`itemName-${item.id}`}
                >
                  {item.name}
                </label>
              </div>
              <div>
                <button
                  onClick={() => dispatch(deleteItem(item.id))}
                  id={`deleteButton-${item.id}`}
                  data-testid="deleteItemButtonTest"
                >
                  X
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Items;
