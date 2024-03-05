import React from "react";
import { Snackbar, IconButton, SnackbarContent } from "@mui/material";
import { Close } from "@mui/icons-material";

interface SnackbarComponentProps {
  open: boolean;
  message: string;
  onClose: () => void;
}

/**
 * React component representing a snackbar component.
 *
 * @component SnackbarComponent
 * @param {object} props - The properties of the component.
 * @param {boolean} props.open - Determines if the snackbar is open or not.
 * @param {string} props.message - The message to be displayed in the snackbar.
 * @param {Function} props.onClose - The function to be called when the snackbar is closed.
 * @returns {JSX.Element} A React element representing the snackbar component.
 */
const SnackbarComponent: React.FC<SnackbarComponentProps> = ({
  open,
  message,
  onClose,
}) => {
  return (
    <Snackbar
      open={open}
      autoHideDuration={6000}
      onClose={onClose}
      anchorOrigin={{
        vertical: "top",
        horizontal: "center",
      }}
    >
      <SnackbarContent
        style={{
          backgroundColor: "red",
          borderRadius: "10px",
          fontSize: "large",
        }}
        message={message}
        action={
          <IconButton
            size="small"
            aria-label="close"
            color="inherit"
            onClick={onClose}
          >
            <Close fontSize="small" />
          </IconButton>
        }
      />
    </Snackbar>
  );
};

export default SnackbarComponent;
