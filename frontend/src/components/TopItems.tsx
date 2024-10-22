import { ITopItems } from "@/api/Api.ts";

interface ITopItemsProps {
  items: ITopItems;
}
export function TopItems({ items }: ITopItemsProps) {
  const topItems = items;

  return (
    <div className="grid grid-cols-2 mx-auto border-accent border-2 p-4">
      <div className="flex flex-col gap-4">
        <h1 className="text-lg my-4 underline underline-offset-4 decoration-accent">
          Top Artists
        </h1>
        {topItems?.topArtists.items.map((artist, index) => {
          return (
            <div className="flex items-center gap-2">
              <>
                <div>{index + 1}</div>
                <div>
                  <img
                    className="rounded-full object-cover h-14"
                    src={artist.images[0].url}
                    alt=""
                  />
                </div>
                <div className="text-sm lg:text-lg">{artist.name}</div>
              </>
            </div>
          );
        })}
      </div>
      <div className="flex flex-col gap-4">
        <h1 className="text-lg my-4 underline underline-offset-4 decoration-accent">
          Top Songs
        </h1>
        {topItems?.topTracks.items.map((track, index) => {
          return (
            <div className="h-14">
              <div className="flex items-center gap-4">
                <div>{index + 1}</div>
                <div className="flex flex-col">
                  <div className="lg:text-lg text-sm">{track.name}</div>
                  <div className="lg:text-sm text-xs">{track.album.name}</div>
                </div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}
