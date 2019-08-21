import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GcsBlockchainApplicationSharedModule } from 'app/shared';
import {
  BlcGiayChungSinhComponent,
  BlcGiayChungSinhDetailComponent,
  BlcGiayChungSinhUpdateComponent,
  BlcGiayChungSinhDeletePopupComponent,
  BlcGiayChungSinhDeleteDialogComponent,
  blcGiayChungSinhRoute,
  blcGiayChungSinhPopupRoute
} from './';

const ENTITY_STATES = [...blcGiayChungSinhRoute, ...blcGiayChungSinhPopupRoute];

@NgModule({
  imports: [GcsBlockchainApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BlcGiayChungSinhComponent,
    BlcGiayChungSinhDetailComponent,
    BlcGiayChungSinhUpdateComponent,
    BlcGiayChungSinhDeleteDialogComponent,
    BlcGiayChungSinhDeletePopupComponent
  ],
  entryComponents: [
    BlcGiayChungSinhComponent,
    BlcGiayChungSinhUpdateComponent,
    BlcGiayChungSinhDeleteDialogComponent,
    BlcGiayChungSinhDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GcsBlockchainApplicationBlcGiayChungSinhModule {}
