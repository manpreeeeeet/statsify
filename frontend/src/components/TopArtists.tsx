interface IArtist {
  name: string;
  image: string;
}

interface ITrack {
  songName: string;
  albumName: string;
}

export function TopArtists() {
  const artists: IArtist[] = [
    {
      name: "michael",
      image: "https://i.scdn.co/image/ab6761610000e5eba7c6125e41a43dbd504c5be5",
    },
    {
      name: "michael",
      image: "https://i.scdn.co/image/ab6761610000e5eba7c6125e41a43dbd504c5be5",
    },
    {
      name: "michael",
      image: "https://i.scdn.co/image/ab6761610000e5eba7c6125e41a43dbd504c5be5",
    },
    {
      name: "michael",
      image: "https://i.scdn.co/image/ab6761610000e5eba7c6125e41a43dbd504c5be5",
    },
    {
      name: "michael",
      image: "https://i.scdn.co/image/ab6761610000e5eba7c6125e41a43dbd504c5be5",
    },
  ];

  return (
    <div className="grid grid-cols-2 mx-auto">
      <div className="flex flex-col gap-4">
        <h1 className="text-lg my-4">Top Artists</h1>
        {artists.map((artist, index) => {
          return (
            <div className="flex items-center gap-2">
              <>
                <div>{index + 1}</div>
                <div>
                  <img
                    className="rounded-full object-cover h-14"
                    src={artist.image}
                    alt=""
                  />
                </div>
                <div>{artist.name}</div>
              </>
            </div>
          );
        })}
      </div>
      <div className="flex flex-col gap-4">
        <h1 className="text-lg my-4">Top Songs</h1>
        {artists.map((artist, index) => {
          return (
            <div className="h-14">
              <div className="flex items-center gap-4">
                <div>{index + 1}</div>
                <div className="flex flex-col">
                  <div className="text-lg">{artist.name}</div>
                  <div className="text-sm">{artist.name}</div>
                </div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}
