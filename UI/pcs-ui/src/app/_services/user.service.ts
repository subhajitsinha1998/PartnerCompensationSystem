import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { UserProfile } from '../_models/userprofile';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private BASE_URL = environment.USER_BASE_URL

  constructor(private httpClient: HttpClient) { }

  public createUser(createUserData: any) {
    return this.httpClient.post(this.BASE_URL+'create-user', createUserData);
  }

  public signIn(signInData: any) {
    return this.httpClient.post(this.BASE_URL+'signin', signInData);
  }

  public getAllUsers() {
    return this.httpClient.get(this.BASE_URL+'users');
  }
  
  public forceResetPassword(newPasswordRequiredData: any) {
    return this.httpClient.post(this.BASE_URL+'newPasswordRequired', newPasswordRequiredData);
  }

  public matchRole(allowedRoles: string[]): boolean {
    const userRole = JSON.parse(localStorage.getItem('userDetails') || '{}').role?.toLowerCase();
    if (userRole){
      for(let i=0; i<= allowedRoles.length; i++){
        if (allowedRoles[i] === userRole) {
          return true
        }
      }
    }
    return false
  }

}
