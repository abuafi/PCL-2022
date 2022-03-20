import math

class CityData:
    def __init__(self, name, x, y):
        self.name = name
        self.x = int(x)
        self.y = int(y)
        self.area = 0

    def get_name(self):
        return self.name

    def get_coords(self):
        return self.x, self.y

    def increment_area(self, area):
        self.area += area

    def __lt__(self, other):
        if self.area == other.area:
            return self.name > other.name
        return self.area < other.area

# def euclidean_distance(x1, x2, y1, y2):
#     return math.sqrt(pow(x2-x1, 2) + pow(y2-y1, 2))

def increment_city_area(city_one: CityData, city_two: CityData, city_three: CityData):
    area = abs((city_one.x *(city_two.y - city_three.y)) + (city_two.x *(city_three.y - city_one.y)) + (city_three.x *(city_one.y - city_two.y)))/2
    # a = euclidean_distance(city_one.x, city_two.x, city_one.y, city_two.y)
    # b = euclidean_distance(city_two.x, city_three.x, city_two.y, city_three.y)
    # c = euclidean_distance(city_three.x, city_one.x, city_three.y, city_one.y)
    # s = (a + b + c)/2
    # area = math.sqrt(s*(s-a)*(s-b)*(s-c))
    city_one.increment_area(area)
    city_two.increment_area(area)
    city_three.increment_area(area)

if __name__ == '__main__':
    cities_and_districts = input().split()
    cities, districts = int(cities_and_districts[0]), int(cities_and_districts[1])
    city_array = list()
    for i in range(0, cities):
        city_inp = input().split()
        city_array.append(CityData(city_inp[0], city_inp[1], city_inp[2]))

    for i in range(0, districts):
        dis_inp = input().split()
        increment_city_area(city_array[int(dis_inp[0]) - 1], city_array[int(dis_inp[1]) - 1], city_array[int(dis_inp[2]) - 1])

    city_array.sort(reverse=True)

    if len(city_array) > 0:
        max_area = city_array[0].area
        for c in city_array:
            if (c.area != max_area): break
            print(c.name)
    else:
        print()
