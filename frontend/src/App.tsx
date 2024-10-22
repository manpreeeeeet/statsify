import Apol from "./assets/apol.svg";
import { useContext, useState } from "react";
import { TopItems } from "@/components/TopItems.tsx";
import { Login } from "@/components/Login.tsx";
import { LoginContext } from "@/LoginContext.tsx";
import { useQuery } from "@tanstack/react-query";
import { getTopMediumTerm, ITopItems } from "@/api/Api.ts";
import { AxiosError, HttpStatusCode } from "axios";

function App() {
  const [activeNavIndex, setActiveNavIndex] = useState(0);
  const { isLoggedIn, setIsLoggedIn } = useContext(LoginContext);

  const {
    data: topItems,
    isSuccess,
    isError,
    isLoading,
    refetch,
    error,
  } = useQuery<ITopItems, AxiosError>({
    queryFn: getTopMediumTerm,
    queryKey: ["GetAllServices"],
  });

  if (isSuccess) {
    setIsLoggedIn(true);
  }

  if (
    error &&
    error.code &&
    Number(error.code) === HttpStatusCode.Unauthorized
  ) {
    setIsLoggedIn(false);
  }

  return (
    <div className="max-w-screen-md mx-auto p-4">
      <div className="flex lg:gap-10 items-end p-1 gap-4">
        <img src={Apol} alt="" className="w-[40px]" />
        <div
          className={`decoration-secondary decoration-wavy text-xl hover:text-secondary-foreground transition-colors duration-200 cursor-pointer ${activeNavIndex == 0 && "underline"}`}
          onClick={() => setActiveNavIndex(0)}
        >
          Top Artists
        </div>
        <div
          className={`decoration-secondary decoration-wavy text-xl hover:text-secondary-foreground transition-colors duration-200 cursor-pointer ${activeNavIndex == 1 && "underline"}`}
          onClick={() => setActiveNavIndex(1)}
        >
          Roast
        </div>
        <div
          className={`decoration-secondary decoration-wavy text-xl hover:text-secondary-foreground transition-colors duration-200 cursor-pointer ${activeNavIndex == 2 && "underline"}`}
          onClick={() => setActiveNavIndex(2)}
        >
          Palette
        </div>
      </div>
      <hr className="border-t-3 border-accent my-2" />
      {isLoggedIn && <TopItems items={topItems} />}
      {!isLoggedIn && <Login />}
    </div>
  );
}

export default App;
