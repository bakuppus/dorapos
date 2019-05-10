import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { DoraposSharedModule } from 'app/shared';
import {
  SuspensionHistoryComponent,
  SuspensionHistoryDetailComponent,
  SuspensionHistoryUpdateComponent,
  SuspensionHistoryDeletePopupComponent,
  SuspensionHistoryDeleteDialogComponent,
  suspensionHistoryRoute,
  suspensionHistoryPopupRoute
} from './';

const ENTITY_STATES = [...suspensionHistoryRoute, ...suspensionHistoryPopupRoute];

@NgModule({
  imports: [DoraposSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SuspensionHistoryComponent,
    SuspensionHistoryDetailComponent,
    SuspensionHistoryUpdateComponent,
    SuspensionHistoryDeleteDialogComponent,
    SuspensionHistoryDeletePopupComponent
  ],
  entryComponents: [
    SuspensionHistoryComponent,
    SuspensionHistoryUpdateComponent,
    SuspensionHistoryDeleteDialogComponent,
    SuspensionHistoryDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraposSuspensionHistoryModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
