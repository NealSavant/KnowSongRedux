import { MusixMatchService } from './../../service/API/musix-match.service';
import { SpotifyAPIService } from './../../service/API/spotify-api.service';
import { UserService } from './../../service/Data/user.service';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CreateGameComponent } from './create-game.component';
import { MusicDataService } from './../../game/data/music-data.service';
import { KnowSongComponent } from './../../game/know-song/know-song.component';
import { ReleaseYearComponent } from './../../game/release-year/release-year.component';
import { LyricMatcherComponent } from './../../game/lyric-matcher/lyric-matcher.component';
import { LandingComponent } from './../../component/landing/landing.component';
import { HomeComponent } from './../../component/home/home.component';
import { AppComponent } from './../../app.component';
import { AppRoutingModule } from './../../app-routing.module';
import { ErrorComponent } from './../../error/error.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { HttpClientModule, HttpXhrBackend } from '@angular/common/http';
import { AuthService } from 'src/app/service/API/auth.service'
import { FormsModule } from '@angular/forms';


describe('CreateGameComponent', () => {
  let component: CreateGameComponent;
  let fixture: ComponentFixture<CreateGameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateGameComponent ],
      imports:[ HttpClientModule,AppRoutingModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
