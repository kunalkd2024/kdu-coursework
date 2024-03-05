import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Header } from "../Utils/Header";
import { AppDispatch, RootState } from "../../redux/store/store";
import { useEffect } from "react";
import { fetchStocks } from "../../thunks/getStocks";
import TableContainer from "@mui/material/TableContainer";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableBody from "@mui/material/TableBody";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import Pagination from "@mui/material/Pagination";
import CloseIcon from "@mui/icons-material/Close";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import CircularProgress from "@mui/material/CircularProgress";
import {
  addToWatchlist,
  deleteFromWatchlist,
} from "../../redux/slices/StocksSlice";
import { StocksAPI } from "../../types/stocksTypes";
import { TableFooter } from "@mui/material";

/**
 * React component representing the explore dashboard page.
 *
 * @component DashboardExplore
 * @returns {JSX.Element} A React element representing the explore dashboard page.
 */
export function DashboardExplore() {
  const dispatch: AppDispatch = useDispatch();
  const { stocks, favorites, loading } = useSelector(
    (state: RootState) => state.stocksData
  );
  const [page, setPage] = useState(1);
  const [hoveredStock, setHoveredStock] = useState<string | null>(null);

  useEffect(() => {
    const delay = setTimeout(() => {
      dispatch(fetchStocks());
    }, 1500);

    return () => clearTimeout(delay);
  }, []);

  const handlePageChange = (
    _event: React.ChangeEvent<unknown>,
    value: number
  ) => {
    setPage(value);
  };

  const handleAddToWatchlist = (stock: StocksAPI) => {
    dispatch(addToWatchlist(stock));
  };

  const handleRemoveFromWatchlist = (stock: StocksAPI) => {
    dispatch(deleteFromWatchlist(stock));
  };

  const isStockInFavorites = (stock: StocksAPI) => {
    return favorites.some(
      (favStock) => favStock.stock_name === stock.stock_name
    );
  };

  const handleMouseEnter = (stock: StocksAPI) => {
    setHoveredStock(stock.stock_symbol);
  };

  const handleMouseLeave = () => {
    setHoveredStock(null);
  };

  return (
    <div>
      <Header />
      {loading === "pending" ? (
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            marginTop: "80px",
          }}
        >
          <CircularProgress size={80} />
        </div>
      ) : (
        <div>
          <div style={{ display: "flex", flexDirection: "row" }}>
            <Link to="/dashboard/explore" style={{ textDecoration: "none" }}>
              <div
                style={{
                  margin: "10px",
                  borderBottom: "3px solid #468ccd",
                  marginLeft: "0",
                  cursor: "pointer",
                  color: "black",
                  fontSize: "smaller",
                }}
              >
                Explore
              </div>
            </Link>
            <Link to="/dashboard/watchlist" style={{ textDecoration: "none" }}>
              <div
                style={{
                  margin: "10px",
                  cursor: "pointer",
                  color: "black",
                  fontSize: "smaller",
                }}
              >
                My WatchList
              </div>
            </Link>
          </div>
          <div style={{ margin: "5px auto", maxWidth: "calc(100% - 350px)" }}>
            <TableContainer
              component={Paper}
              sx={{
                borderRadius: "16px",
                border: "3px solid black",
              }}
            >
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell
                      sx={{
                        borderBottom: "3px solid black",
                        paddingLeft: "50px",
                        fontSize: "1.1rem",
                      }}
                    >
                      Company
                    </TableCell>
                    <TableCell
                      sx={{
                        width: "15%",
                        textAlign: "center",
                        borderBottom: "3px solid black",
                        fontSize: "1.1rem",
                      }}
                    >
                      Base Price
                    </TableCell>
                    <TableCell
                      sx={{
                        width: "15%",
                        textAlign: "center",
                        borderBottom: "3px solid black",
                        paddingRight: "50px",
                        fontSize: "1.1rem",
                      }}
                    >
                      Actions
                    </TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {stocks
                    .slice((page - 1) * 7, page * 7)
                    .map((stock, index) => (
                      <TableRow key={index}>
                        <TableCell
                          sx={{ paddingLeft: "50px", fontSize: "1.1rem" }}
                        >
                          <Link
                            to={`/stock/${stock.stock_symbol}`}
                            style={{ textDecoration: "none", color: "black" }}
                          >
                            {stock.stock_name}
                          </Link>
                        </TableCell>
                        <TableCell
                          sx={{ textAlign: "center", fontSize: "1.1rem" }}
                        >
                          &#8377;{stock.base_price}
                        </TableCell>
                        <TableCell
                          sx={{
                            textAlign: "center",
                            paddingRight: "50px",
                            fontSize: "1.1rem",
                          }}
                          onMouseEnter={() => handleMouseEnter(stock)}
                          onMouseLeave={handleMouseLeave}
                        >
                          {isStockInFavorites(stock) ? (
                            hoveredStock === stock.stock_symbol ? (
                              <CloseIcon
                                style={{
                                  color:
                                    hoveredStock === stock.stock_symbol
                                      ? "#f00"
                                      : "#2a7bc6",
                                  cursor: "pointer",
                                }}
                                onClick={() => handleRemoveFromWatchlist(stock)}
                              />
                            ) : (
                              <CheckCircleIcon
                                style={{
                                  color:
                                    hoveredStock === stock.stock_symbol
                                      ? "#f00"
                                      : "#2a7bc6",
                                  cursor: "pointer",
                                }}
                                onClick={() => handleRemoveFromWatchlist(stock)}
                              />
                            )
                          ) : (
                            <AddCircleOutlineOutlinedIcon
                              style={{
                                color:
                                  hoveredStock === stock.stock_symbol
                                    ? "#2a7bc6"
                                    : "#2a7bc6",
                                cursor: "pointer",
                              }}
                              onClick={() => handleAddToWatchlist(stock)}
                            />
                          )}
                        </TableCell>
                      </TableRow>
                    ))}
                </TableBody>
                <TableFooter>
                  <TableRow>
                    <TableCell colSpan={3} align="center">
                      <Pagination
                        count={Math.ceil(stocks.length / 7)}
                        shape="rounded"
                        page={page}
                        onChange={handlePageChange}
                        sx={{
                          display: "flex",
                          justifyContent: "center",
                          alignItems: "center",
                          "& .MuiPaginationItem-root": {
                            borderRadius: "50%",
                            fontSize: "1.2rem",
                            color: "#2a7bc6",
                          },
                        }}
                      />
                    </TableCell>
                  </TableRow>
                </TableFooter>
              </Table>
            </TableContainer>
          </div>
        </div>
      )}
    </div>
  );
}
