/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { PassswordMatchService } from './passsword-match.service';

describe('Service: PassswordMatch', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PassswordMatchService]
    });
  });

  it('should ...', inject([PassswordMatchService], (service: PassswordMatchService) => {
    expect(service).toBeTruthy();
  }));
});
