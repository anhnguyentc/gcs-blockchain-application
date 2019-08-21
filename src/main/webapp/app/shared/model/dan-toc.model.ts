import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';

export interface IDanToc {
  id?: number;
  maDanToc?: string;
  tenDanToc?: string;
  ghiChu?: string;
  status?: number;
  blcGiayChungSinh?: IBlcGiayChungSinh;
}

export class DanToc implements IDanToc {
  constructor(
    public id?: number,
    public maDanToc?: string,
    public tenDanToc?: string,
    public ghiChu?: string,
    public status?: number,
    public blcGiayChungSinh?: IBlcGiayChungSinh
  ) {}
}
