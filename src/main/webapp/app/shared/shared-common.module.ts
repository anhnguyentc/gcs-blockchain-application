import { NgModule } from '@angular/core';

import { GcsBlockchainApplicationSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [GcsBlockchainApplicationSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [GcsBlockchainApplicationSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class GcsBlockchainApplicationSharedCommonModule {}
