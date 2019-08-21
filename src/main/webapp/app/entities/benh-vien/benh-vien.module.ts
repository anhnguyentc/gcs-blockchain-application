import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GcsBlockchainApplicationSharedModule } from 'app/shared';
import {
  BenhVienComponent,
  BenhVienDetailComponent,
  BenhVienUpdateComponent,
  BenhVienDeletePopupComponent,
  BenhVienDeleteDialogComponent,
  benhVienRoute,
  benhVienPopupRoute
} from './';

const ENTITY_STATES = [...benhVienRoute, ...benhVienPopupRoute];

@NgModule({
  imports: [GcsBlockchainApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BenhVienComponent,
    BenhVienDetailComponent,
    BenhVienUpdateComponent,
    BenhVienDeleteDialogComponent,
    BenhVienDeletePopupComponent
  ],
  entryComponents: [BenhVienComponent, BenhVienUpdateComponent, BenhVienDeleteDialogComponent, BenhVienDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GcsBlockchainApplicationBenhVienModule {}
