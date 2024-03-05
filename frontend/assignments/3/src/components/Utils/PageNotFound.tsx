import { useEffect } from "react";

/**
 * React component for displaying a page not found message and redirecting to the main page.
 *
 * @component PageNotFound
 * @returns {JSX.Element} A React element representing the page not found component.
 */
const PageNotFound = () => {
  useEffect(() => {
    const redirectTimer = setTimeout(() => {
      window.location.href = "/dashboard/explore";
    }, 1500);

    return () => clearTimeout(redirectTimer);
  }, []);

  return (
    <div>
      <h1>No Page Found</h1>
      <p>Redirecting to the Main Page...</p>
    </div>
  );
};

export default PageNotFound;
