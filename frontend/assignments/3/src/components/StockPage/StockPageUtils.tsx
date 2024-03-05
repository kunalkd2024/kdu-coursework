/**
 * Adds a new bar to the graph based on the provided parameters.
 *
 * @function addBarToGraph
 * @param {number | undefined} newPrice - The new price to be represented by the bar.
 * @param {boolean} isPositive - Indicates whether the price change is positive or negative.
 * @param {number | undefined} basePrice - The base price for comparison.
 * @returns {void}
 */
export const addBarToGraph = (
  newPrice: number | undefined,
  isPositive: boolean,
  basePrice: number | undefined
) => {
  const graphBars = document.querySelector(".graphBars");
  if (graphBars) {
    const bar = document.createElement("div");
    bar.classList.add("bar");
    bar.style.backgroundColor = isPositive ? "#2f9e44" : "#e03131";
    bar.style.width = "19.4px";
    bar.style.border = isPositive ? "1px solid #b2f2bb" : "1px solid #ffc9c9";
    const barHeight = Math.abs(newPrice! - (basePrice ? basePrice : 0)) + "px";
    bar.style.height = barHeight;
    graphBars.appendChild(bar);
  }
};

/**
 * Adds a new transaction to the history section based on the provided parameters.
 *
 * @function addToHistory
 * @param {number} quantity - The quantity of stocks involved in the transaction.
 * @param {string} type - The type of transaction (e.g., "BUY", "SELL").
 * @param {React.RefObject<HTMLDivElement>} historySectionRef - Reference to the history section container.
 * @returns {void}
 */
export const addToHistory = (
  quantity: number,
  type: string,
  historySectionRef: React.RefObject<HTMLDivElement>
) => {
  if (quantity > 0 && type) {
    const historySection = historySectionRef.current;
    if (historySection) {
      const transactionContainer = document.createElement("div");
      transactionContainer.classList.add("transaction");
      transactionContainer.style.display = "flex";
      transactionContainer.style.flexDirection = "row";
      transactionContainer.style.gap = "60px";
      transactionContainer.style.border = "1px solid black";
      transactionContainer.style.margin = "10px";
      transactionContainer.style.padding = "0 10px";
      transactionContainer.style.borderRadius = "10px";

      const leftColumn = document.createElement("div");
      leftColumn.style.display = "flex";
      leftColumn.style.flexDirection = "column";

      const quantityRow = document.createElement("div");
      quantityRow.textContent = `${quantity} stocks`;
      leftColumn.appendChild(quantityRow);

      const dateRow = document.createElement("div");
      const currentDate = new Date().toUTCString();
      dateRow.textContent = `${currentDate}`;
      dateRow.style.fontSize = "small";
      leftColumn.appendChild(dateRow);

      const rightColumn = document.createElement("div");
      rightColumn.textContent = `${type}`;
      rightColumn.style.color = type === "BUY" ? "#2f9e44" : "#e03131";
      rightColumn.style.paddingTop = "10px";

      transactionContainer.appendChild(leftColumn);
      transactionContainer.appendChild(rightColumn);

      historySection.appendChild(transactionContainer);

      // Scroll to the newly added transaction container
      transactionContainer.scrollIntoView({
        behavior: "smooth",
        block: "end",
      });
    }
  }
};
