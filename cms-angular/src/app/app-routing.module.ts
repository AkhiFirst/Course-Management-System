import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from 'src/app/dashboard/components/dashboard/dashboard.component';
//const routes: Routes = [{ path: '',   redirectTo: '/login', pathMatch: 'full' }];
const routes: Routes = [
  { path: '',   redirectTo: '/login', pathMatch: 'full' },
   { path: 'dashboard', component: DashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
