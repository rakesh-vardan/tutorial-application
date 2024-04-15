import { Course } from "./course";

export class Student {
  constructor(
    public id: number = 0,
    public name: string = '',
    public gender: string = '',
    public email: string = '',
    public phone: string = '',
    public courses: number[] = []
  ) { }
}
