import { render, screen } from '@testing-library/react';
import { Provider } from 'react-redux';
import  {Store}  from '../redux/Store';
import TodoApp from "../App";

describe('<TodoApp />', () => {
  it('renders without crashing', () => {
    render(
      <Provider store={Store}>
        <TodoApp />
      </Provider>
    );
    expect(screen.getByText(/Add Items/i)).toBeInTheDocument();
  });

  it('renders the Header component', () => {
    render(
      <Provider store={Store}>
        <TodoApp />
      </Provider>
    );
    expect(screen.getByText('Item Lister')).toBeInTheDocument();
    expect(screen.getByPlaceholderText('Search Items...')).toBeInTheDocument();
  });
 
  it('renders the MainSection component', () => {
    render(
      <Provider store={Store}>
        <TodoApp />
      </Provider>
    );
    expect(screen.getByText('Submit')).toBeInTheDocument();
    expect(screen.getByText('Items')).toBeInTheDocument();
  });
});
