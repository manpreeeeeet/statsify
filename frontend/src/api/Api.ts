import axios from "axios";

export interface IArtist {
  name: string;
  images: { url: string }[];
}

export interface ITrack {
  name: string;
  album: {
    name: string;
  };
}

export interface ITopItems {
  topTracks: {
    items: ITrack[];
  };
  topArtists: {
    items: IArtist[];
  };
}

export const getTopMediumTerm = async (): Promise<ITopItems> => {
  const response = await axios.get("/api/top-medium-term");
  return response.data;
};
