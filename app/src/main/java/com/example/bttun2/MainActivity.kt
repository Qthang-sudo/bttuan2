import ctypes
import threading
import time
import random

user32 = ctypes.windll.user32
screen_width = user32.GetSystemMetrics(0)
screen_height = user32.GetSystemMetrics(1)

num_windows = 30
box_width, box_height = 300, 200

def minimize_all_windows():
HWND_BROADCAST = 0xFFFF
WM_SYSCOMMAND = 0x0112
SC_MINIMIZE = 0xF020
user32.PostMessageW(HWND_BROADCAST, WM_SYSCOMMAND, SC_MINIMIZE, 0)

def show_warning():
x = random.randint(0, screen_width - box_width)
y = random.randint(0, screen_height - box_height)
MessageBox = ctypes.windll.user32.MessageBoxW
MessageBox(0, "Bạn đã bị hack!", "Cảnh báo!", 0x40 | 0x1000)

minimize_all_windows()
time.sleep(1)

threads = []
for _ in range(num_windows):
t = threading.Thread(target=show_warning)
t.start()
threads.append(t)
time.sleep(0.1)

for t in threads:
t.join()