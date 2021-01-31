import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.sass']
})
export class ContactComponent implements OnInit {

  latitude = 21.046385;
  longitude = 105.784547;
  constructor() { }

  ngOnInit() {
  }

}
