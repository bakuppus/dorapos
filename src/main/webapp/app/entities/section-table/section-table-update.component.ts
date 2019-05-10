import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISectionTable, SectionTable } from 'app/shared/model/section-table.model';
import { SectionTableService } from './section-table.service';
import { IShopSection } from 'app/shared/model/shop-section.model';
import { ShopSectionService } from 'app/entities/shop-section';

@Component({
  selector: 'jhi-section-table-update',
  templateUrl: './section-table-update.component.html'
})
export class SectionTableUpdateComponent implements OnInit {
  sectionTable: ISectionTable;
  isSaving: boolean;

  shopsections: IShopSection[];

  editForm = this.fb.group({
    id: [],
    tableNumber: [],
    description: [],
    shopSectionsId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected sectionTableService: SectionTableService,
    protected shopSectionService: ShopSectionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sectionTable }) => {
      this.updateForm(sectionTable);
      this.sectionTable = sectionTable;
    });
    this.shopSectionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShopSection[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShopSection[]>) => response.body)
      )
      .subscribe((res: IShopSection[]) => (this.shopsections = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(sectionTable: ISectionTable) {
    this.editForm.patchValue({
      id: sectionTable.id,
      tableNumber: sectionTable.tableNumber,
      description: sectionTable.description,
      shopSectionsId: sectionTable.shopSectionsId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sectionTable = this.createFromForm();
    if (sectionTable.id !== undefined) {
      this.subscribeToSaveResponse(this.sectionTableService.update(sectionTable));
    } else {
      this.subscribeToSaveResponse(this.sectionTableService.create(sectionTable));
    }
  }

  private createFromForm(): ISectionTable {
    const entity = {
      ...new SectionTable(),
      id: this.editForm.get(['id']).value,
      tableNumber: this.editForm.get(['tableNumber']).value,
      description: this.editForm.get(['description']).value,
      shopSectionsId: this.editForm.get(['shopSectionsId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISectionTable>>) {
    result.subscribe((res: HttpResponse<ISectionTable>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackShopSectionById(index: number, item: IShopSection) {
    return item.id;
  }
}
