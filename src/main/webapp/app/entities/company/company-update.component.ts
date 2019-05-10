import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { ICompany, Company } from 'app/shared/model/company.model';
import { CompanyService } from './company.service';

@Component({
  selector: 'jhi-company-update',
  templateUrl: './company-update.component.html'
})
export class CompanyUpdateComponent implements OnInit {
  company: ICompany;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    companyName: [null, [Validators.required]],
    description: [],
    note: [],
    companyLogo: [],
    companyLogoContentType: [],
    companyLogoUrl: [],
    active: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected companyService: CompanyService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ company }) => {
      this.updateForm(company);
      this.company = company;
    });
  }

  updateForm(company: ICompany) {
    this.editForm.patchValue({
      id: company.id,
      companyName: company.companyName,
      description: company.description,
      note: company.note,
      companyLogo: company.companyLogo,
      companyLogoContentType: company.companyLogoContentType,
      companyLogoUrl: company.companyLogoUrl,
      active: company.active
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const company = this.createFromForm();
    if (company.id !== undefined) {
      this.subscribeToSaveResponse(this.companyService.update(company));
    } else {
      this.subscribeToSaveResponse(this.companyService.create(company));
    }
  }

  private createFromForm(): ICompany {
    const entity = {
      ...new Company(),
      id: this.editForm.get(['id']).value,
      companyName: this.editForm.get(['companyName']).value,
      description: this.editForm.get(['description']).value,
      note: this.editForm.get(['note']).value,
      companyLogoContentType: this.editForm.get(['companyLogoContentType']).value,
      companyLogo: this.editForm.get(['companyLogo']).value,
      companyLogoUrl: this.editForm.get(['companyLogoUrl']).value,
      active: this.editForm.get(['active']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompany>>) {
    result.subscribe((res: HttpResponse<ICompany>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
