import { Provider } from 'react-redux'
import { Store } from '../redux/Store'
import AddItem from './AddItem'

describe('<AddItem />', () => {
  it('renders', () => {
    // Mount the component with Cypress
    cy.mount(
      <Provider store={Store}>
        <AddItem />
      </Provider>
    )

    // Check if the component is rendered
    cy.get('#containerAddItem').should('exist')
  })

  context('interactions', () => {
    it('should add a new item', () => {
      // Mount the component with Cypress
      cy.mount(
        <Provider store={Store}>
          <AddItem />
        </Provider>
      )

      // Type into the input field
      cy.get('input[type="text"]').type('New Item')

      // Click the submit button
      cy.contains('Submit').click()

    })

    it('should clear completed items', () => {
      // Mount the component with Cypress
      cy.mount(
        <Provider store={Store}>
          <AddItem />
        </Provider>
      )

      // Type into the input field and submit
      cy.get('input[type="text"]').type('New Item').type('{enter}')

      // Click the "Clear completed" button
      cy.contains('Clear completed').click()

      // Assert that the completed item is cleared
      cy.contains('New Item').should('not.exist')
    })
  })
})
