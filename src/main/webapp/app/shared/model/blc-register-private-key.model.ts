import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';

export interface IBlcRegisterPrivateKey {
  id?: number;
  idBenhVien?: string;
  tenBenhVien?: string;
  uuidAcccount?: string;
  uuidGCSDB?: string;
  idUserCreate?: string;
  codeSoft?: string;
  publicKey?: string;
  addressKey?: string;
  createdDate?: string;
  modifiedDate?: string;
  status?: number;
  blcGiayChungSinh?: IBlcGiayChungSinh;
}

export class BlcRegisterPrivateKey implements IBlcRegisterPrivateKey {
  constructor(
    public id?: number,
    public idBenhVien?: string,
    public tenBenhVien?: string,
    public uuidAcccount?: string,
    public uuidGCSDB?: string,
    public idUserCreate?: string,
    public codeSoft?: string,
    public publicKey?: string,
    public addressKey?: string,
    public createdDate?: string,
    public modifiedDate?: string,
    public status?: number,
    public blcGiayChungSinh?: IBlcGiayChungSinh
  ) {}
}
