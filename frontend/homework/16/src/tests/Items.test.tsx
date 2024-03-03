import { render, screen } from '@testing-library/react';
import { Provider } from 'react-redux';
import { Store } from '../redux/Store';
import MainSection from '../handson_files/MainSection'; // Adjust the import path as needed

describe('<MainSection />', () => {
  it('renders the header', () => {
    render(
      <Provider store={Store}>
        <MainSection />
      </Provider>
    );
    expect(screen.getByText('Add Items')).toBeInTheDocument();  
  });

  it('displays a message when the list is empty', () => {
    render(
      <Provider store={Store}>
        <MainSection />
      </Provider>
    );
    expect(screen.getByText('List Is Empty.')).toBeInTheDocument();
  });
});
