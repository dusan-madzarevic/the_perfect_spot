import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeReportViewComponent } from './recipe-report-view.component';

describe('RecipeReportViewComponent', () => {
  let component: RecipeReportViewComponent;
  let fixture: ComponentFixture<RecipeReportViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipeReportViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipeReportViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
