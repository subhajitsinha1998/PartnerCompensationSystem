import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setUserDetails(userDetails: any) {
    localStorage.setItem('userDetails', JSON.stringify(userDetails));
  }

  public getUserDetails(): any {
    return JSON.parse(localStorage.getItem('userDetails') || '{}');
  }

  public getFullName(): string {
    const firstname = JSON.parse(localStorage.getItem('userDetails') || '{}').firstName
    const lastname = JSON.parse(localStorage.getItem('userDetails') || '{}').lastName
    return `${firstname ? firstname : ''} ${lastname ? lastname : ''}`
  }

  public getRoles(): string {
    return JSON.parse(localStorage.getItem('userDetails') || '{}').role;
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('accessToken', jwtToken);
  }

  public getToken(): string {
    return localStorage.getItem('accessToken') || '';
  }

  public clear() {
    localStorage.clear();
  }

  public isSignedIn() {
    return Boolean(this.getRoles() && this.getToken());
  }

  public signOut() {
    localStorage.clear();
  }

}
