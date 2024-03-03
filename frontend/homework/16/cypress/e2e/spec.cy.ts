import '@testing-library/cypress/add-commands';
describe("Add Item", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173"); 
  });

  it("should add a new item to the list", () => {
    const newItemName = "New Item";

    cy.findByTestId("addItemInputTest").type(newItemName);

    cy.findByTestId("addItemSubmitButtonTest").click();

    cy.findByTestId("ContainerItemsTest").should("contain", newItemName);
  });

  it("should not add an empty item to the list", () => {
    cy.findByTestId("addItemSubmitButtonTest").click();

    cy.findByTestId("ContainerItemsTest").should("not.contain", "List is Empty.");
  });
});

describe("Delete Item", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173"); 
  });

  it("should delete an item from the list", () => {
    const newItemName = "New Item";
    cy.findByTestId("addItemInputTest").type(newItemName);
    cy.findByTestId("addItemSubmitButtonTest").click();
    cy.findByTestId("ContainerItemsTest").should("contain", newItemName);

    cy.findByTestId("deleteItemButtonTest").click();

    cy.findByTestId("ContainerItemsTest").should("not.contain", newItemName);
  });
});

describe("Search Item", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173"); 
  });

  it("should filter items based on search term", () => {
    const itemNames = ["Apple", "Banana", "Orange"];
    itemNames.forEach((itemName) => {
      cy.findByTestId("addItemInputTest").type(itemName);
      cy.findByTestId("addItemSubmitButtonTest").click();
    });

    const searchTerm = "Banana";
    cy.findByTestId("searchInputTest").type(searchTerm);

    cy.findByTestId("ContainerItemsTest").should("contain", searchTerm);
    cy.findByTestId("ContainerItemsTest").should("not.contain", "Apple");
    cy.findByTestId("ContainerItemsTest").should("not.contain", "Orange");
  });
});