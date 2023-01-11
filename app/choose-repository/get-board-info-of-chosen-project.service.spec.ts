import { TestBed } from '@angular/core/testing';

import { GetRepoInfoOfChosenProjectService } from './get-repo-info-of-chosen-project.service';
import {GetBoardInfoOfChosenProjectService} from "./get-board-info-of-chosen-project.service";

describe('GetBoardInfoOfChosenProjectService', () => {
  let service: GetBoardInfoOfChosenProjectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetBoardInfoOfChosenProjectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
