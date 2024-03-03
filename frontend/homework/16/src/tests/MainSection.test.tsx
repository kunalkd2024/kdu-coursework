import { render, screen } from '@testing-library/react';
import { Provider } from 'react-redux';
import { Store } from '../redux/Store';
import MainSection from '../handson_files/MainSection';

describe('<MainSection />', () => {
  it('renders AddItem and Items components', () => {
    render(
      <Provider store={Store}>
        <MainSection />
      </Provider>
    );
    expect(screen.getByRole('textbox')).toBeInTheDocument();  
    expect(screen.getByText('Items')).toBeInTheDocument();  
  });

  it('passes props to AddItem and Items components', () => {
    render(
      <Provider store={Store}>
        <MainSection />
      </Provider>
    );
    expect(screen.getByRole('textbox')).toHaveValue(''); 
    expect(screen.getByText('List Is Empty.')).toBeInTheDocument(); 
  });
});
