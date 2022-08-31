#include <cassert>
#include <cmath>
#include "../include/point3d.hpp"

int main () {

	Point3D p1(0, 0, 0);
	Point3D p2(1, 1, 1);

	double zero2one = sqrt(pow(-1, 2) + pow(-1, 2) + pow(-1, 2));

	assert(p1.DistanceTo(p2) == zero2one);

	return 0;
}

