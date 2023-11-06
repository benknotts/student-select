# student-select

 A program to select students randomly or put them into groups, written for my Java class at STA

 The program must first be built with `javac Students.java`, and a file must be created (by default `students.txt`) with the list of students in it, with each student on a new line.

## Usage

 To select one student at random, simply run `java Students`

 To select a number of students at random, run `java Students [number of students]`

 To sort the students into random groups, run `java Students groups [group size]`

 To get a random number between 1 and 2, run `java Students coin`

 To get a random number between 1 and n, run `java Students die n`

### Example usage

 ```bash
 : java Students
 Charlie
 : java Students 2
 Bob
 Alice
 : java Students groups 2
 Bob and David
 Charlie and Alice
 : java Students groups 3
 Alice and David and Bob
 Charlie
 : java Students coin
 1
 : java Students die 4
 2
 ```
