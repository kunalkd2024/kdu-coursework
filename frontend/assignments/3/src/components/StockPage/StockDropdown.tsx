import React from "react";
import { Link } from "react-router-dom";
import { MenuItem, Select } from "@mui/material";
import { StocksAPI } from "../../types/stocksTypes";

interface StockDropdownProps {
  stocks: StocksAPI[];
  currentStock: StocksAPI | undefined;
}

/**
 * React component representing a dropdown menu for selecting stocks.
 *
 * @component StockDropdown
 * @param {object} props - The props of the component.
 * @param {StocksAPI[]} props.stocks - An array of stocks.
 * @param {StocksAPI | undefined} props.currentStock - The currently selected stock.
 * @returns {JSX.Element} A React element representing the stock dropdown component.
 */
const StockDropdown: React.FC<StockDropdownProps> = ({
  stocks,
  currentStock,
}) => {
  return (
    <Select
      value={currentStock ? currentStock.stock_symbol : ""}
      style={{
        minWidth: 400,
        outline: "none",
        height: 50,
        border: "none",
      }}
      sx={{
        "& .MuiSelect-outlined": {
          border: "none",
        },
        "& .MuiList-root": {
          padding: 0,
        },
        "& .MuiMenuItem-root": {
          padding: "6px 16px",
          "&:hover": {
            backgroundColor: "rgba(0, 0, 0, 0.04)",
          },
        },
      }}
      onChange={(e) => {
        const selectedStock = e.target.value as string;
        if (selectedStock) {
          window.location.href = `/stock/${selectedStock}`;
        }
      }}
    >
      <MenuItem disabled value="">
        {currentStock
          ? `${currentStock.stock_name} (Current)`
          : "Select a stock"}
      </MenuItem>
      {stocks.map((stock) => (
        <MenuItem key={stock.stock_name} value={stock.stock_symbol}>
          <Link
            to={`/stock/${stock.stock_symbol}`}
            style={{
              textDecoration: "none",
              color: "inherit",
              display: "flex",
              alignItems: "center",
              border: "none",
            }}
          >
            <div
              style={{
                backgroundColor: "#ffec99",
                color: "#f08c00",
                padding: "5px 10px",
                borderRadius: "4px",
                marginRight: "10px",
              }}
            >
              {stock.stock_symbol}
            </div>
            <div style={{ color: "black", fontSize: "larger" }}>
              {stock.stock_name}
            </div>
          </Link>
        </MenuItem>
      ))}
    </Select>
  );
};

export default StockDropdown;
