import { Component, OnInit } from '@angular/core';
import { toArray } from 'rxjs';
import { UserProfile } from '../_models/userprofile';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

  constructor (
    private userService: UserService
  ) { }

  listOfUser: any

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(
      (response: any) => {
        this.listOfUser = response
      },
      (error) => {
        console.log(error)
      }
    )
  }

}
