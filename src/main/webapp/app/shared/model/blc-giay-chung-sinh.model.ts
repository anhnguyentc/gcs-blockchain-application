import { IBlcAPILog } from 'app/shared/model/blc-api-log.model';
import { IBlcRegisterPrivateKey } from 'app/shared/model/blc-register-private-key.model';
import { IDanToc } from 'app/shared/model/dan-toc.model';
import { IDonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';
import { IBenhVien } from 'app/shared/model/benh-vien.model';

export interface IBlcGiayChungSinh {
  id?: number;
  uuid?: string;
  createdDate?: string;
  tenMeNguoiNuoiDuong?: string;
  namSinh?: string;
  diaChiThuongTru?: string;
  soCMNDPassport?: string;
  danToc?: string;
  gioSinh?: string;
  ngayThangNam?: string;
  noiSinh?: string;
  soLanSinh?: string;
  soConHienSong?: string;
  soConTrongLanSinh?: string;
  gioiTinh?: string;
  canNang?: string;
  hienTrang?: string;
  nguoiDoDe?: string;
  tenDuDinh?: string;
  qrCodeImage?: string;
  statusApp?: string;
  userApprove?: string;
  userCreate?: string;
  addressGCS?: string;
  typeGCS?: string;
  so?: string;
  quyenSo?: string;
  emailNDK?: string;
  modifiedDate?: string;
  idBenhVien?: string;
  tenBenhVien?: string;
  idUserCreate?: string;
  publicKey?: string;
  codeSoft?: string;
  ngayThangNamCap?: string;
  noiCap?: string;
  hoTenCha?: string;
  pdfFile?: string;
  status?: string;
  blcAPILog?: IBlcAPILog;
  blcRegisterPrivateKeys?: IBlcRegisterPrivateKey[];
  danToc?: IDanToc;
  donViHanhChinh?: IDonViHanhChinh;
  benhVien?: IBenhVien;
}

export class BlcGiayChungSinh implements IBlcGiayChungSinh {
  constructor(
    public id?: number,
    public uuid?: string,
    public createdDate?: string,
    public tenMeNguoiNuoiDuong?: string,
    public namSinh?: string,
    public diaChiThuongTru?: string,
    public soCMNDPassport?: string,
    public danToc?: string,
    public gioSinh?: string,
    public ngayThangNam?: string,
    public noiSinh?: string,
    public soLanSinh?: string,
    public soConHienSong?: string,
    public soConTrongLanSinh?: string,
    public gioiTinh?: string,
    public canNang?: string,
    public hienTrang?: string,
    public nguoiDoDe?: string,
    public tenDuDinh?: string,
    public qrCodeImage?: string,
    public statusApp?: string,
    public userApprove?: string,
    public userCreate?: string,
    public addressGCS?: string,
    public typeGCS?: string,
    public so?: string,
    public quyenSo?: string,
    public emailNDK?: string,
    public modifiedDate?: string,
    public idBenhVien?: string,
    public tenBenhVien?: string,
    public idUserCreate?: string,
    public publicKey?: string,
    public codeSoft?: string,
    public ngayThangNamCap?: string,
    public noiCap?: string,
    public hoTenCha?: string,
    public pdfFile?: string,
    public status?: string,
    public blcAPILog?: IBlcAPILog,
    public blcRegisterPrivateKeys?: IBlcRegisterPrivateKey[],
    public danToc?: IDanToc,
    public donViHanhChinh?: IDonViHanhChinh,
    public benhVien?: IBenhVien
  ) {}
}
