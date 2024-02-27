import { Provider } from 'react-redux'
import { Store } from '../redux/Store'
import {Header} from './Header'

describe('<Header />', () => {
  it('renders', () => {
    // Mount the component with Cypress
    cy.mount(
      <Provider store={Store}>
        <Header />
      </Provider>
    )

    // Check if the component is rendered
    cy.get('.containerHeader').should('exist')
  })

  context('interactions', () => {
    it('should dispatch search action when typing into the input field', () => {
      // Mount the component with Cypress
      cy.mount(
        <Provider store={Store}>
          <Header />
        </Provider>
      )

      // Type into the input field
      cy.get('input[type="text"]').type('Search Term')

    })
  })
})
