#include <cassert>
#include "../include/point3d.hpp"

int main () {

	Point3D p(1, 2, 3);

	/* Test getting coordinates. */
	assert(p.GetX() == 1 && p.GetY() == 2 && p.GetZ() == 3);

	return 0;
}

