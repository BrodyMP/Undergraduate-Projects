/** 
    @mainpage Outer Space Communications

    @author Brody Miller

    This program determines how long it takes for a message between two astronauts
    to reach each other. It does this by utilizing header files, classes, and array.
    Mathematically it heavily utilizes the distance formula to determine distance.

    Thoughts on the project: This one was really tough. I'd think I was on the right path, and then
    convince myself I wasn't, but then I was, just not in the right way. I tied myself up
    into a knot of confusion, but I managed to find myself out thankfully. Implementing
    code like this is very tough for me as I'm more used to simpler code, but this is an invaluable
    lesson as in the long run using classes and header files will streamline my code vastly.
*/

/** \file main.cpp
    \brief The code to solve the problem should be inside main.cpp
    
    Long explination goes here...

    Requires: C++11
*/

#include "../include/point3d.hpp"
#include <iomanip>
#include <string>
#include <cmath>
#include <iostream>
#include <fstream>
using namespace std;

int main()
{
//Number of satellites
unsigned int uiAmount;

//Distance variables
int dDist1;
int dDist2;

//Used for the math in the distance formula functions
int dFill;
int dEnd;

//The provided Speed of Light
const int iSol = 299792458;

//Indexed so the element can be reaccessed in the array
int iA1Close;
int iA2Close;

//For class creation
double dtX;
double dtY;
double dtZ;

//Output
double dAns;

/*Unlike the satellites, we know there are only 2 astronauts, so I
made the array that size*/
Point3D a_Array[2];

//This loop finds the coordinates of the astronauts and sets them
for(int i =0; i < 2; i++)
{
    cin >> dtX >> dtY >> dtZ;
    a_Array[i].SetCoords(dtX, dtY, dtZ);
}

cin >> uiAmount;

/*Made the array that will grow along with the amount of satellites.
Visual Studio doesn't like uiAmount, but the g++ compiler lets it work*/
Point3D satArr[uiAmount];


//Same as the astronauts, goes through the input for the 
//Satellites and sets them
for (int n = 0; n < uiAmount; n++)
{
    cin >> dtX >> dtY >> dtZ;
    satArr[n].SetCoords(dtX, dtY, dtZ); 
}


//Figures which satellite is the closest to the first Astronaut
for (int satt1 = 0; satt1 < uiAmount; satt1++)
{
    //Distance Formula
    dFill = sqrt(pow((a_Array[0].GetX() - satArr[satt1].GetX()), 2.0)
     + pow((a_Array[0].GetY() - satArr[satt1].GetY()), 2.0)
     + pow((a_Array[0].GetZ() - satArr[satt1].GetZ()), 2.0));

     //If it's the closest then it's indexed
     if ( dFill < dDist1 || satt1 == 0)
     {
         dDist1 = dFill;
         iA1Close = satt1;
     } 
}
//Figures which satellite is the closest to the second Astronaut
for (int satt2 = 0; satt2 < uiAmount; satt2++)
{
    //Distance formula
    dFill = sqrt(pow((a_Array[1].GetX() - satArr[satt2].GetX()), 2.0)
     + pow((a_Array[1].GetY() - satArr[satt2].GetY()), 2.0)
     + pow((a_Array[1].GetZ() - satArr[satt2].GetZ()), 2.0));

    //If it's the closest then it gets indexed
     if ( dFill < dDist2 || satt2 == 0)
     {
         dDist2 = dFill;
         iA2Close = satt2;
     } 
}

//Utilizes the distance formula to determine the distance between the two satellites
dEnd += sqrt(pow(satArr[iA1Close].GetX() - satArr[iA2Close].GetX(), 2.0)
        + pow(satArr[iA1Close].GetY() - satArr[iA2Close].GetY(), 2.0)
        + pow(satArr[iA1Close].GetZ() - satArr[iA2Close].GetZ(), 2.0));


//Adds the values together in order to get a cumulative distance
dEnd += dDist1;
dEnd += dDist2;

//Calculates the speed of light
dAns = (dEnd / iSol);

//Formatting the output to 4 decimal points
cout << fixed << showpoint << setprecision(4);
cout << dAns;
return dAns;
}


