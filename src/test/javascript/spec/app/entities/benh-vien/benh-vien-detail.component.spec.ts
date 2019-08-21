/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BenhVienDetailComponent } from 'app/entities/benh-vien/benh-vien-detail.component';
import { BenhVien } from 'app/shared/model/benh-vien.model';

describe('Component Tests', () => {
  describe('BenhVien Management Detail Component', () => {
    let comp: BenhVienDetailComponent;
    let fixture: ComponentFixture<BenhVienDetailComponent>;
    const route = ({ data: of({ benhVien: new BenhVien(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BenhVienDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BenhVienDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BenhVienDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.benhVien).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
