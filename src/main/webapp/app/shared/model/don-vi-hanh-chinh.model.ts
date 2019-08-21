import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';
import { IBlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';

export interface IDonViHanhChinh {
  id?: number;
  maDonViHanhChinh?: string;
  tenDonViHanhChinh?: string;
  ghiChu?: string;
  status?: number;
  blcGiayChungSinh?: IBlcGiayChungSinh;
  blcGiayKhamSucKhoe?: IBlcGiayKhamSucKhoe;
}

export class DonViHanhChinh implements IDonViHanhChinh {
  constructor(
    public id?: number,
    public maDonViHanhChinh?: string,
    public tenDonViHanhChinh?: string,
    public ghiChu?: string,
    public status?: number,
    public blcGiayChungSinh?: IBlcGiayChungSinh,
    public blcGiayKhamSucKhoe?: IBlcGiayKhamSucKhoe
  ) {}
}
