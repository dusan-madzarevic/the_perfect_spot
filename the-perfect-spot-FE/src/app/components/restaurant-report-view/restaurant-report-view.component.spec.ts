import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantReportViewComponent } from './restaurant-report-view.component';

describe('RestaurantReportViewComponent', () => {
  let component: RestaurantReportViewComponent;
  let fixture: ComponentFixture<RestaurantReportViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestaurantReportViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantReportViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
