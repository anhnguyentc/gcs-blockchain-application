/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { AdmUserTokenLoginDetailComponent } from 'app/entities/adm-user-token-login/adm-user-token-login-detail.component';
import { AdmUserTokenLogin } from 'app/shared/model/adm-user-token-login.model';

describe('Component Tests', () => {
  describe('AdmUserTokenLogin Management Detail Component', () => {
    let comp: AdmUserTokenLoginDetailComponent;
    let fixture: ComponentFixture<AdmUserTokenLoginDetailComponent>;
    const route = ({ data: of({ admUserTokenLogin: new AdmUserTokenLogin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [AdmUserTokenLoginDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AdmUserTokenLoginDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdmUserTokenLoginDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.admUserTokenLogin).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
