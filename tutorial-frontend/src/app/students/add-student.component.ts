import { Component } from '@angular/core';
import { StudentService } from './student.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Student } from './student';
import { Router } from '@angular/router';
import { Course } from './course';
import { StudentFormValue } from './student-form';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent {

  studentForm!: FormGroup;
  courses: Course[] = [];

  constructor(private fb: FormBuilder,
    private studentService: StudentService,
    private router: Router) {
    this.studentForm = this.fb.group({
      name: [''],
      gender: [''],
      email: [''],
      phone: [''],
      courses: []
    });
  }

  ngOnInit() {
    this.getCourses();
  }

  addStudent(): void {
    const student: StudentFormValue = this.studentForm.value;
    this.studentService.addStudent(student).subscribe({
      next: (value: Student) => {
        this.router.navigate(['/app-student']);
      },
      error: (error: any) => {
        console.error('Error adding student:', error);
      }
    });
  }

  onCoursesChange(target: EventTarget | null) {
    if (target) {
      const selectedOptions = (target as HTMLSelectElement).selectedOptions;
      const courses = Array.from(selectedOptions).map(option => option.value);
      this.studentForm.get('courses')?.setValue(courses);
    }
  }

  getCourses() {
    this.studentService.getCourses()
    .subscribe(courses => this.courses = courses);
  }
}

