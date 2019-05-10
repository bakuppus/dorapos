import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { DoraposSharedModule } from 'app/shared';
import {
  PaymentMethodComponent,
  PaymentMethodDetailComponent,
  PaymentMethodUpdateComponent,
  PaymentMethodDeletePopupComponent,
  PaymentMethodDeleteDialogComponent,
  paymentMethodRoute,
  paymentMethodPopupRoute
} from './';

const ENTITY_STATES = [...paymentMethodRoute, ...paymentMethodPopupRoute];

@NgModule({
  imports: [DoraposSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PaymentMethodComponent,
    PaymentMethodDetailComponent,
    PaymentMethodUpdateComponent,
    PaymentMethodDeleteDialogComponent,
    PaymentMethodDeletePopupComponent
  ],
  entryComponents: [
    PaymentMethodComponent,
    PaymentMethodUpdateComponent,
    PaymentMethodDeleteDialogComponent,
    PaymentMethodDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraposPaymentMethodModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
