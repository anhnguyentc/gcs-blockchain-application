import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GcsBlockchainApplicationSharedModule } from 'app/shared';
import {
  AdmUserTokenLoginComponent,
  AdmUserTokenLoginDetailComponent,
  AdmUserTokenLoginUpdateComponent,
  AdmUserTokenLoginDeletePopupComponent,
  AdmUserTokenLoginDeleteDialogComponent,
  admUserTokenLoginRoute,
  admUserTokenLoginPopupRoute
} from './';

const ENTITY_STATES = [...admUserTokenLoginRoute, ...admUserTokenLoginPopupRoute];

@NgModule({
  imports: [GcsBlockchainApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AdmUserTokenLoginComponent,
    AdmUserTokenLoginDetailComponent,
    AdmUserTokenLoginUpdateComponent,
    AdmUserTokenLoginDeleteDialogComponent,
    AdmUserTokenLoginDeletePopupComponent
  ],
  entryComponents: [
    AdmUserTokenLoginComponent,
    AdmUserTokenLoginUpdateComponent,
    AdmUserTokenLoginDeleteDialogComponent,
    AdmUserTokenLoginDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GcsBlockchainApplicationAdmUserTokenLoginModule {}
