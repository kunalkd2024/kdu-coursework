import { useEffect } from "react";

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
