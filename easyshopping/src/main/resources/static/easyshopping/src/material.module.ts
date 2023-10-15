import { NgModule } from "@angular/core";
import { MatInputModule } from '@angular/material/input'
import { MatSelectModule } from '@angular/material/select'
import { MatCardModule } from '@angular/material/card'
import { MatCheckboxModule } from '@angular/material/checkbox'
import { MatTableModule } from '@angular/material/table'
import { MatSortModule } from '@angular/material/sort'
import { MatDialogModule } from '@angular/material/dialog'
import { MatButtonModule } from '@angular/material/button'
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatIconModule } from '@angular/material/icon'
// import {} from '@angular/material/'




@NgModule({
    exports: [
        MatInputModule,
        MatSelectModule,
        MatCardModule,
        MatCheckboxModule,
        MatTableModule,
        MatSortModule,
        MatDialogModule,
        MatButtonModule,
        MatToolbarModule,
        MatIconModule
    ]

})
export class MaterialModule {

}