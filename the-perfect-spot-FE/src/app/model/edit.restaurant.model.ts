import {ImageModel} from './image.model';

export interface EditRestaurantModel{
  id:number,
  name: string,
  prices: string,
  isServingAlcohol: boolean,
  address: string,
  phone: string,
  webSite: string,
  start: string,
  end: string,
  petFriendly: boolean,
  hasCarPark: boolean,
  hasWifi: boolean,
  hasBusinessHall: boolean,
  hasPlayground: boolean,
  hasLiveMusic: boolean,
  hasSmokingPart: boolean,
  accessForDisabled: boolean,

  description: string,
  cuisine: string,
  image: ImageModel,
  lat: number,
  lon:number,
  musicGenre: string,
}
