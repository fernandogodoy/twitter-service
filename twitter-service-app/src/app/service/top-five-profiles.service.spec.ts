import { TestBed } from '@angular/core/testing';

import { TopFiveProfilesService } from './top-five-profiles.service';

describe('TopFiveProfilesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TopFiveProfilesService = TestBed.get(TopFiveProfilesService);
    expect(service).toBeTruthy();
  });
});
