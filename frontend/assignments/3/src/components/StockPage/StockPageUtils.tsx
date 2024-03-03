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

      transactionContainer.scrollIntoView({
        behavior: "smooth",
        block: "end",
      });
    }
  }
};
