import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalDashboardPageComponent } from './professional-dashboard-page.component';

describe('ProfessionalDashboardPageComponent', () => {
  let component: ProfessionalDashboardPageComponent;
  let fixture: ComponentFixture<ProfessionalDashboardPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfessionalDashboardPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessionalDashboardPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
