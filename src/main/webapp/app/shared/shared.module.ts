import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { DoraposSharedLibsModule, DoraposSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [DoraposSharedLibsModule, DoraposSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [DoraposSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraposSharedModule {
  static forRoot() {
    return {
      ngModule: DoraposSharedModule
    };
  }
}
