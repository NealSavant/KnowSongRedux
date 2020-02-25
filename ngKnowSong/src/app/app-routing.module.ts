import { Board1Component } from './game/board1/board1.component';
import { UserHomeComponent } from './pages/user-home/user-home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './pages/index/index.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AuthorizeComponent } from './spotifyJSON/authorize/authorize.component';
import { CallbackComponent } from './spotifyJSON/callback/callback.component';
import { MatchHistoryComponent } from './game/match-history/match-history.component';


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'index'},
  {path: 'index', component: IndexComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home', component: UserHomeComponent},
  {path: 'game', component: Board1Component},
  {path: 'history', component: MatchHistoryComponent},
  {path: 'authorize', component: AuthorizeComponent},
  {path: 'callback', component: CallbackComponent},
  {path: '**', component: IndexComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
