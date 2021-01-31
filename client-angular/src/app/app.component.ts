import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { SidenavMenu } from './components/shared/sidebar/sidebar-menu.model';
import { Router, NavigationEnd } from '@angular/router';
import { AppSettings, Settings } from './components/shared/services/color-option.service';
import { TokenStorageService } from './_services/token-storage.service';
import {Title} from '@angular/platform-browser';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  public url : any;

  public sidenavMenuItems:Array<any>;

  title = 'Khoithuong Corp';
  scrollElem;
  public settings: Settings;

  isLoggedIn = false;
  username: string;

  constructor(private spinner: NgxSpinnerService,
              public router: Router,
              public appSettings:AppSettings,
              private tokenStorageService: TokenStorageService,
              private titleService: Title) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.url = event.url;
      }
      window.scrollTo(0, 0)
    } );

    this.router.events.subscribe(evt => {
      if (evt instanceof NavigationEnd) {
           document.body.scrollTop = 0; // scroll top to body element
      }
  });

  this.settings = this.appSettings.settings;
  }
  ngAfterViewInit(){
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
          window.scrollTo(0,0);
      }
    })
  }


  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();

      this.username = user.username;
      this.titleService.setTitle('Khoithuong Corp');
    }

    /** spinner starts on init */
    this.spinner.show();

    setTimeout(() => {
      /** spinner ends after 5 seconds */
      this.spinner.hide();
    }, 5000);

    this.scrollElem = document.querySelector('#moveTop');
    // this.scrollElem.scrollIntoView();
  }


  onActivate(event) {
    const scrollToTop = window.setInterval(() => {
        const pos = window.pageYOffset;
        if (pos > 0) {
            window.scrollTo(0, pos - 20); // how far to scroll on each step
        } else {
            window.clearInterval(scrollToTop);
        }
    }, 16);
}

}
