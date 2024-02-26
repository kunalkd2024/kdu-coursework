import React from "react";
import "./AddItem.scss";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { addItem, clearCompleted, input } from "../redux/ToDoListSlice";

const AddItem: React.FC = () => {
  const newItemName = useSelector(
    (state: RootState) => state.ToDoList.newItemName
  );
  const dispatch = useDispatch();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(input(e.target.value));
  };

  const handleSubmit = (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (newItemName.trim() !== "") {
      dispatch(addItem(newItemName));
      dispatch(input(""));
    }
  };

  return (
    <div id="containerAddItem">
      <h2>Add Items</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" value={newItemName} onChange={handleInputChange} />
        <button type="submit">Submit</button>
        <button type="button" onClick={() => dispatch(clearCompleted())}>Clear completed</button>
      </form>
    </div>
  );
};

export default AddItem;
