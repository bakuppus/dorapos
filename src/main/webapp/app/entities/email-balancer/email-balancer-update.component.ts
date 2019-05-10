import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IEmailBalancer, EmailBalancer } from 'app/shared/model/email-balancer.model';
import { EmailBalancerService } from './email-balancer.service';

@Component({
  selector: 'jhi-email-balancer-update',
  templateUrl: './email-balancer-update.component.html'
})
export class EmailBalancerUpdateComponent implements OnInit {
  emailBalancer: IEmailBalancer;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    relayId: [],
    relayPassword: [],
    startingCount: [],
    endingCount: [],
    provider: [],
    relayPort: [],
    enabled: []
  });

  constructor(protected emailBalancerService: EmailBalancerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ emailBalancer }) => {
      this.updateForm(emailBalancer);
      this.emailBalancer = emailBalancer;
    });
  }

  updateForm(emailBalancer: IEmailBalancer) {
    this.editForm.patchValue({
      id: emailBalancer.id,
      relayId: emailBalancer.relayId,
      relayPassword: emailBalancer.relayPassword,
      startingCount: emailBalancer.startingCount,
      endingCount: emailBalancer.endingCount,
      provider: emailBalancer.provider,
      relayPort: emailBalancer.relayPort,
      enabled: emailBalancer.enabled
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const emailBalancer = this.createFromForm();
    if (emailBalancer.id !== undefined) {
      this.subscribeToSaveResponse(this.emailBalancerService.update(emailBalancer));
    } else {
      this.subscribeToSaveResponse(this.emailBalancerService.create(emailBalancer));
    }
  }

  private createFromForm(): IEmailBalancer {
    const entity = {
      ...new EmailBalancer(),
      id: this.editForm.get(['id']).value,
      relayId: this.editForm.get(['relayId']).value,
      relayPassword: this.editForm.get(['relayPassword']).value,
      startingCount: this.editForm.get(['startingCount']).value,
      endingCount: this.editForm.get(['endingCount']).value,
      provider: this.editForm.get(['provider']).value,
      relayPort: this.editForm.get(['relayPort']).value,
      enabled: this.editForm.get(['enabled']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmailBalancer>>) {
    result.subscribe((res: HttpResponse<IEmailBalancer>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
