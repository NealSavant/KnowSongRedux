import { Injectable } from '@angular/core';
import { User } from '../model/user.model';
import { AuthService } from './auth.service';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user: User;
  constructor(
  ) { }

  setUser(user: User){
    this.user = user;
  }

  getUser(): User{
    return this.user; 
  }
}
