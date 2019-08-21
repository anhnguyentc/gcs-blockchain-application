import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GcsBlockchainApplicationSharedModule } from 'app/shared';
import {
  DonViHanhChinhComponent,
  DonViHanhChinhDetailComponent,
  DonViHanhChinhUpdateComponent,
  DonViHanhChinhDeletePopupComponent,
  DonViHanhChinhDeleteDialogComponent,
  donViHanhChinhRoute,
  donViHanhChinhPopupRoute
} from './';

const ENTITY_STATES = [...donViHanhChinhRoute, ...donViHanhChinhPopupRoute];

@NgModule({
  imports: [GcsBlockchainApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DonViHanhChinhComponent,
    DonViHanhChinhDetailComponent,
    DonViHanhChinhUpdateComponent,
    DonViHanhChinhDeleteDialogComponent,
    DonViHanhChinhDeletePopupComponent
  ],
  entryComponents: [
    DonViHanhChinhComponent,
    DonViHanhChinhUpdateComponent,
    DonViHanhChinhDeleteDialogComponent,
    DonViHanhChinhDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GcsBlockchainApplicationDonViHanhChinhModule {}
