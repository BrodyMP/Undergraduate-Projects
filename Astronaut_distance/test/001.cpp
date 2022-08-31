#include <cassert>
#include "../include/point3d.hpp"

int main () {

	/* Default constructor. */
	Point3D p1;

	assert(p1.GetX() == 0 && p1.GetY() == 0 && p1.GetZ() == 0);

	/* Parameterized constructor. */
	Point3D p2(1, 1, 1);

	assert(p2.GetX() == 1 && p2.GetY() == 1 && p2.GetZ() == 1);

	return 0;
}

