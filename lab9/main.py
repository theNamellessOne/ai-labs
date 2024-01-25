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

# Genetic Algorithm parameters
population_size = 50
generations = 1000
mutation_rate = 0.01


# Function to calculate the total distance of a tour
def calculate_distance(tour):
    distance = 0
    for i in range(len(tour) - 1):
        city1, city2 = tour[i], tour[i + 1]
        distance += np.sqrt((cities[city2][0] - cities[city1][0]) ** 2 + (cities[city2][1] - cities[city1][1]) ** 2)
    # Return to the starting city
    distance += np.sqrt(
        (cities[tour[-1]][0] - cities[tour[0]][0]) ** 2 + (cities[tour[-1]][1] - cities[tour[0]][1]) ** 2)
    return distance


# Function to generate an initial population of tours
def generate_population(size):
    population = []
    for _ in range(size):
        tour = list(cities.keys())
        np.random.shuffle(tour)
        population.append(tour)
    return population


# Function to select parents for crossover using tournament selection
def select_parents(population, tournament_size=3):
    tournament = np.random.choice(len(population), size=tournament_size, replace=False)
    tournament_fitness = [calculate_distance(population[i]) for i in tournament]
    return population[tournament[np.argmin(tournament_fitness)]]


# Function for crossover (Order Crossover)
def crossover(parent1, parent2):
    size = len(parent1)
    start = np.random.randint(size)
    end = np.random.randint(start, size)
    child = [-1] * size
    child[start:end] = parent1[start:end]
    remaining = [gene for gene in parent2 if gene not in child]
    remaining_idx = 0
    for i in range(size):
        if child[i] == -1:
            child[i] = remaining[remaining_idx]
            remaining_idx += 1
    return child


# Function for mutation (Swap Mutation)
def mutate(tour):
    idx1, idx2 = np.random.choice(len(tour), size=2, replace=False)
    tour[idx1], tour[idx2] = tour[idx2], tour[idx1]
    return tour


# Main Genetic Algorithm loop
def genetic_algorithm():
    population = generate_population(population_size)

    for generation in range(generations):
        population = sorted(population, key=lambda x: calculate_distance(x))
        new_population = [population[0]]  # Keep the best tour from the previous generation

        while len(new_population) < population_size:
            parent1 = select_parents(population)
            parent2 = select_parents(population)
            child = crossover(parent1, parent2)

            if np.random.rand() < mutation_rate:
                child = mutate(child)

            new_population.append(child)

        population = new_population

        if generation % 100 == 0:
            best_tour = population[0]
            best_distance = calculate_distance(best_tour)
            print(f"Generation {generation}, Best Distance: {best_distance}, Best Tour: {best_tour}")

    best_tour = population[0]
    best_distance = calculate_distance(best_tour)
    print(f"Final Result - Best Distance: {best_distance}, Best Tour: {best_tour}")
    return best_tour


def plot_tour(tour):
    tour_x = [cities[city][0] for city in tour]
    tour_y = [cities[city][1] for city in tour]

    plt.figure(figsize=(10, 8))
    plt.plot(tour_x + [tour_x[0]], tour_y + [tour_y[0]], marker='o', linestyle='-', label='Tour')
    plt.scatter(tour_x, tour_y, c='red')

    for city, (x, y) in cities.items():
        plt.text(x, y, city)

    total_distance = calculate_distance(tour)
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
    plot_tour(genetic_algorithm())
