import { Store } from './redux/Store';
import { Provider } from 'react-redux';
import {TodoApp} from './App';

describe('<TodoApp />', () => {
  it('renders', () => {
    cy.mount(<Provider store={Store}><TodoApp /></Provider>);
  });

  it('renders the Header container', () => {
    cy.mount(<Provider store={Store}><TodoApp /></Provider>);

    cy.get('.containerHeader').should('exist');
  });

  it('renders the MainSection container', () => {
    cy.mount(<Provider store={Store}><TodoApp /></Provider>);

    cy.get('.containerMainSec').should('exist');
  });
});