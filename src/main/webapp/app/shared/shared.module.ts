import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { GcsBlockchainApplicationSharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [GcsBlockchainApplicationSharedCommonModule],
  declarations: [HasAnyAuthorityDirective],
  exports: [GcsBlockchainApplicationSharedCommonModule, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GcsBlockchainApplicationSharedModule {
  static forRoot() {
    return {
      ngModule: GcsBlockchainApplicationSharedModule
    };
  }
}
