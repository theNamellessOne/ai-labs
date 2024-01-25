import io
import tkinter as tk
from tkinter import Canvas, Button, Label, PhotoImage
from tensorflow.keras.models import load_model
import numpy as np
from PIL import Image, ImageDraw
from image_format_util import rec_digit

model = load_model('model.h5')


# Function to recognize the digit
def recognize_digit():
    # Get the image from the canvas
    ps = cv.postscript(colormode='gray')
    img = Image.open(io.BytesIO(ps.encode('utf-8')))
    img = img.resize((28, 28), Image.BICUBIC)
    img.save('digit.png')

    img = prediction = rec_digit("digit.png")

    prediction = str(np.argmax(model.predict(img)))

    result_label.config(text=f"Predicted Digit: {prediction}")

# Function to clear the canvas
def clear_canvas():
    cv.delete('all')
    result_label.config(text="Predicted Digit: ")


# Create the main window
root = tk.Tk()
root.title("Digit Recognition")

# Create a canvas for drawing
cv = Canvas(root, width=280, height=280, bg="white")
cv.grid(row=0, column=0, padx=10, pady=10, columnspan=2)

# Create a label for displaying the result
result_label = Label(root, text="Predicted Digit: ")
result_label.grid(row=1, column=0, columnspan=2)

# Create buttons
recognize_button = Button(root, text="Recognize Digit", command=recognize_digit)
recognize_button.grid(row=2, column=0, pady=10)

clear_button = Button(root, text="Clear", command=clear_canvas)
clear_button.grid(row=2, column=1, pady=10)

# Variables for drawing
drawing = False
last_x = 0
last_y = 0


# Function to start drawing
def start_drawing(event):
    global drawing, last_x, last_y
    drawing = True
    last_x = event.x
    last_y = event.y


# Function to draw
def draw(event):
    global drawing, last_x, last_y
    if drawing:
        cv.create_line((last_x, last_y, event.x, event.y), width=10, capstyle=tk.ROUND, smooth=tk.TRUE, splinesteps=36,
                       fill='black')
        last_x = event.x
        last_y = event.y


# Function to stop drawing
def stop_drawing(event):
    global drawing
    drawing = False


# Bind events to functions
cv.bind("<Button-1>", start_drawing)
cv.bind("<B1-Motion>", draw)
cv.bind("<ButtonRelease-1>", stop_drawing)

root.mainloop()

