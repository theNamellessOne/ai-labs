import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import AgglomerativeClustering
from tkinter import Tk, Button, Canvas, Entry, Label
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg


def generate_dots(n):
    return np.random.rand(n, 2)


def agglomerate_cluster_dots(dots, n_clusters):
    clustering = AgglomerativeClustering(n_clusters=n_clusters)
    return clustering.fit_predict(dots)


def plot_clusters(ax, dots, labels):
    unique_labels = np.unique(labels)
    colors = plt.cm.rainbow(np.linspace(0, 1, len(unique_labels)))

    for label, color in zip(unique_labels, colors):
        cluster_dots = dots[labels == label]
        ax.scatter(cluster_dots[:, 0], cluster_dots[:, 1], c=[color], label=f'Cluster {label + 1}')

    ax.legend()
    ax.set_title('Agglomerative Clustering')


def on_button_press():
    global canvas
    n_dots = int(entry_dots.get())
    n_clusters = int(entry_clusters.get())

    dots = generate_dots(n_dots)
    labels = agglomerate_cluster_dots(dots, n_clusters)

    fig, ax = plt.subplots()
    plot_clusters(ax, dots, labels)

    if canvas:
        canvas.get_tk_widget().destroy()

    canvas = FigureCanvasTkAgg(fig, master=root)
    canvas.draw()
    canvas.get_tk_widget().pack()


root = Tk()
root.title('Agglomerative Clustering')

canvas = None

label_dots = Label(root, text='Number of Dots:')
label_dots.pack()
entry_dots = Entry(root)
entry_dots.pack()

label_clusters = Label(root, text='Number of Clusters:')
label_clusters.pack()
entry_clusters = Entry(root)
entry_clusters.pack()

button = Button(root, text='Cluster Dots', command=on_button_press)
button.pack()

root.mainloop()

