/*!
    @mainpage Concat Primes

    @author Brody Miller

    This program creates a string of concanated primes leading to 1000 and prints 5 characters from the user input n.
    
   concat_primes.cpp
*/

/** \file concat_primes.cpp
    \brief This program creates a string of concanated primes leading to 1000 and prints 5 characters from the user input n.
    */

/*!
*
*   This program seeks to create a string of all prime numbers that is 1000 integers long, and then allow the user to 
*   display a 5 character "slice" of this string by inputting the n value. It utilizes 2 functions to do this,
*   'string get_concatenated_primes()' and 'string get_slice_of_five'. The first function creates the string, and the 
*   second function displays the 5 integers according to the users input. The provided main function includes the test
*   test cases and takes the user input. This program assumes that the user input is between 0 and 1000, and that the
*   indexing in the string starts from 0.
* 
*   Requires: C++11
*
*/

#include<iostream>
#include<string>
#include <assert.h>
#include <iomanip>


/** \brief This function creates a string of the first 1000 prime numbers
*/

/*!
*   This function creates a string of the first 1000 prime numbers by using boolean data types and while loops.
*   The value of 'k' was left to our discretion, so I looked up what the 1000th prime number is, and set the value to
*   one more than that. I did this to avoid overloading. I used a 'while' loop so that the loop would terminate when it 
*   hit 7919. I used a boolean variable because it is by far the easiest way to determine if a number is prime or not.
*   If there is not a remainder then the number inherently cannot be prime because it is only divisible by one and itself
*   If the loop detects a nonprime integer, it excludes it from the loop, and vice versa. This is done with a simple 'for'
*   loop and if statement, no more is necessary. It then returns the newly created string
* 
* 
*   @return std::string
*/
std::string get_concatenated_primes()
{
    std::string concat_primes = "";

    //This is the first prime number, and I did not want 1 or 0 to conflict with any math that I had going on
    //I tried setting it from 0 and the program did not like that
    int iNum = 2;

    //The 1000th prime number is 7919 so I set k to one more than that
    int k = 7920;

    //Defining i so it can be used in the loop
    int i = 0;

    //Using a while loop so that it will stop when it hits 7919, the 1000th prime number
    while (iNum <= k)
    {

        //Using a boolean variable because it is the easiest way to determine if a number
        //is prime, similar concept to determining if a number is even or odd
        bool bPrime = true;

        //If it is not a prime number, it does not add it to the string
        for (i = 2; i <= (iNum / 2); i++)
        {

            if (iNum % i == 0)
            {
                bPrime = false;
                break;
            }
        }

        //If it is a prime number, it adds it to the string
        if (bPrime == true)
        {
            concat_primes = concat_primes + std::to_string(iNum);
        }

        //Adds the value to the string
        iNum++;
    }

    //Returns it to the main function
    return concat_primes.substr(0, 1000);
}


/* /brief This function prints a slice of 5 integers in the created string
*/

/*!
*   This function just takes the user input and references the string for where it goes using the & operator.
*  
*/
std::string get_slice_of_5(const std::string& primes, const int index)
{
    std::string ret = "";

    //Primes refers to the string created by the first function,
    //while index refers to the start of the string from the user
    //input.
    ret = primes.substr(index, 5);

    return ret;
}

int main(int argc, char* argv[]) {
    using namespace std;
    int n;
    string concat_primes;
    if (argc >= 2) {
        concat_primes = get_concatenated_primes();
        assert(get_slice_of_5(concat_primes, 3) == "71113");
        cout << "Unit Test 1 passed\n";
        assert(get_slice_of_5(concat_primes, 6) == "13171");
        cout << "Unit Test 2 passed\n";
        assert(get_slice_of_5(concat_primes, 10) == "19232");
        cout << "Unit Test 3 passed\n";
        assert(get_slice_of_5(concat_primes, 120) == "92332");
        cout << "Unit Test 4 passed\n";
        assert(get_slice_of_5(concat_primes, 998) == "91");
        cout << "Unit Test 5 passed\n";

        //I included the provided test cases from the assignment pdf to be safe
    }
    else {
        cout << "Please input n: ";
        while (std::cin >> n)
        {
            concat_primes = get_concatenated_primes();
            std::cout << get_slice_of_5(concat_primes, n) << std::endl;

            //I revised the output for the question to improve user-friendliness
            cout << "Please input an integer: ";
        }
    }
    return 0;
}