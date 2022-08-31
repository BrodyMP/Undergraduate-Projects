#include <cassert>
#include "../include/point3d.hpp"

int main () {

	Point3D p;

	/* Test setting coordinates. */
	p.SetCoords(1, 1, 1);

	assert(p.GetX() == 1 && p.GetY() == 1 && p.GetZ() == 1);

	return 0;
}

