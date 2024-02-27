import { render, fireEvent, screen } from "@testing-library/react";
import { Provider } from "react-redux";
import { Store } from "../redux/Store"; 
import { Header } from "../handson_files/Header"; 
import { search } from "../redux/ToDoListSlice"; 

describe("<Header />", () => {
    it('dispatches search action on input change', () => {
        render(
          <Provider store={Store}>
            <Header />
          </Provider>
        );
    
        const searchInput = screen.getByPlaceholderText('Search Items...');

    fireEvent.change(searchInput, { target: { value: 'test' } });

    expect(Store.dispatch).toHaveBeenCalledWith(search('test'));
      });

  it("renders search input field with correct placeholder text", () => {
    render(
      <Provider store={Store}>
        <Header />
      </Provider>
    );

    const searchInput = screen.getByPlaceholderText("Search Items...");
    expect(searchInput).toBeInTheDocument();
  });
});
