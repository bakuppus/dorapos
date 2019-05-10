/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { DoraposTestModule } from '../../../test.module';
import { SuspensionHistoryUpdateComponent } from 'app/entities/suspension-history/suspension-history-update.component';
import { SuspensionHistoryService } from 'app/entities/suspension-history/suspension-history.service';
import { SuspensionHistory } from 'app/shared/model/suspension-history.model';

describe('Component Tests', () => {
  describe('SuspensionHistory Management Update Component', () => {
    let comp: SuspensionHistoryUpdateComponent;
    let fixture: ComponentFixture<SuspensionHistoryUpdateComponent>;
    let service: SuspensionHistoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DoraposTestModule],
        declarations: [SuspensionHistoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SuspensionHistoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SuspensionHistoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SuspensionHistoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SuspensionHistory(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new SuspensionHistory();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
