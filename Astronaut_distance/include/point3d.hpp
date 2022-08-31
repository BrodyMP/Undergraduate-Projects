///Header File for point3D class \file point3d.hpp


#include <iostream>
#include <iomanip>
#ifndef POINT3D_H
#define POINT3D_H

//1-28 4 AM I can satisfy the first test conditions but I cannot get an output no matter 
//how hard I try. I can't code anymore, I've been coding for 20 hours. It's too much.
//I don't have a partner so I can't get any perspectives and nobody will answer my questions
//in the Slack


//Wanted to use the hungarian method here, but I was afraid it would conflict with the test files
//Always better safe than sorry imho, already messed this code up too many times
class Point3D
{
public:

	//Put in a default constructor to initialize the objects
	Point3D();

	//Create another constructor to initialize the points
	Point3D(double fPointX, double fPointY, double fPointZ);

	//Returns the coordinates from the input
	double GetX();
	double GetY();
	double GetZ();

	//Sets the coordinates
	void SetCoords (double dX, double dY, double dZ);

	//I used functions in main to find the distance,
	//but I still needed to satisfy the 4th test
	double DistanceTo(Point3D &);

private:
	double fPointX;
	double fPointY;
	double fPointZ;
};
#endif
