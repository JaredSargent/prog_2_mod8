BEGIN PROGRAM
    CREATE empty LinkedList called students
    SET scanner to read user input

    WHILE true DO
        PROMPT "Enter student name (or 'quit' to finish): "
        READ name
        IF name equals "quit" (case-insensitive) THEN
            BREAK loop
        END IF

        PROMPT "Enter student address: "
        READ address

        SET validGPA to false
        WHILE NOT validGPA DO
            PROMPT "Enter student GPA (0.0-4.0): "
            TRY
                READ gpa as double
                IF gpa >= 0.0 AND gpa <= 4.0 THEN
                    SET validGPA to true
                ELSE
                    PRINT "GPA must be between 0.0 and 4.0."
                END IF
            CATCH NumberFormatException
                PRINT "Invalid input. Enter a numeric GPA."
            END TRY
        END WHILE

        CREATE new Student object with name, address, gpa
        ADD Student to students LinkedList
    END WHILE

    SORT students LinkedList by name (ascending)

    OPEN file "students.txt" for writing
    FOR each student in students DO
        WRITE "Name: " + student.name to file
        WRITE "Address: " + student.address to file
        WRITE "GPA: " + student.gpa to file
        WRITE blank line to file
    END FOR
    CLOSE file
    PRINT "Data written to students.txt successfully!"

    CLOSE scanner
END PROGRAM

CLASS Student
    PRIVATE String name
    PRIVATE String address
    PRIVATE double gpa

    CONSTRUCTOR(name, address, gpa)
        SET this.name = name
        SET this.address = address
        SET this.gpa = gpa
    END CONSTRUCTOR

    METHOD getName()
        RETURN name
    END METHOD

    METHOD getAddress()
        RETURN address
    END METHOD

    METHOD getGpa()
        RETURN gpa
    END METHOD
END CLASS
