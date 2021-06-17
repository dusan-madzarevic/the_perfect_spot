import { TestBed } from '@angular/core/testing';

import { Filter.RecipeService } from './filter.recipe.service';

describe('Filter.RecipeService', () => {
  let service: Filter.RecipeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Filter.RecipeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
