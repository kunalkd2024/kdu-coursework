import React from "react";
import { Snackbar, IconButton, SnackbarContent } from "@mui/material";
import { Close } from "@mui/icons-material";

interface SnackbarComponentProps {
  open: boolean;
  message: string;
  onClose: () => void;
}

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
