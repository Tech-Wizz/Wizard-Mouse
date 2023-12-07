import pyautogui
import time
import math
import keyboard

def WizardMouse():
    screen_width, screen_height = pyautogui.size()
    radius = 2
    speed = 0.1  # Decreased sleep time for more responsiveness
    center_x, center_y = screen_width // 2, screen_height // 2

    print("Press 'Esc' to exit.")

    while True:
        for angle in range(0, 360, 10):
            x = center_x + int(radius * math.cos(math.radians(angle)))
            y = center_y + int(radius * math.sin(math.radians(angle)))

            pyautogui.moveTo(x, y, duration=speed)

            # Check if the 'Esc' key is pressed
            if keyboard.is_pressed('esc'):
                print("Exiting the program.")
                return

            time.sleep(0.01)  # Short sleep time for responsiveness

if __name__ == "__main__":
    WizardMouse()
