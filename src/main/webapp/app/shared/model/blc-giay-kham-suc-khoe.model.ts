import { Moment } from 'moment';
import { IDonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';
import { IBenhVien } from 'app/shared/model/benh-vien.model';

export interface IBlcGiayKhamSucKhoe {
  id?: number;
  uuid?: string;
  soGiayKSK?: string;
  hoTen?: string;
  gioiTinh?: string;
  tuoi?: number;
  soCMND?: string;
  ngayCap?: Moment;
  noiCap?: string;
  noiO?: string;
  idBenhVien?: string;
  tenBenhVien?: string;
  ngayKham?: string;
  hangBangLai?: string;
  ketLuan?: string;
  bacSyKetLuan?: string;
  pdfGiayKSK?: string;
  status?: number;
  donViHanhChinh?: IDonViHanhChinh;
  benhVien?: IBenhVien;
}

export class BlcGiayKhamSucKhoe implements IBlcGiayKhamSucKhoe {
  constructor(
    public id?: number,
    public uuid?: string,
    public soGiayKSK?: string,
    public hoTen?: string,
    public gioiTinh?: string,
    public tuoi?: number,
    public soCMND?: string,
    public ngayCap?: Moment,
    public noiCap?: string,
    public noiO?: string,
    public idBenhVien?: string,
    public tenBenhVien?: string,
    public ngayKham?: string,
    public hangBangLai?: string,
    public ketLuan?: string,
    public bacSyKetLuan?: string,
    public pdfGiayKSK?: string,
    public status?: number,
    public donViHanhChinh?: IDonViHanhChinh,
    public benhVien?: IBenhVien
  ) {}
}
