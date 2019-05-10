/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DoraposTestModule } from '../../../test.module';
import { ShopSectionDeleteDialogComponent } from 'app/entities/shop-section/shop-section-delete-dialog.component';
import { ShopSectionService } from 'app/entities/shop-section/shop-section.service';

describe('Component Tests', () => {
  describe('ShopSection Management Delete Component', () => {
    let comp: ShopSectionDeleteDialogComponent;
    let fixture: ComponentFixture<ShopSectionDeleteDialogComponent>;
    let service: ShopSectionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DoraposTestModule],
        declarations: [ShopSectionDeleteDialogComponent]
      })
        .overrideTemplate(ShopSectionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShopSectionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShopSectionService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
