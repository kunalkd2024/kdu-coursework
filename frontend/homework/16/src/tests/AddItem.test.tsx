import { render, fireEvent, screen } from "@testing-library/react";
import { Provider } from "react-redux";
import { Store } from "../redux/Store"; // Assuming store is exported as default
import AddItem from "../handson_files/AddItem";
import { addItem } from "../redux/ToDoListSlice";

describe("AddItem component", () => {
  it("dispatches addItem action on form submission", () => {
    render(
      <Provider store={Store}>
        <AddItem /> 
      </Provider>
    );
    fireEvent.change(screen.getByRole("textbox"), {
      target: { value: "New Item" },
    });
    fireEvent.click(screen.getByText("Submit"));
    const newItem = Store.getState().ToDoList.items[0];
    expect(newItem.name).toEqual("New Item");
    expect(newItem.completed).toBe(false);
  });

  it("clears input value on form submission", () => {
    render(
      <Provider store={Store}>
        <AddItem />
      </Provider>
    );
    fireEvent.change(screen.getByRole("textbox"), {
      target: { value: "New Item" },
    });
    fireEvent.click(screen.getByText("Submit"));
    expect(screen.getByRole("textbox")).toHaveValue("");
  });

  it('dispatches clearCompleted action on "Clear completed" button click', () => {
    Store.dispatch(addItem("Completed Item"));
    render(
      <Provider store={Store}>
        <AddItem />
      </Provider>
    );
    fireEvent.click(screen.getByText("Clear completed"));
    expect(Store.getState().ToDoList.items).not.toContain(
      expect.objectContaining({ completed: true })
    );
  });
});
