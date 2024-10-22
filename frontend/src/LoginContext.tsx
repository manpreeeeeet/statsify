import React, { createContext, useState } from "react";

interface ILoginContext {
  isLoggedIn: boolean;
  setIsLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
}

export const LoginContext = createContext<ILoginContext>({
  isLoggedIn: false,
  setIsLoggedIn: () => false,
});

export const LoginProvider = (props) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <LoginContext.Provider value={{ isLoggedIn, setIsLoggedIn }}>
      {props.children}
    </LoginContext.Provider>
  );
};
