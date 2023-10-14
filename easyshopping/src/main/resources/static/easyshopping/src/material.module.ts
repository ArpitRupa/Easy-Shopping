import { NgModule } from "@angular/core";
import { MatInputModule } from '@angular/material/input'
import { MatSelectModule } from '@angular/material/select'
import { MatCardModule } from '@angular/material/card'
import { MatCheckboxModule } from '@angular/material/checkbox'
import { MatTableModule } from '@angular/material/table'
import { MatSortModule } from '@angular/material/sort'
import { MatDialogModule } from '@angular/material/dialog'




@NgModule({
    exports: [
        MatInputModule,
        MatSelectModule,
        MatCardModule,
        MatCheckboxModule,
        MatTableModule,
        MatSortModule,
        MatDialogModule
    ]

})
export class MaterialModule {

}