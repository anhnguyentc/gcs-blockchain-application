import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GcsBlockchainApplicationSharedModule } from 'app/shared';
import {
  BlcAPILogComponent,
  BlcAPILogDetailComponent,
  BlcAPILogUpdateComponent,
  BlcAPILogDeletePopupComponent,
  BlcAPILogDeleteDialogComponent,
  blcAPILogRoute,
  blcAPILogPopupRoute
} from './';

const ENTITY_STATES = [...blcAPILogRoute, ...blcAPILogPopupRoute];

@NgModule({
  imports: [GcsBlockchainApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BlcAPILogComponent,
    BlcAPILogDetailComponent,
    BlcAPILogUpdateComponent,
    BlcAPILogDeleteDialogComponent,
    BlcAPILogDeletePopupComponent
  ],
  entryComponents: [BlcAPILogComponent, BlcAPILogUpdateComponent, BlcAPILogDeleteDialogComponent, BlcAPILogDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GcsBlockchainApplicationBlcAPILogModule {}
