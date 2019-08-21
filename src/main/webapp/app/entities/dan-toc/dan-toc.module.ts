import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GcsBlockchainApplicationSharedModule } from 'app/shared';
import {
  DanTocComponent,
  DanTocDetailComponent,
  DanTocUpdateComponent,
  DanTocDeletePopupComponent,
  DanTocDeleteDialogComponent,
  danTocRoute,
  danTocPopupRoute
} from './';

const ENTITY_STATES = [...danTocRoute, ...danTocPopupRoute];

@NgModule({
  imports: [GcsBlockchainApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [DanTocComponent, DanTocDetailComponent, DanTocUpdateComponent, DanTocDeleteDialogComponent, DanTocDeletePopupComponent],
  entryComponents: [DanTocComponent, DanTocUpdateComponent, DanTocDeleteDialogComponent, DanTocDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GcsBlockchainApplicationDanTocModule {}
