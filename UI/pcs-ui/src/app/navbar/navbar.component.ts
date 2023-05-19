import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor (
    private userAuthService: UserAuthService,
    private userService: UserService,
    private router: Router
  ) { }

  fullname = () => this.isSignedIn() ? this.userAuthService.getFullName() : ''

  isSignedIn = () => this.userAuthService.isSignedIn()

  signOut() {
    this.userAuthService.signOut()
    this.router.navigate(['/sign-in']);
  }

  public allowedRoles(roles: string[]): boolean {
    return this.userService.matchRole(roles);
  }
}
