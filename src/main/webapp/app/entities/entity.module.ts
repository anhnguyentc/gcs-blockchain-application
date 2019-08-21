import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'adm-user-token-login',
        loadChildren: () =>
          import('./adm-user-token-login/adm-user-token-login.module').then(m => m.GcsBlockchainApplicationAdmUserTokenLoginModule)
      },
      {
        path: 'blc-api-log',
        loadChildren: () => import('./blc-api-log/blc-api-log.module').then(m => m.GcsBlockchainApplicationBlcAPILogModule)
      },
      {
        path: 'blc-giay-kham-suc-khoe',
        loadChildren: () =>
          import('./blc-giay-kham-suc-khoe/blc-giay-kham-suc-khoe.module').then(m => m.GcsBlockchainApplicationBlcGiayKhamSucKhoeModule)
      },
      {
        path: 'blc-giay-chung-sinh',
        loadChildren: () =>
          import('./blc-giay-chung-sinh/blc-giay-chung-sinh.module').then(m => m.GcsBlockchainApplicationBlcGiayChungSinhModule)
      },
      {
        path: 'blc-register-private-key',
        loadChildren: () =>
          import('./blc-register-private-key/blc-register-private-key.module').then(
            m => m.GcsBlockchainApplicationBlcRegisterPrivateKeyModule
          )
      },
      {
        path: 'dan-toc',
        loadChildren: () => import('./dan-toc/dan-toc.module').then(m => m.GcsBlockchainApplicationDanTocModule)
      },
      {
        path: 'don-vi-hanh-chinh',
        loadChildren: () => import('./don-vi-hanh-chinh/don-vi-hanh-chinh.module').then(m => m.GcsBlockchainApplicationDonViHanhChinhModule)
      },
      {
        path: 'benh-vien',
        loadChildren: () => import('./benh-vien/benh-vien.module').then(m => m.GcsBlockchainApplicationBenhVienModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GcsBlockchainApplicationEntityModule {}
