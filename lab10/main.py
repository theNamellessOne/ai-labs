import numpy as np
import matplotlib.pyplot as plt

# Define the cities and their coordinates
cities = {
    'Chernivtsi': (1, 37),
    'Odesa': (2, 49),
    'Kyiv': (8, 52),
    'Lviv': (52, 2),
    'Bucurest': (22, 22),
    'Lutsk': (65, 33),
    'Krakow': (4, 18),
}

# ACO parameters
num_ants = 20
alpha = 1.0  # pheromone importance
beta = 2.0  # visibility (distance) importance
rho = 0.1  # pheromone evaporation rate
Q = 100  # total pheromone deposited by each ant

generations = 500


# Function to calculate the distance matrix
def calculate_distance_matrix():
    num_cities = len(cities)
    distance_matrix = np.zeros((num_cities, num_cities))

    for i, (city1, (x1, y1)) in enumerate(cities.items()):
        for j, (city2, (x2, y2)) in enumerate(cities.items()):
            distance_matrix[i, j] = np.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)

    return distance_matrix


distance_matrix = calculate_distance_matrix()


# Function to perform ACO
def ant_colony_optimization():
    num_cities = len(cities)
    pheromone_matrix = np.ones((num_cities, num_cities))  # Initial pheromone levels

    ant_tours = []

    for iteration in range(generations):
        for ant in range(num_ants):
            tour = generate_tour(pheromone_matrix)
            ant_tours.append((tour, calculate_tour_length(tour)))

        update_pheromones(pheromone_matrix, ant_tours)

        if iteration % 100 == 0:
            best_tour = min(ant_tours, key=lambda x: x[1])[0]
            best_distance = calculate_tour_length(best_tour)
            print(f"Iteration {iteration}, Best Distance: {best_distance}, Best Tour: {best_tour}")

    best_tour = min(ant_tours, key=lambda x: x[1])[0]
    best_distance = calculate_tour_length(best_tour)
    print(f"Final Result - Best Distance: {best_distance}, Best Tour: {best_tour}")
    return best_tour


def generate_tour(pheromone_matrix):
    num_cities = len(cities)
    start_city = np.random.choice(list(cities.keys()))  # Choose a random starting city
    unvisited_cities = set(cities.keys())
    unvisited_cities.remove(start_city)
    tour = [start_city]

    while unvisited_cities:
        next_city = choose_next_city(pheromone_matrix, tour[-1], unvisited_cities)
        tour.append(next_city)
        unvisited_cities.remove(next_city)

    return tour


def choose_next_city(pheromone_matrix, current_city, unvisited_cities):
    num_cities = len(cities)

    current_city_index = list(cities.keys()).index(current_city)
    unvisited_indices = [list(cities.keys()).index(city) for city in unvisited_cities]

    pheromone_values = pheromone_matrix[current_city_index, unvisited_indices]
    visibility_values = 1 / distance_matrix[current_city_index, unvisited_indices]

    probabilities = (pheromone_values ** alpha) * (visibility_values ** beta)
    probabilities /= probabilities.sum()

    next_city_index = np.random.choice(unvisited_indices, p=probabilities)
    next_city = list(cities.keys())[next_city_index]

    return next_city


def calculate_tour_length(tour):
    total_distance = sum(
        distance_matrix[list(cities.keys()).index(i), list(cities.keys()).index(j)] for i, j in zip(tour, tour[1:]))
    total_distance += distance_matrix[
        list(cities.keys()).index(tour[-1]), list(cities.keys()).index(tour[0])]  # Return to the starting city
    return total_distance


def update_pheromones(pheromone_matrix, ant_tours):
    pheromone_matrix *= (1 - rho)  # Evaporation

    for tour, tour_length in ant_tours:
        tour_indices = [list(cities.keys()).index(city) for city in tour]

        for i, j in zip(tour_indices, tour_indices[1:]):
            pheromone_matrix[i, j] += Q / tour_length
            pheromone_matrix[j, i] += Q / tour_length


def plot_tour(tour):
    tour_x = [cities[city][0] for city in tour]
    tour_y = [cities[city][1] for city in tour]

    plt.figure(figsize=(10, 8))
    plt.plot(tour_x + [tour_x[0]], tour_y + [tour_y[0]], marker='o', linestyle='-', label='Tour')
    plt.scatter(tour_x, tour_y, c='red')

    for city, (x, y) in cities.items():
        plt.text(x, y, city)

    total_distance = calculate_tour_length(tour)  # Fix this line
    plt.title(f'Traveling Salesman Problem\nTour: {tour}\nTotal Distance: {total_distance:.2f}')

    for i in range(len(tour) - 1):
        city1, city2 = tour[i], tour[i + 1]
        distance = np.sqrt((cities[city2][0] - cities[city1][0]) ** 2 + (cities[city2][1] - cities[city1][1]) ** 2)
        plt.text((cities[city1][0] + cities[city2][0]) / 2, (cities[city1][1] + cities[city2][1]) / 2,
                 f'{distance:.2f}', color='blue')

    plt.xlabel('X Coordinate')
    plt.ylabel('Y Coordinate')
    plt.legend()
    plt.grid(True)
    plt.show()


if __name__ == "__main__":
    best_tour_aco = ant_colony_optimization()
    plot_tour(best_tour_aco)
