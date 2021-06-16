import {ImageModel} from './image.model';

export interface RestaurantModel {
  id:number,
  name: string,
  grade: number,
  address: string,
  phone: string,
  webSite: string,
  workingHours: string,
  petFriendly: boolean,
  hasCarPark: boolean,
  hasWifi: boolean,
  hasBusinessHall: boolean,
  hasPlayground: boolean,
  hasLiveMusic: boolean,
  hasSmokingPart: boolean,
  description: string,
  cuisine: string,
  image: ImageModel,
}
