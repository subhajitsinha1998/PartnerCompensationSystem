import { Component } from '@angular/core';
import { UserProfile } from '../_models/userprofile';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent{

  constructor (
    private userAuthService: UserAuthService
  ) { }
  
  myProfileData: UserProfile = this.userAuthService.getUserDetails()
  

}
