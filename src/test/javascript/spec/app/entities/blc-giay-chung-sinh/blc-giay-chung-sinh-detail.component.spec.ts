/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcGiayChungSinhDetailComponent } from 'app/entities/blc-giay-chung-sinh/blc-giay-chung-sinh-detail.component';
import { BlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';

describe('Component Tests', () => {
  describe('BlcGiayChungSinh Management Detail Component', () => {
    let comp: BlcGiayChungSinhDetailComponent;
    let fixture: ComponentFixture<BlcGiayChungSinhDetailComponent>;
    const route = ({ data: of({ blcGiayChungSinh: new BlcGiayChungSinh(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcGiayChungSinhDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BlcGiayChungSinhDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlcGiayChungSinhDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.blcGiayChungSinh).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
