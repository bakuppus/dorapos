/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { EmailBalancerService } from 'app/entities/email-balancer/email-balancer.service';
import { IEmailBalancer, EmailBalancer } from 'app/shared/model/email-balancer.model';

describe('Service Tests', () => {
  describe('EmailBalancer Service', () => {
    let injector: TestBed;
    let service: EmailBalancerService;
    let httpMock: HttpTestingController;
    let elemDefault: IEmailBalancer;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(EmailBalancerService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EmailBalancer(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 0, false);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a EmailBalancer', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new EmailBalancer(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a EmailBalancer', async () => {
        const returnedFromService = Object.assign(
          {
            relayId: 'BBBBBB',
            relayPassword: 'BBBBBB',
            startingCount: 1,
            endingCount: 1,
            provider: 'BBBBBB',
            relayPort: 1,
            enabled: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of EmailBalancer', async () => {
        const returnedFromService = Object.assign(
          {
            relayId: 'BBBBBB',
            relayPassword: 'BBBBBB',
            startingCount: 1,
            endingCount: 1,
            provider: 'BBBBBB',
            relayPort: 1,
            enabled: true
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EmailBalancer', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
