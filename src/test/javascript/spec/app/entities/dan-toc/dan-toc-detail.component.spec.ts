/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { DanTocDetailComponent } from 'app/entities/dan-toc/dan-toc-detail.component';
import { DanToc } from 'app/shared/model/dan-toc.model';

describe('Component Tests', () => {
  describe('DanToc Management Detail Component', () => {
    let comp: DanTocDetailComponent;
    let fixture: ComponentFixture<DanTocDetailComponent>;
    const route = ({ data: of({ danToc: new DanToc(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [DanTocDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DanTocDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DanTocDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.danToc).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
