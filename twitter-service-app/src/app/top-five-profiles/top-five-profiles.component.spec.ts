import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopFiveProfilesComponent } from './top-five-profiles.component';

describe('TopFiveProfilesComponent', () => {
  let component: TopFiveProfilesComponent;
  let fixture: ComponentFixture<TopFiveProfilesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopFiveProfilesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopFiveProfilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
