import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GcsBlockchainApplicationSharedModule } from 'app/shared';
import {
  BlcGiayKhamSucKhoeComponent,
  BlcGiayKhamSucKhoeDetailComponent,
  BlcGiayKhamSucKhoeUpdateComponent,
  BlcGiayKhamSucKhoeDeletePopupComponent,
  BlcGiayKhamSucKhoeDeleteDialogComponent,
  blcGiayKhamSucKhoeRoute,
  blcGiayKhamSucKhoePopupRoute
} from './';

const ENTITY_STATES = [...blcGiayKhamSucKhoeRoute, ...blcGiayKhamSucKhoePopupRoute];

@NgModule({
  imports: [GcsBlockchainApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BlcGiayKhamSucKhoeComponent,
    BlcGiayKhamSucKhoeDetailComponent,
    BlcGiayKhamSucKhoeUpdateComponent,
    BlcGiayKhamSucKhoeDeleteDialogComponent,
    BlcGiayKhamSucKhoeDeletePopupComponent
  ],
  entryComponents: [
    BlcGiayKhamSucKhoeComponent,
    BlcGiayKhamSucKhoeUpdateComponent,
    BlcGiayKhamSucKhoeDeleteDialogComponent,
    BlcGiayKhamSucKhoeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GcsBlockchainApplicationBlcGiayKhamSucKhoeModule {}
