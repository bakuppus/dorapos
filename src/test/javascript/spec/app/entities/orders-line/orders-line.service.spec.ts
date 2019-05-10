/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { OrdersLineService } from 'app/entities/orders-line/orders-line.service';
import { IOrdersLine, OrdersLine } from 'app/shared/model/orders-line.model';

describe('Service Tests', () => {
  describe('OrdersLine Service', () => {
    let injector: TestBed;
    let service: OrdersLineService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrdersLine;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(OrdersLineService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new OrdersLine(
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
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

      it('should create a OrdersLine', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new OrdersLine(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a OrdersLine', async () => {
        const returnedFromService = Object.assign(
          {
            ordersLineName: 'BBBBBB',
            ordersLineValue: 'BBBBBB',
            ordersLinePrice: 1,
            ordersLineDescription: 'BBBBBB',
            thumbnailPhoto: 'BBBBBB',
            fullPhoto: 'BBBBBB',
            fullPhotoUrl: 'BBBBBB',
            thumbnailPhotoUrl: 'BBBBBB'
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

      it('should return a list of OrdersLine', async () => {
        const returnedFromService = Object.assign(
          {
            ordersLineName: 'BBBBBB',
            ordersLineValue: 'BBBBBB',
            ordersLinePrice: 1,
            ordersLineDescription: 'BBBBBB',
            thumbnailPhoto: 'BBBBBB',
            fullPhoto: 'BBBBBB',
            fullPhotoUrl: 'BBBBBB',
            thumbnailPhotoUrl: 'BBBBBB'
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

      it('should delete a OrdersLine', async () => {
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
