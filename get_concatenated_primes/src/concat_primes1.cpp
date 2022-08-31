/*!
    @mainpage Concat Primes

    @author Brody Miller

    This program creates a string of concanated primes leading to 1000 and prints 5 characters
    from the user input n.

    concat_primes.cpp
*/

/** \file concat_primes.cpp
    \brief Brief explanation...

    Long explination goes here...

    Requires: C++11
*/


#include<iostream>
#include<string>
#include <assert.h>
#include <iomanip>
#include <cmath>
#include <sstream>

/** \brief Brief function explaination

   And a long one...

    @return std::string
*/
std::string get_concatenated_primes()
{
    std::string concat_primes = "";
    //Complete this function

    int i = 0;
    int num = 1000;
    int max = 0;

    std::cin >> num;

    for (int i = 2; i < num; i++)
    {
        max = sqrt(i);
            if (max < 2)
        {
                concat_primes += i;
                concat_primes += '';
                continue;
        }

            for (int x = 2; x <= max; x++)
            {
                if (i % x == 0)
                    break;

                else if (x == max)
                {
                    concat_primes += i + "";
                    concat_primes += '';
                }
            }
    }

    std::cout << concat_primes;

    return concat_primes.substr(0, 1000);
}


std::string get_slice_of_5(const std::string& primes, const int index)
{
    std::string ret = "";
    //Complete this function
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

        //Please add 3 more unit test here/
    }
    else {
        cout << "Please input n: ";
        while (std::cin >> n)
        {
            concat_primes = get_concatenated_primes();
            std::cout << get_slice_of_5(concat_primes, n) << std::endl;
            cout << "Please input n: ";
        }
    }
    return 0;
}
