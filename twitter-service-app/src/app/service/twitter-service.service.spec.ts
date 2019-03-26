import { TestBed } from '@angular/core/testing';

import { TwitterServiceService } from './twitter-service.service';

describe('TwitterServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TwitterServiceService = TestBed.get(TwitterServiceService);
    expect(service).toBeTruthy();
  });
});
