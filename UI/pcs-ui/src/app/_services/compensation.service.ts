import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Compensation } from '../_models/commpensation';

@Injectable({
  providedIn: 'root'
})
export class CompensationService {

  private BASE_URL = environment.COMPENSATION_BASE_URL

  constructor(private httpClient: HttpClient) { }

  public createCompensation(compensation: Compensation) {
    return this.httpClient.post<Compensation>(this.BASE_URL+'compensation', compensation);
  }

  public getAllCompensation() {
    return this.httpClient.get(this.BASE_URL+'compensation');
  }
  
}
