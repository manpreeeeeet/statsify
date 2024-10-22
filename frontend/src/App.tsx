import Apol from "./assets/apol.svg";
import { useState } from "react";
import { TopArtists } from "@/components/TopArtists.tsx";
import { Login } from "@/components/Login.tsx";

function App() {
  const [activeNavIndex, setActiveNavIndex] = useState(0);

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
      {/*<TopArtists />*/}
      <Login />
    </div>
  );
}

export default App;
