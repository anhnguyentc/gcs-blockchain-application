import { Moment } from 'moment';

export interface IAdmUserTokenLogin {
  id?: number;
  userName?: string;
  createdDate?: Moment;
  expirationDate?: Moment;
  loginIP?: string;
  active?: string;
  heThong?: string;
  tokenType?: string;
  status?: number;
}

export class AdmUserTokenLogin implements IAdmUserTokenLogin {
  constructor(
    public id?: number,
    public userName?: string,
    public createdDate?: Moment,
    public expirationDate?: Moment,
    public loginIP?: string,
    public active?: string,
    public heThong?: string,
    public tokenType?: string,
    public status?: number
  ) {}
}
