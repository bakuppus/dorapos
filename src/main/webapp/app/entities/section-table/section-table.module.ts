import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { DoraposSharedModule } from 'app/shared';
import {
  SectionTableComponent,
  SectionTableDetailComponent,
  SectionTableUpdateComponent,
  SectionTableDeletePopupComponent,
  SectionTableDeleteDialogComponent,
  sectionTableRoute,
  sectionTablePopupRoute
} from './';

const ENTITY_STATES = [...sectionTableRoute, ...sectionTablePopupRoute];

@NgModule({
  imports: [DoraposSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SectionTableComponent,
    SectionTableDetailComponent,
    SectionTableUpdateComponent,
    SectionTableDeleteDialogComponent,
    SectionTableDeletePopupComponent
  ],
  entryComponents: [
    SectionTableComponent,
    SectionTableUpdateComponent,
    SectionTableDeleteDialogComponent,
    SectionTableDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraposSectionTableModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
