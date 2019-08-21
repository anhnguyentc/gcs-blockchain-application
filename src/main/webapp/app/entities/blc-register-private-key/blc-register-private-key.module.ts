import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GcsBlockchainApplicationSharedModule } from 'app/shared';
import {
  BlcRegisterPrivateKeyComponent,
  BlcRegisterPrivateKeyDetailComponent,
  BlcRegisterPrivateKeyUpdateComponent,
  BlcRegisterPrivateKeyDeletePopupComponent,
  BlcRegisterPrivateKeyDeleteDialogComponent,
  blcRegisterPrivateKeyRoute,
  blcRegisterPrivateKeyPopupRoute
} from './';

const ENTITY_STATES = [...blcRegisterPrivateKeyRoute, ...blcRegisterPrivateKeyPopupRoute];

@NgModule({
  imports: [GcsBlockchainApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BlcRegisterPrivateKeyComponent,
    BlcRegisterPrivateKeyDetailComponent,
    BlcRegisterPrivateKeyUpdateComponent,
    BlcRegisterPrivateKeyDeleteDialogComponent,
    BlcRegisterPrivateKeyDeletePopupComponent
  ],
  entryComponents: [
    BlcRegisterPrivateKeyComponent,
    BlcRegisterPrivateKeyUpdateComponent,
    BlcRegisterPrivateKeyDeleteDialogComponent,
    BlcRegisterPrivateKeyDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GcsBlockchainApplicationBlcRegisterPrivateKeyModule {}
