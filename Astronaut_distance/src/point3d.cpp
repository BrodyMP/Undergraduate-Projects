/** \file point3d.cpp
 * 
 * @brief Point3D Class implementation
 */

#include "../include/point3d.hpp"
#include <cmath>
#include <iomanip>
#include <iostream>
using namespace std;


//This constructor intitializes the coordinates
Point3D::Point3D()
{
	fPointX = 0;
	fPointY = 0;
	fPointZ = 0;
}

//This contr
Point3D::Point3D(double x, double y, double z)
{
	SetCoords(x, y, z);
}
//Getters, gets the value from the user

double Point3D::GetX() 
{
	return fPointX;
}

double Point3D::GetY()
{
	return fPointY;
}

double Point3D::GetZ()
{
	return fPointZ;
}

//Setter, sets the input in the functions
void Point3D::SetCoords(double dX, double dY, double dZ)
{
fPointX = dX;
fPointY = dY;
fPointZ = dZ;
}


//I didn't see this test case initially, so I programmed
//The distance formula into my base code. I still needed
//to satisfy the 4th test.
double Point3D::DistanceTo(Point3D &p)
{
	double dDist = sqrt(pow((fPointX - p.GetX()), 2.0) + pow((fPointY - p.GetY()), 2.0) + pow((fPointZ - p.GetZ()), 2.0));
	return dDist;
}