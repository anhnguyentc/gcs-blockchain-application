import { Moment } from 'moment';
import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';

export interface IBlcAPILog {
  id?: number;
  uuid?: string;
  status?: string;
  messageStatus?: string;
  methodName?: string;
  soGCS?: string;
  soSOGCS?: string;
  requestContent?: string;
  errorMessage?: string;
  loadedTime?: Moment;
  ipAddress?: string;
  idBenhvien?: string;
  tenBenhvien?: string;
  processTime?: number;
  responseTime?: number;
  blcGiayChungSinh?: IBlcGiayChungSinh;
}

export class BlcAPILog implements IBlcAPILog {
  constructor(
    public id?: number,
    public uuid?: string,
    public status?: string,
    public messageStatus?: string,
    public methodName?: string,
    public soGCS?: string,
    public soSOGCS?: string,
    public requestContent?: string,
    public errorMessage?: string,
    public loadedTime?: Moment,
    public ipAddress?: string,
    public idBenhvien?: string,
    public tenBenhvien?: string,
    public processTime?: number,
    public responseTime?: number,
    public blcGiayChungSinh?: IBlcGiayChungSinh
  ) {}
}
