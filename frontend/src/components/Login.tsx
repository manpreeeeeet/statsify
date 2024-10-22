import Apol from "../assets/apol.svg";
export function Login() {
  return (
    <div className="mx-auto flex flex-col mt-4 items-center border-accent border-2 p-4 rounded-sm text-center">
      <img src={Apol} alt="" className="h-80" />
      <p className="text-lg">
        Check your top artists, songs and other fun stats.
      </p>
      <button className="bg-primary shadow-lg shadow-primary text-background text-lg py-4 px-10 rounded-sm w-fit mt-4 mb-4">
        Login with spotify
      </button>
    </div>
  );
}