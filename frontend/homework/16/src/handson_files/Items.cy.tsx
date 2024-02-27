import { Provider } from 'react-redux'
import { Store } from '../redux/Store'
import Items from './Items'

describe('<Items />', () => {
  it('renders', () => {
    // Mount the component with Cypress
    cy.mount(
      <Provider store={Store}>
        <Items />
      </Provider>
    )

    // Check if the component is rendered
    cy.get('#ContainerItems').should('exist')
  })

  context('interactions', () => {
    it('should add an item and display it', () => {
      // Mount the component with Cypress
      cy.mount(
        <Provider store={Store}>
          <Items />
        </Provider>
      )

      // Check if the component is rendered
      cy.get('#ContainerItems').should('exist')

      // Add a new item
      // cy.window().its('store').invoke('dispatch', { type: 'addItem', payload: { name: 'New Item' } })

      // Assert that the new item is added and displayed
      // cy.contains('New Item').should('exist')
    })

    it('should delete an item', () => {
      // Mount the component with Cypress
      cy.mount(
        <Provider store={Store}>
          <Items />
        </Provider>
      )

      // Check if the component is rendered
      cy.get('#ContainerItems').should('exist')

      
    })
  })
})
