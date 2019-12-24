import tkinter as tk
from core_system.database import DataBase


# noinspection PyAttributeOutsideInit
class Application(tk.Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.pack()
        self.db = DataBase()
        self.create_widgets()

    def create_widgets(self):
        self.connect_button = tk.Button(self, text="Connect to Database", command=self.db.connect)
        self.quit = tk.Button(self, text="QUIT", fg="red", command=root.destroy)
        self.connect_button.pack(side="top")
        self.quit.pack(side="bottom")


root = tk.Tk()

# Set Title
root.title('DBMS Application')

# Adding Padding
padding = 100
windowWidth = root.winfo_reqwidth() + padding
windowHeight = root.winfo_reqheight() + padding

# Gets Offset to Center Screen
positionRight = int(root.winfo_screenwidth() / 2 - windowWidth / 2)
positionDown = int(root.winfo_screenheight() / 2 - windowHeight / 2)

# Set Centered
geometry = '+{}+{}'.format(positionRight, positionDown)
root.geometry(geometry)

# Set Window Size
size = '{}x{}'.format(windowWidth, windowHeight)
root.geometry(size)

root.resizable(0, 0)

app = Application(master=root)
app.mainloop()
