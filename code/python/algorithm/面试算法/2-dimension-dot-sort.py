
class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def distance_from_origin(self):
        return (self.x ** 2 + self.y ** 2)

    def __repr__(self):
        return f"Point({self.x}, {self.y})"

"""
Given a list of points, sort them in a way that the points are sorted by their distance from the origin.
"""
def sort_points(points: Point):
    return sorted(points,key=lambda p: p.distance_from_origin())



if __name__ == "__main__":
    points = [Point(1, 2), Point(3, 4), Point(0, 0), Point(-1, -1)]
    sorted_points = sort_points(points)
    print("Sorted points by distance from origin:", sorted_points)
