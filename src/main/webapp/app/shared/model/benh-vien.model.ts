import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';
import { IBlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';

export interface IBenhVien {
  id?: number;
  maBenhVien?: string;
  tenBenhVien?: string;
  ghiChu?: string;
  status?: number;
  blcGiayChungSinh?: IBlcGiayChungSinh;
  blcGiayKhamSucKhoe?: IBlcGiayKhamSucKhoe;
}

export class BenhVien implements IBenhVien {
  constructor(
    public id?: number,
    public maBenhVien?: string,
    public tenBenhVien?: string,
    public ghiChu?: string,
    public status?: number,
    public blcGiayChungSinh?: IBlcGiayChungSinh,
    public blcGiayKhamSucKhoe?: IBlcGiayKhamSucKhoe
  ) {}
}
