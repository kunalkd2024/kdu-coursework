import { Store } from '../redux/Store';
import { Provider } from 'react-redux';
import MainSection from './MainSection';

describe('<MainSection />', () => {
  it('renders AddItem and Items components', () => {
    cy.mount(<Provider store={Store}><MainSection /></Provider>);
  });

  it('renders the AddItem component', () => {
    cy.mount(<Provider store={Store}><MainSection /></Provider>);

    cy.get('#containerAddItem').should('exist');
  });

  it('renders the Items component', () => {
    cy.mount(<Provider store={Store}><MainSection /></Provider>);

    cy.get('#ContainerItems').should('exist');
  });
});